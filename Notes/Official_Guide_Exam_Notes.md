# Official Guide Exam Notes
Notes taken from doing the exam questions within the official study guide.

## Introduction Assessment
- Default security group allows communication between other resources in the group, allows all outbound traffic, and denies everything else. 
- Elastic Map Reduce uses Apache Hadoop as its engine. 
- DynamoDB allows multiple secondary indexes per table, but must be created when the table is created. 
- Data in instance store is lost if instance is stopped or terminated. 

## S3
- Service access logs provide a record of access to any object in S3.
- There is no folder structure in S3, just URLS. 
- Make sure in S3 there is randomness in key space to improve performance. 
- Bucket policies can filter based on IP range, AWS account, and object prefix. 
- Glacier vaults can be locked, data is stored in archives, and it can be used as a standalone service. 

## EC2 and EBS
- Reserved instances can change instance type only within the same family, or change availability zone. 
- Instance stores are low durability, high IOPS storage. 
- Volumes are created immediatly with data lazy loaded. 
- VM Import/Export can end up as EC2 instance or as an AMI. 

## VPC
- Minimum size subnet is /28. 
- A DHCP option on a VPC allows customer to define DNS server. 
- To make an instance daul homed attack an ENI associated with a different subnet. 

## ELB, CloudWatch and Auto-Scaling
- CloudWatch data is kept for 2 weeks. 
- Auto-Scaling adds or terminates instances, launched instances from AMI associated from launch configuration, enforces minimum number of instance running. 
- Auto-Scaling may use on demand and spot instances. 
- ELB health check can be a ping, a connection attempt, or a page that is checked. 
- Auto-Scaling group plans: manual, scheduled, dynamic, maintain. 

## Database 
- To use SQL Server Enterprise, you use the BYOL model. 
- General purpose SSD is built for databases with bursts of traffic. 
- DynamoDB allows single local secondary index, must be created at the time of table creation. Can have many global secondary indexes which can be created after the table is created. 

## SQS, SWF, and SNS
- Maximum time for SQS long polling is 20 seconds. 
- SQS default message retention period is 4 days. 
- To grant another account access to SQS queue, create a SQS policy that grants account access. 

## DNS and Route53
- An AAAA record routes traffic for IPv6. A record is used for IPv4. 
- DNS primary uses UDP to serve requests. 
- If DNS request is over 512 bytes use TCP to serve request. 

## Elasticache 
- Memcached limit nodes to 20, redis is 1 but you can group into replication group. 
- Can backup automatic and manual with redis. Memcached cannot be backed up. 

## Additional Key Services
- Use multiple origins and cache behaviour to solve multiple content locations from CloudFront. 
- Use CloudFront origin access identifier (OAI) to restrict S3 objects to just CloudFront. 
- Customer Managed Keys never leave AWS unencrypted. 
- CloudTrail records API calls, and stores in S3 bucket. 
- You require encryption context and ciphertext unmoidifed to successfully decrypt ciphertext. 
- If audit of AWS environment, use AWS Config. 

## Security on AWS
- 4 tier hierachy on RedShift: master, cipher, DB, encryption keys used. 
- Server order preference determines cipher used for ELB. 
- Workspaces uses PC over IP to provide data security. 
- Instance profile is container for EC2 roles. 

## Risk and Compliance
- AWS provides IT control information to customer through either specific control instructions or general control standard compliance. 
- Energy is not part of the discrete control environment. 

## Architecture Best Practices
- Internet Gateway minimises DDoS attack by reducing number of neccessary internet points, obfuscating neccessary internet points, adds non-critical internet entity points to the architecture.  