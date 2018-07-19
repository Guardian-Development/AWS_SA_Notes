package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KinesisTweetDAOHelperTests {

    @Test
    public void creationOfPutRecordRequestSucceeds() {
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setCreatedDate(new Date());
        tweetDTO.setHashtags(Arrays.asList("Test", "Test2"));
        tweetDTO.setUserId("user");
        tweetDTO.setMessage("message");

        Optional<PutRecordRequest> request = KinesisTweetDAOHelper.putRecordRequestFromTweetDTO(tweetDTO);
        assertThat(request.isPresent(), is(true));
    }

    @Test
    public void creationOfTweetDAOThrowsExceptionCausingPutRecordToNotBeCreated() {
        TweetDTO tweetDTO = mock(TweetDTO.class);
        when(tweetDTO.getUserId()).thenAnswer(s -> new Exception());

        Optional<PutRecordRequest> request = KinesisTweetDAOHelper.putRecordRequestFromTweetDTO(tweetDTO);

        assertThat(request.isPresent(), is(false));
    }
}