# Twitter Source EC2 instance
resource "aws_instance" "twitter-source" {
  ami = "${var.twitter_source_instance_ami}"
  instance_type = "${var.twitter_source_instance_type}"
  vpc_security_group_ids = [
    "${aws_security_group.ec2-inbound-traffic.id}",
    "${aws_security_group.ec2-outgoing-traffic.id}"]
  key_name = "${var.twitter_source_ec2_key_pair_name}"

  tags {
    Name = "Twitter Source"
    Deployment = "${var.deployment_tag}"
  }
}

resource "null_resource" "twitter-source-configure" {

  triggers {
    twitter_source = "${aws_instance.twitter-source.id}"
  }

  provisioner "file" {
    source      = "${var.twitter_source_jar_path}"
    destination = "./TwitterProducer.jar"

    connection {
      type = "ssh"
      timeout = "2m"
      user = "ec2-user"
      private_key = "${file(var.twitter_source_ec2_key_pair_file_path)}"
      host = "${aws_instance.twitter-source.public_ip}"
    }
  }

  provisioner "remote-exec" {
    inline = [
      "sudo yum install -y java-1.8.0",
      "sudo yum remove -y java-1.7.0-openjdk",
      "export awsKinesisAccessKey=${var.access_key}",
      "export awsKinesisSecretKey=${var.secret_key}",
      "export awsKinesisRegion=${var.region}",
      "export awsKinesisStreamName=${var.kinesis_stream_name}",
      "nohup java -jar TwitterProducer.jar > twitterProducer.logs &",
      "sleep 10s",
      "echo 'Running Twitter Producer'"
    ]

    connection {
      type = "ssh"
      timeout = "2m"
      user = "ec2-user"
      private_key = "${file(var.twitter_source_ec2_key_pair_file_path)}"
      host = "${aws_instance.twitter-source.public_ip}"
    }
  }
}