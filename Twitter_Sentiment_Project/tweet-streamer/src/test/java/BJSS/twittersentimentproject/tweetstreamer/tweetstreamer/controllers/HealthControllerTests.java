package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.controllers;

import BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.dao.IProvideTweetDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthControllerTests {

	@MockBean
	private IProvideTweetDAO tweetDAO;

    @Autowired
    private HealthController healthController;

	@Test
	public void canCreateHealthControllerWithinSpringContext() {
		assertThat(healthController).isNotNull();
	}

	@Test
    public void canReceiveHealthCheckSuccessfully() {
	    assertThat(healthController.healthCheckResponse()).contains("I am healthy.");
    }
}
