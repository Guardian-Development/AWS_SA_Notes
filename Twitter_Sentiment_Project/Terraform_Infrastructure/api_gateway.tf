resource "aws_api_gateway_rest_api" "tweet_api" {
  name        = "TweetAPI"
  description = "API to access Hashtags, Tweets and their sentiments."
}

resource "aws_api_gateway_deployment" "tweet_api_test_deployment_stage" {
  depends_on = [
    "aws_api_gateway_integration.tweet_get_hashtags_lambda_integration"
  ]

  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  stage_name  = "test"
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

resource "aws_lambda_function" "tweet_get_hashtags_function" {
  filename = "${var.twitter_get_hashtags_source_jar_path}"
  function_name = "Get-Hashtags"
  role = "${aws_iam_role.lambda_api_gateway_execution_role.arn}"
  handler = "APIGetHashtagsLambda"
  runtime = "java8"
  timeout = 30
  memory_size = 256
  source_code_hash = "${base64sha256(file(var.twitter_get_hashtags_source_jar_path))}"

  tags {
    Deployment = "${var.deployment_tag}"
  }
}

resource "aws_api_gateway_integration" "tweet_get_hashtags_lambda_integration" {
  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
  resource_id = "${aws_api_gateway_method.tweet_api_hashtags_get_end_point.resource_id}"
  http_method = "${aws_api_gateway_method.tweet_api_hashtags_get_end_point.http_method}"

  integration_http_method = "POST"
  type = "AWS_PROXY"
  uri = "${aws_lambda_function.tweet_get_hashtags_function.invoke_arn}"
}

resource "aws_lambda_permission" "tweet_api_grant_hashtag_lambda_function_access_test_deployment" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = "${aws_lambda_function.tweet_get_hashtags_function.arn}"
  principal     = "apigateway.amazonaws.com"
  source_arn = "${aws_api_gateway_deployment.tweet_api_test_deployment_stage.execution_arn}/*/*"
}

//TODO: work out why API not got correct permissions to execute Lambda
//TODO: add further Lambda end points
//TODO: actually implement the end points properly

//resource "aws_api_gateway_resource" "tweet_api_individual_hashtag_end_point" {
//  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
//  parent_id = "${aws_api_gateway_resource.tweet_api_hastags_end_point.id}"
//  path_part = "{hashtag}"
//}
//
//resource "aws_api_gateway_resource" "tweet_api_hastag_average_sentiment_end_point" {
//  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
//  parent_id = "${aws_api_gateway_resource.tweet_api_individual_hashtag_end_point.id}"
//  path_part = "sentiment"
//}
//
//resource "aws_api_gateway_method" "tweet_api_hashtag_average_sentiment_get_end_point" {
//  rest_api_id = "${aws_api_gateway_rest_api.tweet_api.id}"
//  resource_id = "${aws_api_gateway_resource.tweet_api_hastag_average_sentiment_end_point.id}"
//  http_method = "GET"
//  authorization = "NONE"
//
//  request_parameters {
//    "method.request.path.hashtag" = true
//  }
//}