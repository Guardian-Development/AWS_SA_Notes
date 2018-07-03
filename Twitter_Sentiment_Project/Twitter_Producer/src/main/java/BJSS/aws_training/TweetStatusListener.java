package BJSS.aws_training;

import twitter4j.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TweetStatusListener implements StatusListener
{
    private IProvideDownstreamAnalysis<Tweet> provideDownstreamAnalysis;

    TweetStatusListener(IProvideDownstreamAnalysis<Tweet> provideDownstreamAnalysis)
    {
        this.provideDownstreamAnalysis = provideDownstreamAnalysis;
    }

    public void onStatus(Status status)
    {
        if(status.getHashtagEntities() != null && status.getHashtagEntities().length > 0)
        {
            String userId = String.valueOf(status.getUser().getId());
            String body = status.getText();
            List<String> hashTags = Arrays.stream(status.getHashtagEntities())
                    .map(HashtagEntity::getText)
                    .collect(Collectors.toList());

            Date createdDate = status.getCreatedAt();

            provideDownstreamAnalysis.postMessageForAnalysis(new Tweet(userId, body, createdDate, hashTags));
        }
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice){}
    public void onTrackLimitationNotice(int numberOfLimitedStatuses){}
    public void onScrubGeo(long userId, long upToStatusId){}
    public void onStallWarning(StallWarning warning)
    {
        System.out.println("Stall Warning: " + warning);
    }
    public void onException(Exception ex)
    {
        ex.printStackTrace();
    }
}
