import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

import static com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;

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
                    System.out.println("Mixed: " + detectSentimentResult.getSentimentScore().getMixed());
                    System.out.println("Negative: " +detectSentimentResult.getSentimentScore().getNegative());
                    System.out.println("Neutral: " +detectSentimentResult.getSentimentScore().getNeutral());
                    System.out.println("Positive: " +detectSentimentResult.getSentimentScore().getPositive());
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
