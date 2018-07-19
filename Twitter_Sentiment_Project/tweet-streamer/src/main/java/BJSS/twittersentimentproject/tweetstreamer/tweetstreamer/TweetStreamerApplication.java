package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TweetStreamerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	public static void main(String[] args) {
		LOGGER.info("Starting application..");

		SpringApplication.run(TweetStreamerApplication.class, args);
	}
}
