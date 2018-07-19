# Tweet Streamer
A Spring Boot service that is able to stream tweets relating to specific hashtags.

## Requirements
- docker installed
- gradle installed
- java installed

## Environment Variables
For this to work there needs these environment variables set:
- twitter4j.oauth.consumerKey
- twitter4j.oauth.consumerSecret
- twitter4j.oauth.accessToken
- twitter4j.oauth.accessTokenSecret
- kinesis.stream.accessKey
- kinesis.stream.secretKey