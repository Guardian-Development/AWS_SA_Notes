# AWS Configuration Variables
variable "access_key" {
  description = "The AWS access key"
}

variable "secret_key" {
  description = "The AWS secret key"
}

variable "region" {
  description = "The AWS Region to deploy resources to"
  default     = "eu-west-1"
}

variable "deployment_tag" {
  description = "The deployment tag to associate all resources created in this deployment execution"
  default = "development"
}

# VPC Configuration Variables
variable "public_subnet_availability_zone" {
  default = "eu-west-1a"
}

variable "private_subnet_availability_zone" {
  default = "eu-west-1b"
}