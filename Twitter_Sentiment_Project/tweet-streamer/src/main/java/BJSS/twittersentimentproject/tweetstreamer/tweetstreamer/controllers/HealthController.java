package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
public class HealthController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String healthCheckResponse() {
        LOGGER.info("Health check requested");

        return "<h1>I am healthy.</h1>";
    }
}
