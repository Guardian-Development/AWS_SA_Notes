package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;

public class KinesisTweetDAO implements IProvideTweetDAO
{
    private static final Logger LOGGER = LoggerFactory.getLogger(KinesisTweetDAO.class);

    private final AmazonKinesis kinesisClient;
    private final String kinesisStreamName;
    private final Function<TweetDTO, Optional<PutRecordRequest>> tweetDTOToPutRecordRequest;

    public KinesisTweetDAO(AmazonKinesis kinesisClient,
                           String kinesisStreamName,
                           Function<TweetDTO, Optional<PutRecordRequest>> tweetDTOToPutRecordRequest) {
        this.kinesisClient = kinesisClient;
        this.kinesisStreamName = kinesisStreamName;
        this.tweetDTOToPutRecordRequest = tweetDTOToPutRecordRequest;
    }

    @Override
    public void saveTweet(TweetDTO tweet) {
        LOGGER.debug("Saving tweet: " + tweet);

        Optional<PutRecordRequest> putRecordRequest = tweetDTOToPutRecordRequest.apply(tweet);
        putRecordRequest.ifPresent(r -> {
            r.setStreamName(kinesisStreamName);
            PutRecordResult result = kinesisClient.putRecord(r);
            LOGGER.debug("Tweet:" + tweet + " saved successfully under ID: " + result.getSequenceNumber());
        });
    }
}
