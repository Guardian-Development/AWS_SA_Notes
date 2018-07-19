package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto;

import org.junit.Test;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;
import java.util.Date;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatusToTweetDTOTransformerTest {

    @Test
    public void fromStatusToTweetCanTransformCorrectly() {
        Date statusDate = new Date();
        HashtagEntity hashtagEntity = mock(HashtagEntity.class);
        HashtagEntity[] hashtagEntities = new HashtagEntity[] { hashtagEntity };
        User user = mock(User.class);
        Status status = mock(Status.class);

        when(hashtagEntity.getText()).thenReturn("hashtag");
        when(user.getId()).thenReturn(2000L);
        when(status.getCreatedAt()).thenReturn(statusDate);
        when(status.getUser()).thenReturn(user);
        when(status.getText()).thenReturn("message");
        when(status.getHashtagEntities()).thenReturn(hashtagEntities);

        Optional<TweetDTO> tweetDTO = StatusToTweetDTOTransformer.fromStatus(status);

        assertThat(tweetDTO.isPresent(), is(true));
        assertThat(tweetDTO.get().getUserId(), is("2000"));
        assertThat(tweetDTO.get().getCreatedDate(), is(statusDate));
        assertThat(tweetDTO.get().getHashtags(), contains("hashtag"));
        assertThat(tweetDTO.get().getMessage(), is("message"));
    }

    @Test
    public void fromStatusToTweetFailsCausesOptionalEmptyReturned() {
        Status status = mock(Status.class);
        when(status.getText()).thenReturn(null);

        Optional<TweetDTO> tweetDTO = StatusToTweetDTOTransformer.fromStatus(status);

        assertThat(tweetDTO.isPresent(), is(false));
    }
}