variable "environment" {
  description = "This is the tag used to describe the environment"
  default = "dev"
}

variable "tag" {
  description = "The base tag used to tag AWS resources"
  default = "location"
}

variable "dockerImageUrl" {
  description = "This is the docker image to deploy to your ECS cluster"
  default = "chuckh/location"
}

variable "containerPort" {
  description = "This is port for your docker container running on your ECS cluster"
  default = "8080"
}

variable "aws_region" {
  description = "The AWS region to create things in."
  default     = "us-east-1"
}

variable "az_count" {
  description = "Number of AZs to cover in a given AWS region"
  default     = "2"
}

variable "key_name" {
  description = "Name of AWS key pair"
  default = "docker-examples"
}

variable "instance_type" {
  description = "AWS instance type"
  default     = "t2.micro"
}

variable "asg_min" {
  description = "Min numbers of servers in ASG"
  default     = "1"
}

variable "asg_max" {
  description = "Max numbers of servers in ASG"
  default     = "2"
}

variable "asg_desired" {
  description = "Desired numbers of servers in ASG"
  default     = "1"
}

variable "admin_cidr_ingress" {
  description = "CIDR to allow tcp/22 ingress to EC2 instance"
  default = "10.0.0.0/28"
}
