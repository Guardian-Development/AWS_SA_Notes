package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.controllers;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.cache.RuntimeCache;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming.IControlTwitterDataStream;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming.TwitterDataStreamFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterStreamControllerTests {

    @Autowired
    private TwitterStreamController twitterStreamController;

    @MockBean
    private RuntimeCache runtimeCache;

    @MockBean
    private TwitterDataStreamFactory twitterDataStreamFactory;

    @MockBean
    private IProvideTweetDAO tweetDAO;

    @Test
    public void canSuccessfullyRequestStreamToStartMultipleFilters() {
        List<String> filters = Arrays.asList("filter1", "filter2");

        IControlTwitterDataStream dataStream = mock(IControlTwitterDataStream.class);
        when(runtimeCache.getItem(eq("TwitterStreamThread"), any())).thenReturn(Optional.empty());
        when(runtimeCache.getItem(eq("TwitterStreamSource"), any())).thenReturn(Optional.empty());
        when(twitterDataStreamFactory.twitterDataStreamFor(filters)).thenReturn(dataStream);

        twitterStreamController.startStream(filters);

        verify(dataStream, timeout(1000).times(1)).run();
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamThread");
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamSource");
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamThread"), any());
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamSource"), eq(dataStream));
    }

    @Test
    public void canSuccessfullyRequestStreamToStartSingleFilter() {
        List<String> filters = Collections.singletonList("filter2");

        IControlTwitterDataStream dataStream = mock(IControlTwitterDataStream.class);
        when(runtimeCache.getItem(eq("TwitterStreamThread"), any())).thenReturn(Optional.empty());
        when(runtimeCache.getItem(eq("TwitterStreamSource"), any())).thenReturn(Optional.empty());
        when(twitterDataStreamFactory.twitterDataStreamFor(filters)).thenReturn(dataStream);

        twitterStreamController.startStream(filters);

        verify(dataStream, timeout(1000).times(1)).run();
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamThread");
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamSource");
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamThread"), any());
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamSource"), eq(dataStream));
    }

    @Test
    public void canSuccessfullyRequestStreamToStartNoFilters() {
        List<String> filters = Collections.emptyList();

        IControlTwitterDataStream dataStream = mock(IControlTwitterDataStream.class);
        when(runtimeCache.getItem(eq("TwitterStreamThread"), any())).thenReturn(Optional.empty());
        when(runtimeCache.getItem(eq("TwitterStreamSource"), any())).thenReturn(Optional.empty());
        when(twitterDataStreamFactory.twitterDataStreamFor(filters)).thenReturn(dataStream);

        twitterStreamController.startStream(filters);

        verify(dataStream, timeout(1000).times(1)).run();
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamThread");
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamSource");
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamThread"), any());
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamSource"), eq(dataStream));
    }

    @Test
    public void requestingStreamToStartWithExistingStreamStopsExistingStream() throws InterruptedException {
        List<String> filters = Collections.emptyList();

        IControlTwitterDataStream dataStream = mock(IControlTwitterDataStream.class);
        when(runtimeCache.getItem(eq("TwitterStreamThread"), any())).thenReturn(Optional.empty());
        when(runtimeCache.getItem(eq("TwitterStreamSource"), any())).thenReturn(Optional.of(dataStream));
        when(twitterDataStreamFactory.twitterDataStreamFor(filters)).thenReturn(dataStream);

        twitterStreamController.startStream(filters);

        verify(dataStream, timeout(1000).times(1)).endStream();

        verify(dataStream, timeout(1000).times(1)).run();
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamThread");
        verify(runtimeCache, times(1)).clearKeyIfPresent("TwitterStreamSource");
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamThread"), any());
        verify(runtimeCache, times(1)).putItem(eq("TwitterStreamSource"), eq(dataStream));
    }
}
