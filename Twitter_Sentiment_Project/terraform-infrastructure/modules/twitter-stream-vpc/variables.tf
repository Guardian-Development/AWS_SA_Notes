variable "vpc_cidr_block" {
  description = "CIDR for the Twitter Stream VPC"
  default = "10.0.0.0/16"
}

variable "public_subnet_cidr_block" {
  description = "CIDR for the public subnet"
  default = "10.0.1.0/24"
}

variable "public_subnet_availability_zone" {
  description = "Public subnet availability zone to be deployed to"
}

variable "private_subnet_cidr_block" {
  description = "CIDR for the private subnet"
  default = "10.0.2.0/24"
}

variable "private_subnet_availability_zone" {
  description = "Private subnet availability zone to be deployed to"
}

variable "deployment_tag" {
  description = "The deployment tag to associate all resources created in this deployment execution"
}