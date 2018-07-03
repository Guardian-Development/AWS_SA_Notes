# Security group to allow all network traffic
resource "aws_security_group" "ec2-inbound-traffic" {
  name = "Allow all network traffic inbound"

  ingress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags {
    Name = "Allow all network traffic inbound"
    Deployment = "${var.deployment_tag}"
  }
}

resource "aws_security_group" "ec2-outgoing-traffic" {
  name = "Allow all network traffic outbound"

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags {
    Name = "Allow all network outbound"
    Deployment = "${var.deployment_tag}"
  }
}
