package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

public class TweetDTOTests {

    @Test
    public void allPropertiesAreSuccessfullyAbleToBeSetAndGet() {
        Date tweetDTODate = new Date();
        List<String> tweetDTOHashtags = Arrays.asList("test1", "test2");

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setUserId("testUser");
        tweetDTO.setMessage("testMessage");
        tweetDTO.setCreatedDate(tweetDTODate);
        tweetDTO.setHashtags(tweetDTOHashtags);

        assertThat(tweetDTO.getUserId(), is("testUser"));
        assertThat(tweetDTO.getMessage(), is("testMessage"));
        assertThat(tweetDTO.getCreatedDate(), is(tweetDTODate));
        assertThat(tweetDTO.getHashtags(), is(tweetDTOHashtags));
    }
}