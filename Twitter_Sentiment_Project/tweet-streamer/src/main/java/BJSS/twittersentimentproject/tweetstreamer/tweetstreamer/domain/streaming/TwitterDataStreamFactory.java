package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.StatusToTweetDTOTransformer;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import java.util.List;

public class TwitterDataStreamFactory
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterDataStreamController.class);

    private final TwitterStream twitterStream;
    private final IProvideTweetDAO tweetDAO;

    public TwitterDataStreamFactory(TwitterStream twitterStream, IProvideTweetDAO tweetDAO){
        this.twitterStream = twitterStream;
        this.tweetDAO = tweetDAO;
    }

    public IControlTwitterDataStream twitterDataStreamFor(List<String> filters) {
        LOGGER.debug("Providing Twitter data stream for filters: " + StringUtils.join(filters));

        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery
                .track(filters.toArray(new String[0]))
                .language("en");

        return new TwitterDataStreamController(
                twitterStream,
                tweetFilterQuery,
                new TwitterStreamStatusListener(tweetDAO, StatusToTweetDTOTransformer::fromStatus));
    }
}
