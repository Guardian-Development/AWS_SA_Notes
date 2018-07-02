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
                        System.getenv("aws.kinesis.region"),
                        System.getenv("aws.kinesis.accessKey"),
                        System.getenv("aws.kinesis.secretKey"),
                        System.getenv("aws.kinesis.streamName")));

        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}
