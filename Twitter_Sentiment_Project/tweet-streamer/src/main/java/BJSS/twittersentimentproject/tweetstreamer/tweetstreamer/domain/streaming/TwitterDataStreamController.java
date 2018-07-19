package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

public class TwitterDataStreamController implements IControlTwitterDataStream
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterDataStreamController.class);

    private final TwitterStream twitterStream;
    private final FilterQuery filterQuery;
    private final StatusListener twitterStatusListener;

    public TwitterDataStreamController(TwitterStream twitterStream,
                                       FilterQuery filterQuery,
                                       StatusListener twitterStatusListener){
        this.twitterStream = twitterStream;
        this.filterQuery = filterQuery;
        this.twitterStatusListener = twitterStatusListener;
    }

    @Override
    public void startStream() {
        LOGGER.info("Starting stream with filter: " + filterQuery);

        twitterStream.addListener(twitterStatusListener);
        twitterStream.filter(filterQuery);
    }

    @Override
    public void endStream() {
        LOGGER.info("Ending stream with filter: " + filterQuery);

        twitterStream.cleanUp();
        twitterStream.shutdown();
    }

    @Override
    public void run() {
        startStream();
    }
}
