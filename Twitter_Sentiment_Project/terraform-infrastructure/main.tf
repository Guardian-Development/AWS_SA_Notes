provider "aws" {
  access_key = "${var.access_key}"
  secret_key = "${var.secret_key}"
  region     = "${var.region}"
}

# Create VPC
module "twitter-stream-vpc" {
  source = "./modules/twitter-stream-vpc"
  deployment_tag = "${var.deployment_tag}"
  public_subnet_availability_zone = "${var.public_subnet_availability_zone}"
  private_subnet_availability_zone = "${var.private_subnet_availability_zone}"
}
