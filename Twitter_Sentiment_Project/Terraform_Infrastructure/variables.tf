# AWS Configuration Variables
variable "access_key" {}
variable "secret_key" {}

variable "region" {
  default = "eu-west-1"
}
variable "deployment_tag" {
  default = "dev"
}

# Twitter Producer Variables
variable "twitter_source_instance_ami" {
  # Amazon Linux AMI 2018.03.0 (HVM), SSD Volume Type
  default = "ami-e4515e0e"
}
variable "twitter_source_instance_type" {
  default = "t2.micro"
}
variable "twitter_source_ec2_key_pair_name" {
  default = "dev-release-key-pair"
}
variable "twitter_source_ec2_key_pair_file_path" {
  default = "/Users/joe.honour/Desktop/TwitterProjectCreds/dev-release-key-pair.pem"
}
variable "twitter_source_jar_path" {
  default = "/Users/joe.honour/AWS_SA_Notes/Twitter_Sentiment_Project/Twitter_Producer/target/TwitterProducer-1.0-SNAPSHOT-jar-with-dependencies.jar"
}

# Kinesis Vairables
variable "kinesis_stream_name" {
  default = "TweetsStream"
}

# Twitter Consumer Variables
variable "twitter_consumer_source_jar_path" {
  default = "/Users/joe.honour/AWS_SA_Notes/Twitter_Sentiment_Project/Twitter_Consumer/target/Twitter_Consumer-1.0-SNAPSHOT.jar"
}
variable "tweets_table_name" {
  default = "Tweets"
}