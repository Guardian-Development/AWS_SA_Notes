import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;

//                        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
//                                .withPrimaryKey("Hashtag", hashtag)
//                                .withUpdateExpression("SET tweets = list_append(:t, tweets)")
//                                .withValueMap(new ValueMap().withList(":t", Collections.singletonList(tweet)))
//                                .withReturnValues(ReturnValue.UPDATED_NEW);
//
//                        try
//                        {
//                            System.out.println("Updating the item...");
//                            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
//                            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
//
//                        }
//                        catch (Exception e)
//                        {
//                            System.out.println("Failure!");
//                            System.out.println(e.getMessage());
//                            System.out.println(Arrays.toString(e.getStackTrace()));
//                        }

public class KinesisTweetEventHandler implements RequestHandler<KinesisEvent, Void>
{
        public Void handleRequest(KinesisEvent event, Context context)
        {
            for(KinesisEventRecord record : event.getRecords())
            {
                try
                {
                    Tweet tweet = parseTweetFromKinesisEventRecord(record);
                    System.out.println("Detecting sentiment for: " + tweet.Body);

                    AmazonComprehend comprehendClient = buildAmazonComprehendClient(
                            System.getenv("lambdaAccessKey"),
                            System.getenv("lambdaSecretKey"),
                            System.getenv("lambdaComprehendRegion"));

                    System.out.println("Calling DetectSentiment");

                    DetectSentimentResult detectSentimentResult = detectSentiment(tweet.Body, comprehendClient);
                    System.out.println("Sentiment result:" + detectSentimentResult.getSentiment());

                    Table tweetsTable = buildDynamoDBTableConnection(
                            System.getenv("lambdaAccessKey"),
                            System.getenv("lambdaSecretKey"),
                            System.getenv("lambdaDynamoRegion"),
                            System.getenv("lambdaDynamoTweetsTableName"));

                    storeTweetWithSentimentInDynamoDB(tweetsTable, tweet, detectSentimentResult);
                }
                catch (IOException e)
                {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }

            return null;
        }

        private Table buildDynamoDBTableConnection(String accesskey, String secretKey, String region, String tableName) {
            AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
            builder.setRegion(region);
            builder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey,secretKey)));
            AmazonDynamoDB client = builder.build();
            DynamoDB dynamoDB = new DynamoDB(client);
            return dynamoDB.getTable(tableName);
        }

        private void storeTweetWithSentimentInDynamoDB(Table tweetsTable, Tweet tweet, DetectSentimentResult sentimentResult) {
            for(String hashtag : tweet.HashTags)
            {
                final Map<String, Object> tweetAsMap = new HashMap<String, Object>();
                tweetAsMap.put("UserId", tweet.UserId);
                tweetAsMap.put("Body", tweet.Body);
                tweetAsMap.put("CreatedDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(tweet.CreatedDate));
                tweetAsMap.put("SentimentClassification", sentimentResult.getSentiment());

                try
                {
                    System.out.println("Adding item to database");
                    PutItemOutcome outcome = tweetsTable.putItem(new Item()
                                    .withPrimaryKey("Hashtag", hashtag)
                                    .withList("tweets", Collections.singletonList(tweetAsMap)));

                    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

                }
                catch (Exception e)
                {
                    System.out.println("Failure!");
                    System.err.println(e.getMessage());
                }
            }
        }

        private Tweet parseTweetFromKinesisEventRecord(KinesisEventRecord record) throws IOException {
            return new ObjectMapper().readValue(record.getKinesis().getData().array(), Tweet.class);
        }

        private AmazonComprehend buildAmazonComprehendClient(String accessKey, String secretKey, String region) {
            AWSCredentialsProvider credentials =
                    new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(accessKey, secretKey));

            return AmazonComprehendClientBuilder.standard()
                    .withCredentials(credentials)
                    .withRegion(region)
                    .build();
        }

        private DetectSentimentResult detectSentiment(String text, AmazonComprehend comprehendClient) {
            DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest()
                    .withText(text)
                    .withLanguageCode("en");

            return comprehendClient.detectSentiment(detectSentimentRequest);
        }
}
