resource "aws_lambda_function" "tweet_sentiment_analysis_function" {
  filename = "${var.twitter_consumer_source_jar_path}"
  function_name = "Kinesis-Twitter-Consumer"
  role = "${aws_iam_role.lambda_execution_role.arn}"
  handler = "KinesisTweetEventHandler"
  runtime = "java8"
  source_code_hash = "${base64sha256(file(${var.twitter_consumer_source_jar_path}))}"
}

resource "aws_lambda_event_source_mapping" "kinesis_lambda_event_mapping" {
  batch_size = 5
  event_source_arn = "${aws_kinesis_stream.tweets-stream.arn}"
  enabled = true
  function_name = "${aws_lambda_function.tweet_sentiment_analysis_function.arn}"
  starting_position = "TRIM_HORIZON"
}

//TODO: update function so that it calls sentiment analysis service.
//TODO: save result of tweet and sentiment analysis in DynamoDB.