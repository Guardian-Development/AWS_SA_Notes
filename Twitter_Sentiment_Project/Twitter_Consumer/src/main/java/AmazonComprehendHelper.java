import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

public class AmazonComprehendHelper
{
    public static AmazonComprehend buildAmazonComprehendClient(String accessKey, String secretKey, String region) {
        AWSCredentialsProvider credentials =
                new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey));

        return AmazonComprehendClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(region)
                .build();
    }

    public static DetectSentimentResult detectSentiment(String text, AmazonComprehend comprehendClient) {
        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest()
                .withText(text)
                .withLanguageCode("en");

        return comprehendClient.detectSentiment(detectSentimentRequest);
    }
}
