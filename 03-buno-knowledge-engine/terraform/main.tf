terraform {
 required_version=">= 1.5.0"
 required_providers { aws={source="hashicorp/aws",version="~> 5.0"} }
}
provider "aws" { region=var.region }
variable "region" { type=string; default="us-east-1" }
variable "environment" { type=string; default="dev" }
resource "aws_s3_bucket" "documents" { bucket="buno-knowledge-docs-${var.environment}-replace-me" }
output "documents_bucket" { value=aws_s3_bucket.documents.bucket }
