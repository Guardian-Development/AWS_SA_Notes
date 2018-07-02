# Tweets Stream using Kinesis
resource "aws_kinesis_stream" "tweets-stream" {
  name             = "TweetsStream"
  shard_count      = 1
  retention_period = 24

  tags {
    Name = "Tweet Stream"
    Deployment = "${var.deployment_tag}"
  }
}