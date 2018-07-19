package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Optional;

public class KinesisTweetDAOHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinesisTweetDAOHelper.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static Optional<PutRecordRequest> putRecordRequestFromTweetDTO(TweetDTO tweetDTO) {
        LOGGER.debug("Attempting to parse tweet: " + tweetDTO);

        try {
            String json = mapper.writeValueAsString(tweetDTO);
            PutRecordRequest request = new PutRecordRequest();
            request.setPartitionKey(String.format("partitionKey-%d", 0));
            request.setData(ByteBuffer.wrap(json.getBytes()));

            LOGGER.debug("Successfully parsed tweet: " + tweetDTO);
            return Optional.of(request);
        }
        catch (JsonProcessingException e)
        {
            LOGGER.warn("Failed to parse tweet: " + tweetDTO);
            return Optional.empty();
        }
    }
}
