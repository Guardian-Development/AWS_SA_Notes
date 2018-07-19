package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dto.TweetDTO;

public interface IProvideTweetDAO {
    void saveTweet(TweetDTO tweet);
}
