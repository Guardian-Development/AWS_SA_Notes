package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import org.junit.Test;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TwitterStreamStatusListenerTests {

    @Test
    public void onStatusOriginalTweetCallsDAOSuccessfully() {
        Status twitterStatus = mock(Status.class);
        IProvideTweetDAO tweetDAO = mock(IProvideTweetDAO.class);
        TweetDTO tweetDTO = new TweetDTO();
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(tweetDAO, s -> Optional.of(tweetDTO));

        when(twitterStatus.isRetweet()).thenReturn(false);
        when(twitterStatus.getHashtagEntities()).thenReturn(new HashtagEntity[]{mock(HashtagEntity.class)});

        listener.onStatus(twitterStatus);

        verify(tweetDAO, times(1)).saveTweet(tweetDTO);
        verifyNoMoreInteractions(tweetDAO);
    }

    @Test
    public void onStatusOriginalTweetNullHashtagsDoesNotCallsDAO() {
        Status twitterStatus = mock(Status.class);
        IProvideTweetDAO tweetDAO = mock(IProvideTweetDAO.class);
        TweetDTO tweetDTO = new TweetDTO();
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(tweetDAO, s -> Optional.of(tweetDTO));

        when(twitterStatus.isRetweet()).thenReturn(false);
        when(twitterStatus.getHashtagEntities()).thenReturn(null);

        listener.onStatus(twitterStatus);

        verifyZeroInteractions(tweetDAO);
    }

    @Test
    public void onStatusOriginalTweetEmptyHashtagsDoesNotCallsDAO() {
        Status twitterStatus = mock(Status.class);
        IProvideTweetDAO tweetDAO = mock(IProvideTweetDAO.class);
        TweetDTO tweetDTO = new TweetDTO();
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(tweetDAO, s -> Optional.of(tweetDTO));

        when(twitterStatus.isRetweet()).thenReturn(false);
        when(twitterStatus.getHashtagEntities()).thenReturn(new HashtagEntity[0]);

        listener.onStatus(twitterStatus);

        verifyZeroInteractions(tweetDAO);
    }

    @Test
    public void onStatusRetweetDoesNotCallDAO() {
        Status twitterStatus = mock(Status.class);
        IProvideTweetDAO tweetDAO = mock(IProvideTweetDAO.class);
        TweetDTO tweetDTO = new TweetDTO();
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(tweetDAO, s -> Optional.of(tweetDTO));

        when(twitterStatus.isRetweet()).thenReturn(true);

        listener.onStatus(twitterStatus);

        verifyZeroInteractions(tweetDAO);
    }

    @Test
    public void onStatusTransformReturningOptionalEmptyDoesNotCallDAO() {
        Status twitterStatus = mock(Status.class);

        IProvideTweetDAO tweetDAO = mock(IProvideTweetDAO.class);
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(tweetDAO, s -> Optional.empty());

        listener.onStatus(twitterStatus);
        verifyZeroInteractions(tweetDAO);
    }
}