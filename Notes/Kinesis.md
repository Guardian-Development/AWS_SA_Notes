# Kinesis
Kinese is a platform to send your streaming data too. It provides an easy way to load, analyse, and build custom applications around your data. 
- Streaming data is data that is generated continously by thousands of data sources, which typically send data in small amounts. 
## Kinesis Streams 
- Stores data by default for 24 hours, that can be increased to 7 days. The data is stored in shards. 
- Data is then consumed by consumers. 
- Shards: 
    - 5 transactions per second for reads, maximum total data rate of 2MB per second and up to 1000 records per second, up to a maximum write rate of 1MB per second. 
    - The data capacity of your streams is a function of the number of shards you specify for tha stream. Total capacity is the sum of the total shards capacity. 
## Kinesis Firehose 
- Similar to streams, except you don't need to manage shards. It is completely automated, can use AWS Lambda to analyse the data, but then data is sent to be stored in S3 (so no consumers). You therefore don't manage data retention within Kinesis. 
## Kinesis Analytics
- Lets you run SQL queries over the data in Kinesis, then store the result in S3, RedShift, or Elastic Search Cluster. 