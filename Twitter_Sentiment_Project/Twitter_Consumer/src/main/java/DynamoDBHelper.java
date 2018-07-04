import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DynamoDBHelper
{
    public static Table buildDynamoDBTableConnection(String accesskey, String secretKey, String region, String tableName) {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        builder.setRegion(region);
        builder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey,secretKey)));
        AmazonDynamoDB client = builder.build();
        DynamoDB dynamoDB = new DynamoDB(client);
        return dynamoDB.getTable(tableName);
    }

    public static void storeTweetWithSentimentInDynamoDB(Table tweetsTable, Tweet tweet, DetectSentimentResult sentimentResult) {
        for(String hashtag : tweet.HashTags)
        {
            final Map<String, Object> tweetAsMap = buildMapFromTweetAndSentiment(tweet, sentimentResult);

            try
            {
                System.out.println("Adding item to database");

                UpdateItemSpec updateItemSpec = buildUpdateItemSpecForTweetMap(hashtag, tweetAsMap);
                UpdateItemOutcome outcome = tweetsTable.updateItem(updateItemSpec);

                System.out.println("PutItem succeeded:\n" + outcome.getUpdateItemResult());

            }
            catch (Exception e)
            {
                System.out.println("Failure!");
                System.err.println(e.getMessage());
            }
        }
    }

    private static UpdateItemSpec buildUpdateItemSpecForTweetMap(String hashtag, Map<String, Object> tweetAsMap) {
        return new UpdateItemSpec()
                .withPrimaryKey("Hashtag", hashtag)
                .withReturnValues(ReturnValue.ALL_NEW)
                .withUpdateExpression("SET tweets = list_append(if_not_exists(tweets, :empty_list), :t)")
                .withValueMap(new ValueMap()
                        .withList(":t", Collections.singletonList(tweetAsMap))
                        .withList(":empty_list", Collections.emptyList()));
    }

    private static Map<String, Object> buildMapFromTweetAndSentiment(Tweet tweet, DetectSentimentResult sentimentResult) {
        final Map<String, Object> tweetAsMap = new HashMap<String, Object>();
        tweetAsMap.put("UserId", tweet.UserId);
        tweetAsMap.put("Body", tweet.Body);
        tweetAsMap.put("CreatedDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(tweet.CreatedDate));
        tweetAsMap.put("SentimentClassification", sentimentResult.getSentiment());
        return tweetAsMap;
    }
}
