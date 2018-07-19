package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto;

import java.util.Date;
import java.util.List;

public class TweetDTO {

    private String userId;
    private String message;
    private Date createdDate;
    private List<String> hashtags;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
