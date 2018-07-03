resource "aws_lambda_function" "tweet_sentiment_analysis_function" {
  filename = "${var.twitter_consumer_source_jar_path}"
  function_name = "Kinesis-Twitter-Consumer"
  role = "${aws_iam_role.lambda_execution_role.arn}"
  handler = "KinesisTweetEventHandler"
  runtime = "java8"
  timeout = 30
  memory_size = 256
  source_code_hash = "${base64sha256(file(var.twitter_consumer_source_jar_path))}"

  environment {
    variables = {
      lambdaAccessKey = "${var.access_key}"
      lambdaSecretKey = "${var.secret_key}"
      lambdaComprehendRegion = "eu-west-1"
      lambdaDynamoRegion = "${var.region}"
      lambdaDynamoTweetsTableName = "${var.tweets_table_name}"
    }
  }

  tags {
    Deployment = "${var.deployment_tag}"
  }
}

resource "aws_lambda_event_source_mapping" "kinesis_lambda_event_mapping" {
  batch_size = 5
  event_source_arn = "${aws_kinesis_stream.tweets-stream.arn}"
  enabled = true
  function_name = "${aws_lambda_function.tweet_sentiment_analysis_function.arn}"
  starting_position = "TRIM_HORIZON"
}