variable "access_key" {}
variable "secret_key" {}

variable "region" {
  default = "eu-west-1"
}
variable "deployment_tag" {
  default = "dev"
}

# Twitter Source Variables
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
variable "twitter_source_jar_path" {
  default = "../Twitter_Producer/target/TwitterProducer-1.0-SNAPSHOT.jar"
}

//TODO: set up EC2 and run the Twitter service and make sure it publishes to Kinesis correctly.