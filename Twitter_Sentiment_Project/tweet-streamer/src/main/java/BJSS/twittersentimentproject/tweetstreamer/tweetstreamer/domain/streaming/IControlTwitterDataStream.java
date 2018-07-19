package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.streaming;

public interface IControlTwitterDataStream extends Runnable {
    void startStream();
    void endStream();
}
