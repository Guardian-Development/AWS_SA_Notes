# Storage Gateway
Connects an on-premises software applicance with cloud based storage to provide integration between on-premises IT environment and AWS storage infrastructure. This is downloaded as a VM image that you install in your data center (VMWare, HyperV support). Once registered, you can then use the console to create the type of Storgae Gateway you require for your use case. 
## Types of Gateway 
There are 4 types of Storage Gateway.
### File Gateway
- Files stored as objected in S3 bucket, accessed through network file system mount point. Once in S3, the objects are treated as normal S3 objects and can be managed as such. 
### Volume Gateway
- Presents your applications with disk volumes using iSCSI block protocol (VMI storage). 
- Data written to these volumes can be asynchronously backed up at point-in-time snapshots of your volumes, and stored as Amazon EBS snapshots. 
- Snapshots are incremental and capture just the blocks which have changed since the previous version. Snapshots are then compressed to reduce the cost of storage.
#### Stored Volumes 
- Store is primarily held on-premises, with asynchronous backups happening to AWS. 
- Provides low-latency access as primary held on-premises, while providing durable off-site backups. 
- Stored volumes can be between 1GB and 16TB. 
#### Cached Volumes 
- Use S3 as primary data storage, while retaining frequently accessed data locally in Storage Gateway. 
- Minimises local storage requirements, while providing low-latency access to frequently accessed data. 
- Volums storage on S3 with snapshots also. 
- Cached volumes can be between 1GB and 32TB.
### Tape Gateway 
- Archive data in AWS. Create virtual tape catridges and store them in AWS using existing backup tape architecture and services.