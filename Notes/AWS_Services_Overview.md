# AWS Services Overview 
Overview of AWS and the services it offers. Sections needed for the Solutions Associate exam are marked with a tick. 

## AWS Infrastructure 
- [x]
- A region: is a geographical area, consisting of 2 or more availability zones. 
- An availability zone: is simple 1 or more data centre's, that have seperate power, networking, etc so they fail independently. 
- Edge locations: are endpoints used for caching content, typically consisting of CloudFront and Amazon Content Delivery services. For instance, a response to a request from Sydney to London can be cached at an edge location in Australia in order to reduce the distance of further calls. 

## AWS Services 
The currently available AWS services. 
### Compute
- [x]
- EC2 (Elastic Compute Cloud): essentially virtual machines for rent. 
- EC2 (Elastic Container Service): manage docker containers at scale. 
- Elastic Beanstalk: upload code and then provisions everything you need to run that code. 
- Lambda: code executed on demand, usually through a trigger. 
- LightSail: virtual private service, service. Provides you a server with a static IP, that you can then SSH into and configure. 
- Batch: used for batch computing. 
### Storage 
- [x]
- S3 (Simple Storage Service): object based storage in buckets. 
- EFS (Elastic File System): network attatched storage.
- Glacier: data archiving. 
- Snowball: way of physically sending large amounts of data to AWS. 
- Storage Gateway: virtual machine installed onsite that can replicate information back to S3. 
### Databases
- [x]
- RDS (Relational Database Service): MySQL, PostGres etc as a service. 
- DynamoDB: NoSQL based storage. 
- Elasticache: caching common query results from a database to reduce load. 
- Redshift: data wearhousing and business intelligence. 
### Migration 
- [x]
- Migration Hub: tracking services as you migrate to AWS. 
- Application Discovery Service: detects applications and their dependencies within AWS, allowing tracking of dependencies to then occur. 
- Database Migration Service: migrate from onsite into AWS.
- Server Migration Service: migrate physical/virtual servers from onsite to AWS.
### Networking and Content Delivery
- [x]
- VPC (Virtual Private Cloud): a virtual data center you can setup in AWS.
- CloudFront: content delivery network, media assets can then be stored closer to location of users.
- Route53: DNS service. 
- API Gateway: creating a common API for services to talk to. 
- Direct Connect: run dedicated line from corporate head office to a VPC witin AWS.
### Developer Tools 
- Code Star: project managing your code and provides collaboration. 
- Code Commit: source control. 
- Code Build: compile, test and produce code packages for distribution. 
- Code Deploy: automate application deployment to onsite, EC2 and Lambda services. 
- Code Pipeline: continous delivery service. 
- X-Ray: debug and analyse serverless applications. 
- Cloud9: IDE environment hosted within AWS. 
### Management Tools
- [x]
- Cloud Watch: monitoring service. 
- Cloud Formation: scripting infrastructure. 
- Cloud Trail: logging calls made within the AWS environment. 
- Config: monitors and snapshots entire AWS environment. 
- OpsWorks: Chef and Puppet integration for automation of environment. 
- Service Catalog: managing IT services approved for use within AWS.
- Systems Manager: allow the roll out of security patches to all instances etc. 
- Trusted Advisor: advice around AWS configuration including security, workload, use cases etc. 
- Managed Services: helps with auto-scaling etc of services. 
### Media Services
- Elastic Transcoder: takes video etc and transforms it to look good on different platforms/devices e.g. Android/iOS and tablet. 
- Multiple media services: provide other services to support video streaming at scale, along with targetted advertising and optimised storage to reduce latency. 
### Machine Learning 
- Sage Maker: allows for the running of deep learning based models. 
- Comprehend: sentiment analysis. 
- DeepLens: Artifically aware camera (with on camera compute power). 
- Lex: Amazon Alexa, chatting with customers etc. 
- Machine Learning: dataset analysis including things like recommendation engines etc. 
- Polly: takes text and transforms it to speech. 
- Recognition: image and object detection with classification. 
- Amazon Translate: Google translate equivalent. 
- Amazon Transcribe: automatic speech recognition, turning it into text. 
### Analytics 
- [x]
- Athena: SQL Queries against items held within S3. 
- EMR (Elastic Map Reduce): processing large amounts of data. 
- Cloud Search and Elastic Search Service: provide searching at scale. 
- Kinesis: ingesting large amounts of data into AWS. 
- Kinesis Video Streams: ingest large amounts of video. 
- Quicksight: buisness intelligence tool. 
- Data Pipeline: moving data between AWS services. 
- Glue: extract/transform/load data migration tool. 
### Security, Identity and Compliance 
- [x]
- IAM: Identity Access Management. 
- Cognito: device authentication for mobile apps. 
- Guard Duty: monitors for malicious activities throughout all AWS services. 
- Inspector: install on EC2, tests for security vulnerabilities and generates a report. 
- Macie: scan S3 for peronally identifiable information. 
- Certificate Manager: SSL certificates for free if using Route53. 
- Cloud HSM (Hardware Security Module): dedicated hardware storage for cryptographic keys. 
- Directory Service: integrate Active Directory into AWS. 
- WAF (Web Application Firewall): guards against SQL injection, XSS, etc provides firewall service. 
- Shield: DDOS mitigation and protection. 
- Artifact: download AWS compliant reports for auditing. 
### Mobile 
- Mobile HUB: management for mobile services. 
- Pinpoint: targetted push notifications to drive user engagement. 
- AWS App Sync: updates data within web/mobile app in real-time. 
- Device Farm: tests apps on real devices. 
- Mobile Analytics: analytics for apps. 
### Augmented/Virtual Reality 
- Sumerian: common tools for developing, bulding and designing of AR/VR environments. 
### Application Integration
- [x]
- Step Functions: managing Lambda functions. 
- AmazonMQ: message queue. 
- SNS (Simple Notification Service): notification service such as billing notification within AWS.
- SQS (Simple Queue Service): decoupling infrastructure, hold message in queue until it is consumed and processed succesfully. 
- SWF (Simple Workflow Service): allows workflows for items, including human resources, to be created and designed. 
### Customer Engagement
- Connect: call centre as a service. 
- Simple Email Service: sending large amounts of emails. 
### Business Productivity 
- Alexa for Business: dial into meeting rooms, re-order printer ink etc.
- Chime: video conferencing tool, like Google Hangouts. 
- Work Docs: dropbox for AWS.
- Work Mail: Office 365 style for email.
### Desktop and App Streaming
- [x]
- Workspaces: VDI solution, VM in cloud streamed to device. 
- Appstream 2.0: Stream actual application to device rather than entire VDI image. 
### Internet of Things
- IOT Device Management: help with IOT based streaming. 
- Amazon FreeRTOS: operating system for micro-controllers. 
- Green Grass: runs local compute services in a secure way. 
### Game Development 
- Game Lift: help develop games in the cloud. 