# DynamoDB
Fast and flexible NoSQL database servie for all applications that need consistency and low latency at scale. 
## Features
- Supports both document and key-value pair data models. 
- Stored on SSD storage. 
- Spread across 3 geographical distinct data centers. 
- Supports eventually consistent (default) and strongly consistent reads. 
    - Eventual: consistency across all data is usually reached within a second (best read performance). 
    - Strongly: consistent reads return a result that reflects all writes that received a successful response prior to the read. 
- Pricing is done based on provisioned throughput capacity and storage costs. 
- DynamoDB is cheap for reads, but expensive for writes. 