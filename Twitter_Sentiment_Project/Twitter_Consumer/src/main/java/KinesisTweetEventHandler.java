import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import static com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;

/**
 * Body of the Lambda function. Requires the environment variables set:
 *      - lambdaAccessKey
 *      - lambdaSecretKey
 *      - lambdaComprehendRegion
 *      - lambdaDynamoRegion
 *      - lambdaDynamoTweetsTableName
 */
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

                    AmazonComprehend comprehendClient = AmazonComprehendHelper.buildAmazonComprehendClient(
                            System.getenv("lambdaAccessKey"),
                            System.getenv("lambdaSecretKey"),
                            System.getenv("lambdaComprehendRegion"));

                    System.out.println("Calling DetectSentiment");

                    DetectSentimentResult detectSentimentResult =
                            AmazonComprehendHelper.detectSentiment(tweet.Body, comprehendClient);

                    System.out.println("Sentiment result:" + detectSentimentResult.getSentiment());

                    Table tweetsTable = DynamoDBHelper.buildDynamoDBTableConnection(
                            System.getenv("lambdaAccessKey"),
                            System.getenv("lambdaSecretKey"),
                            System.getenv("lambdaDynamoRegion"),
                            System.getenv("lambdaDynamoTweetsTableName"));

                    DynamoDBHelper.storeTweetWithSentimentInDynamoDB(tweetsTable, tweet, detectSentimentResult);
                }
                catch (IOException e)
                {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }

            return null;
        }

        private Tweet parseTweetFromKinesisEventRecord(KinesisEventRecord record) throws IOException {
            return new ObjectMapper().readValue(record.getKinesis().getData().array(), Tweet.class);
        }
}
