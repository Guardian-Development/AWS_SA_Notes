package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import org.junit.Test;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TwitterDataStreamControllerTests {

    @Test
    public void startStreamSuccessfullyRegistersListenerForFilteredStream() {
        FilterQuery query = new FilterQuery();
        TwitterStream stream = mock(TwitterStream.class);
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(mock(IProvideTweetDAO.class), s -> Optional.of(new TweetDTO()));

        doNothing().when(stream).addListener(listener);
        doNothing().when(stream).filter(query);

        TwitterDataStreamController controller = new TwitterDataStreamController(stream, query, listener);
        controller.startStream();

        verify(stream, times(1)).addListener(listener);
        verify(stream, times(1)).filter(query);
        verifyNoMoreInteractions(stream);
    }

    @Test
    public void endStreamSuccessfullyStopsStream() {
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(mock(IProvideTweetDAO.class), s -> Optional.of(new TweetDTO()));
        TwitterStream stream = mock(TwitterStream.class);

        doNothing().when(stream).cleanUp();
        doNothing().when(stream).shutdown();

        TwitterDataStreamController controller =
                new TwitterDataStreamController(stream, new FilterQuery(), listener);

        controller.endStream();

        verify(stream, times(1)).cleanUp();
        verify(stream, times(1)).shutdown();
        verifyNoMoreInteractions(stream);
    }

    @Test
    public void runSuccessfullyRegistersListenerForFilteredStream() {
        FilterQuery query = new FilterQuery();
        TwitterStream stream = mock(TwitterStream.class);
        TwitterStreamStatusListener listener =
                new TwitterStreamStatusListener(mock(IProvideTweetDAO.class), s -> Optional.of(new TweetDTO()));

        doNothing().when(stream).addListener(listener);
        doNothing().when(stream).filter(query);

        TwitterDataStreamController controller = new TwitterDataStreamController(stream, query, listener);
        controller.run();

        verify(stream, times(1)).addListener(listener);
        verify(stream, times(1)).filter(query);
        verifyNoMoreInteractions(stream);
    }
}