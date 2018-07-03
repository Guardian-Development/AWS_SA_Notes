resource "aws_dynamodb_table" "tweet-database-table" {
  name           = "Tweets"
  read_capacity  = 1
  write_capacity = 1
  hash_key       = "Hashtag"

  attribute {
    name = "Hashtag"
    type = "S"
  }

  tags {
    Name = "Tweets Database Table"
    Deployment = "${var.deployment_tag}"
  }
}