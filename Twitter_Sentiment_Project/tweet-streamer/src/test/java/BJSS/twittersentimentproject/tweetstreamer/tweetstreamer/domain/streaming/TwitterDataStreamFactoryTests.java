package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import org.junit.Test;
import twitter4j.TwitterStream;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;

public class TwitterDataStreamFactoryTests {

    @Test
    public void canSuccessfullyCreateTwitterStreamControllerNoFilters() {
        TwitterStream stream =  mock(TwitterStream.class);
        TwitterDataStreamFactory factory = new TwitterDataStreamFactory(stream, mock(IProvideTweetDAO.class));

        IControlTwitterDataStream controller = factory.twitterDataStreamFor(Collections.emptyList());

        assertThat("Controller was successfully created", controller, is(notNullValue()));
    }

    @Test
    public void canSuccessfullyCreateTwitterStreamControllerSingleFilter() {
        TwitterStream stream =  mock(TwitterStream.class);
        TwitterDataStreamFactory factory = new TwitterDataStreamFactory(stream, mock(IProvideTweetDAO.class));

        IControlTwitterDataStream controller = factory.twitterDataStreamFor(Collections.singletonList("testFilter"));

        assertThat("Controller was successfully created", controller, is(notNullValue()));
    }

    @Test
    public void canSuccessfullyCreateTwitterStreamControllerMultipleFilters() {
        TwitterStream stream =  mock(TwitterStream.class);
        TwitterDataStreamFactory factory = new TwitterDataStreamFactory(stream, mock(IProvideTweetDAO.class));

        IControlTwitterDataStream controller = factory.twitterDataStreamFor(
                Arrays.asList("filter1", "filter2", "filter3"));

        assertThat("Controller was successfully created", controller, is(notNullValue()));
    }
}