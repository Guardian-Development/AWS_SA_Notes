package BJSS.aws_training;

import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

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
