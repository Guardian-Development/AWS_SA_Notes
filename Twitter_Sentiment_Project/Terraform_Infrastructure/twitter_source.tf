# Twitter Source EC2 instance
resource "aws_instance" "twitter-source" {
  ami = "${var.twitter_source_instance_ami}"
  instance_type = "${var.twitter_source_instance_type}"
  vpc_security_group_ids = ["${aws_security_group.ec2-all-traffic.id}"]
  key_name = "${var.twitter_source_ec2_key_pair_name}"

  tags {
    Name = "Twitter Source"
    Deployment = "${var.deployment_tag}"
  }
}