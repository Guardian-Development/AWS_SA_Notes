# Performance Efficiency
Focuses on how to use computing resources efficiently to meet your requirements, and how to maintain that efficiency as demand changes and technologies evolve. 
## Design Principles 
- Democratise advanced technologies (technologies as a service). 
- Go global in minutes. 
- Use serverless architectures. 
- Experiment more often. 
## Compute
- Use the right kind of server (Heavy CPU, high I/O etc).
- You can also switch to serverless, along with upgrading instances at a click of a button. 
## Storage
- Optimal storage for your environment depends on a number of factors: 
    - Access method (block, file, object).
    - Pattern of access (random, sequential). 
    - Throughput requirements. 
    - Frequency of access. 
    - Frequency of update. 
    - Availability/Durability contraints. 
- As data storage is virtualised, can easily change as requirements change, such as dev, test, prod environments. 
## Databases 
- Depends on a number of factors: do you need consistency, high availability, NoSQL, disaster recovery ? 
## Space-Time Trade Off
- Can add read replicas to reduce load, and lower latency. 
- Direct connect to provide predictable latency between your HQ and AWS.
- Can have global infrastructure, and use caching systems to reduce latency. 
## Key Services
- Compute: autoscaling. 
- Storage: EBS, S3, Glacier. 
- Databases: RDS, DynamoDB, Glacier. 
- Space-time trade off: CloudFront, Elasticache, Direct Connect, RDS read replicas, etc. 