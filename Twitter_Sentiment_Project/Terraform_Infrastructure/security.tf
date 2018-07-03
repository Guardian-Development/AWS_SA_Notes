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

# Lambda execution roles to allow talking to Kinesis
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

resource "aws_iam_role" "lambda_execution_role" {
  name = "kinesis_streamer_iam_role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "execute_lambda_policy" {
  role = "${aws_iam_role.lambda_execution_role.id}"
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_iam_role_policy_attachment" "execute_kinesis_policy" {
  role = "${aws_iam_role.lambda_execution_role.id}"
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaKinesisExecutionRole"
}
