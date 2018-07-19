package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.cache.RuntimeCache;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.KinesisTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.KinesisTweetDAOHelper;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming.TwitterDataStreamFactory;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
@ComponentScan("BJSS.twittersentimentproject.tweetstreamer.tweetstreamer")
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    public TwitterDataStreamFactory twitterDataStreamFactory() {
        LOGGER.debug("Providing twitter data stream factory");

        return new TwitterDataStreamFactory(twitterStream(), tweetDAO());
    }

    @Bean
    public IProvideTweetDAO tweetDAO() {
        LOGGER.debug("Providing TweetDAO");

        return new KinesisTweetDAO(kinesisClientFromEnvironmentVariables(),
                System.getenv("kinesis.stream.name"),
                KinesisTweetDAOHelper::putRecordRequestFromTweetDTO);
    }

    public AmazonKinesis kinesisClientFromEnvironmentVariables() {
        LOGGER.debug("Providing AmazonKinesis client");

        AmazonKinesisClientBuilder builder = AmazonKinesisClientBuilder.standard();
        builder.setRegion(System.getenv("kinesis.stream.region"));
        builder.setCredentials(
                new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                System.getenv("kinesis.stream.accessKey"),
                                System.getenv("kinesis.stream.secretKey"))));
        builder.setClientConfiguration(new ClientConfiguration());
        builder.setClientConfiguration(new ClientConfiguration());
        return builder.build();
    }

    @Bean
    public TwitterStream twitterStream() {
        LOGGER.debug("Providing TwitterStream");

        return new TwitterStreamFactory().getInstance();
    }

    @Bean
    public RuntimeCache runtimeCache() {
        LOGGER.debug("Providing RuntimeCache");

        return new RuntimeCache();
    }
}
