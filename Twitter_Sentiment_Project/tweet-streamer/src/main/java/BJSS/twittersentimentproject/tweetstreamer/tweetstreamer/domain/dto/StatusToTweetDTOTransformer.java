package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class StatusToTweetDTOTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusToTweetDTOTransformer.class);

    public static Optional<TweetDTO> fromStatus(Status status) {
        LOGGER.debug("Attempting to transform status to tweet: " + status);

        try {
            TweetDTO tweetDTO = new TweetDTO();
            tweetDTO.setUserId(String.valueOf(status.getUser().getId()));
            tweetDTO.setMessage(status.getText());
            tweetDTO.setHashtags(Arrays.stream(status.getHashtagEntities())
                    .map(HashtagEntity::getText)
                    .collect(Collectors.toList()));
            tweetDTO.setCreatedDate(status.getCreatedAt());

            LOGGER.debug("Successfully transformed status: " + status);
            return Optional.of(tweetDTO);
        }
        catch (Exception e)
        {
            LOGGER.warn("Failed to transform status: " + status);
            return Optional.empty();
        }
    }
}
