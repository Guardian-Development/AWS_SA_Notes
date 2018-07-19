package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.Optional;
import java.util.function.Function;

public class TwitterStreamStatusListener implements StatusListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamStatusListener.class);

    private final IProvideTweetDAO tweetDAO;
    private final Function<Status, Optional<TweetDTO>> statusToTweet;

    public TwitterStreamStatusListener(IProvideTweetDAO tweetDAO,
                                       Function<Status, Optional<TweetDTO>> statusToTweet) {
        this.tweetDAO = tweetDAO;
        this.statusToTweet = statusToTweet;
    }

    @Override
    public void onStatus(Status status) {
        LOGGER.debug("Processing status with ID: " + status.getId());

        if(status.isRetweet())
        {
            return;
        }

        if(status.getHashtagEntities() != null && status.getHashtagEntities().length > 0)
        {
            statusToTweet.apply(status).ifPresent(tweetDAO::saveTweet);
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        LOGGER.debug("Deletion notice for: " + statusDeletionNotice.getStatusId());
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        LOGGER.debug("Limitation notice: " + numberOfLimitedStatuses);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        LOGGER.debug("Scrub geo: (UserID: " + userId + " UpToStatus: " + upToStatusId + ")");
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        LOGGER.info("Staff warning: " + warning.getMessage());
    }

    @Override
    public void onException(Exception ex) {
        LOGGER.error("Twitter Stream Exception: ", ex);
    }
}
