package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KinesisTweetDAOTests {

    @Test
    public void canSuccessfullySaveTweetToCorrectStream() {
        AmazonKinesis amazonKinesis = mock(AmazonKinesis.class);
        PutRecordRequest putRecordRequest = mock(PutRecordRequest.class);
        TweetDTO tweetDTO = mock(TweetDTO.class);

        KinesisTweetDAO tweetDAO = new KinesisTweetDAO(amazonKinesis, "TestStream", t -> Optional.of(putRecordRequest));
        tweetDAO.saveTweet(tweetDTO);

        verify(putRecordRequest, times(1)).setStreamName(eq("TestStream"));
        verify(amazonKinesis, times(1)).putRecord(putRecordRequest);
        verifyNoMoreInteractions(amazonKinesis);
    }

    @Test
    public void unableToBuildPutRecordRequestDoesNotSaveTweetToStream() {
        AmazonKinesis amazonKinesis = mock(AmazonKinesis.class);
        TweetDTO tweetDTO = mock(TweetDTO.class);

        KinesisTweetDAO tweetDAO = new KinesisTweetDAO(amazonKinesis, "TestStream", t -> Optional.empty());
        tweetDAO.saveTweet(tweetDTO);

        verifyZeroInteractions(amazonKinesis);
    }
}