package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.controllers;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.cache.RuntimeCache;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming.IControlTwitterDataStream;
import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming.TwitterDataStreamFactory;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "twitter/stream")
public class TwitterStreamController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamController.class);

    private static String runningTwitterStreamThread = "TwitterStreamThread";
    private static String runningTwitterStreamSource = "TwitterStreamSource";

    private final @NotNull TwitterDataStreamFactory twitterDataStreamFactory;
    private final @NotNull RuntimeCache runtimeCache;

    public TwitterStreamController(TwitterDataStreamFactory twitterDataStreamFactory,
                                   RuntimeCache runtimeCache) {
        this.twitterDataStreamFactory = twitterDataStreamFactory;
        this.runtimeCache = runtimeCache;
    }

    @RequestMapping(path = "start", method = RequestMethod.GET)
    public void startStream(@RequestParam(value = "filters")List<String > filters) {
        LOGGER.info("Starting Stream for filters: ", StringUtils.join(filters));

        stopExistingStreamIfRunning();
        stopExistingStreamThreadIfRunning();

        IControlTwitterDataStream twitterDataStream = twitterDataStreamFactory.twitterDataStreamFor(filters);
        Thread twitterStreamHandler = new Thread(twitterDataStream);
        twitterStreamHandler.start();

        LOGGER.info("Stream started for: ", StringUtils.join(filters));

        runtimeCache.putItem(runningTwitterStreamSource, twitterDataStream);
        runtimeCache.putItem(runningTwitterStreamThread, twitterStreamHandler);
    }

    @RequestMapping(path = "stop", method = RequestMethod.GET)
    public void stopStream() {
        stopExistingStreamIfRunning();
        stopExistingStreamThreadIfRunning();
    }

    private void stopExistingStreamIfRunning() {
        LOGGER.info("Stopping existing stream if running");

        runtimeCache
                .getItem(runningTwitterStreamSource, IControlTwitterDataStream.class)
                .ifPresent(IControlTwitterDataStream::endStream);
        runtimeCache.clearKeyIfPresent(runningTwitterStreamSource);
    }

    private void stopExistingStreamThreadIfRunning() {
        LOGGER.info("Stopping existing stream thread if running");

        runtimeCache.getItem(runningTwitterStreamThread, Thread.class).ifPresent(s -> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        runtimeCache.clearKeyIfPresent(runningTwitterStreamThread);
    }
}
