package BJSS.aws_training;

import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * In order for this service to run, you need to have the environment variables set:
 *      - awsKinesisRegion
 *      - awsKinesisAccessKey
 *      - awsKinesisSecretKey
 *      - awsKinesisStreamName
 *  Under the resource folder create a file called twitter.properties, this should then contain:
 *      - oauth.consumerKey
 *      - oauth.consumerSecret
 *      - oauth.accessToken
 *      - oauth.accessTokenSecret
 */
public class Main
{
    public static void main(String[] args)
    {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new TweetStatusListener(
                new KinesisDownstreamAnalysisProvider(
                        System.getenv("awsKinesisRegion"),
                        System.getenv("awsKinesisAccessKey"),
                        System.getenv("awsKinesisSecretKey"),
                        System.getenv("awsKinesisStreamName")));

        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}
