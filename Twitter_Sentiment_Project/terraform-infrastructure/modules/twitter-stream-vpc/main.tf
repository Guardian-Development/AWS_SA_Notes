# Create VPC
resource "aws_vpc" "twitter-stream-vpc" {
  cidr_block = "${var.vpc_cidr_block}"

  tags {
    Name = "Twitter-Stream-VPC"
    Deployment = "${var.deployment_tag}"
  }
}

# Create public subnet
resource "aws_subnet" "vpc-public-subnet" {
  cidr_block = "${var.public_subnet_cidr_block}"
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"
  availability_zone = "${var.public_subnet_availability_zone}"

  tags {
    Name = "Twitter-Stream-Public-Subnet"
    Deployment = "${var.deployment_tag}"
  }
}

# Create internet gateway
resource "aws_internet_gateway" "vpc-internet-gateway" {
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"

  tags {
    Name = "Twitter-Stream-Internet-Gateway"
    Deployment = "${var.deployment_tag}"
  }
}

# Create route from internet gateway to public subnet
resource "aws_route_table" "vpc-internet-gateway-route-table" {
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.vpc-internet-gateway.id}"
  }

  tags {
    Name = "Twitter-Stream-Internet-Gateway-Route-Table"
    Deployment = "${var.deployment_tag}"
  }
}

# Creatre association between public subnet and the route table allowing internet access
resource "aws_route_table_association" "vpc-internet-gateway-public-subnet-association" {
  route_table_id = "${aws_route_table.vpc-internet-gateway-route-table.id}"
  subnet_id = "${aws_subnet.vpc-public-subnet.id}"
}

# Create public subnet NACL allowing HTTP/HTTPS/SSH only from public subnet
resource "aws_network_acl" "vpc-private-subnet-nacl" {
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"
  subnet_ids = ["${aws_subnet.vpc-public-subnet.id}"]

  ingress {
    from_port = 80
    protocol = "tcp"
    rule_no = 200
    to_port = 80
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  egress {
    from_port = 80
    protocol = "tcp"
    rule_no = 201
    to_port = 80
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  ingress {
    from_port = 443
    protocol = "tcp"
    rule_no = 202
    to_port = 443
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  egress {
    from_port = 443
    protocol = "tcp"
    rule_no = 203
    to_port = 443
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  ingress {
    from_port = 22
    protocol = "tcp"
    rule_no = 204
    to_port = 22
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  egress {
    from_port = 22
    protocol = "tcp"
    rule_no = 205
    to_port = 22
    cidr_block = "0.0.0.0/0"
    action = "allow"
  }

  tags {
    Name = "Twitter-Stream-Public-Subnet-NACL"
    Deployment = "${var.deployment_tag}"
  }
}

# Create private subnet
resource "aws_subnet" "vpc-private-subnet" {
  cidr_block = "${var.private_subnet_cidr_block}"
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"
  availability_zone = "${var.private_subnet_availability_zone}"

  tags {
    Name = "Twitter-Stream-Private-Subnet"
    Deployment = "${var.deployment_tag}"
  }
}

# Create Elastic IP for the NAT gateway
resource "aws_eip" "vpc-nat-gateway-elastic-ip" {
  vpc = true
  depends_on = ["aws_internet_gateway.vpc-internet-gateway"]

  tags {
    Name = "Twitter-Stream-Nat-Gateway-Elastic-IP"
    Deployment = "${var.deployment_tag}"
  }
}

# Create NAT gateway
resource "aws_nat_gateway" "vpc-nat-gateway" {
  allocation_id = "${aws_eip.vpc-nat-gateway-elastic-ip.id}"
  subnet_id = "${aws_subnet.vpc-public-subnet.id}"
  depends_on = ["aws_internet_gateway.vpc-internet-gateway"]

  tags {
    Name = "Twitter-Stream-Public-Subnet-NAT-Gateway"
    Deployment = "${var.deployment_tag}"
  }
}

# Create route from private subnet to internet via NAT gateway
resource "aws_route_table" "vpc-private-subnet-route-table" {
  vpc_id = "${aws_vpc.twitter-stream-vpc.id}"

  tags {
    Name = "Twitter-Stream-Private-Subnet-Route-Table"
    Deployment = "${var.deployment_tag}"
  }
}

# Creatre association between private subnet and the route table allowing internet access through NAT gateway
resource "aws_route_table_association" "vpc-internet-NAT-gateway-private-subnet-association" {
  route_table_id = "${aws_route_table.vpc-private-subnet-route-table.id}"
  subnet_id = "${aws_subnet.vpc-private-subnet.id}"
}