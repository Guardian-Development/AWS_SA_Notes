resource "aws_api_gateway_rest_api" "tweet_api" {
  name        = "TweetAPI"
  description = "API to access Hashtags, Tweets and their sentiments."
}

resource "aws_api_gateway_resource" "tweet_api_hastags_end_point" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  parent_id = "${aws_api_gateway_rest_api.tweet_api.root_resource_id}"
  path_part = "hashtags"
}

resource "aws_api_gateway_method" "tweet_api_hashtags_get_end_point" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  resource_id = "${aws_api_gateway_resource.tweet_api_hastags_end_point.id}"
  http_method = "GET"
  authorization = "NONE"
}

resource "aws_api_gateway_resource" "tweet_api_individual_hashtag_end_point" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  parent_id = "${aws_api_gateway_resource.tweet_api_hastags_end_point.id}"
  path_part = "{hashtag}"
}

resource "aws_api_gateway_resource" "tweet_api_hastag_average_sentiment_end_point" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  parent_id = "${aws_api_gateway_resource.tweet_api_individual_hashtag_end_point.id}"
  path_part = "sentiment"
}

resource "aws_api_gateway_method" "tweet_api_hashtag_average_sentiment_get_end_point" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  resource_id = "${aws_api_gateway_resource.tweet_api_hastag_average_sentiment_end_point.id}"
  http_method = "GET"
  authorization = "NONE"

  request_parameters {
    "method.request.path.hashtag" = true
  }
}