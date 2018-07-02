# Security group to allow all network traffic
resource "aws_security_group" "ec2-all-traffic" {
  name = "Allow all network traffic"

  ingress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags {
    Name = "Allow all network traffic"
    Deployment = "${var.deployment_tag}"
  }
}