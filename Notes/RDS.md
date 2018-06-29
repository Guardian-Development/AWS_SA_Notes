# Relational Database Service
RDS is a service that hosts different relational database engines.

## Notes on RDS
- When you create an RDS instance it creates its own security group. If you need other services to speak to your RDS instance you need to go in to the security group for your RDS instance and allow traffic from the security group containing the instance that wishes to speak to RDS on the correct port (3306).
## Backups
2 types of backups, Automated Backups and Database Snapshots. 
### Automated Backups
- Allow you to recover your database to any point in time within a retention period. Retention period can be between 1 and 35 days. 
- Automated Backups take a full daily snapshot and store transactional logs throughout the day. To recover, AWS picks latest backup and then applies transactional log to allow you to recover a database to the second. 
- Automated backups are enabled by default, with storage within S3 free up to the same size of the database instance. 
- During backup window storage I/O may be suspended and you may experience elevated latency. 
### Snapshots 
- Are done manually and are stored even after the original RDS instance is deleted, unlike Automated Backups. 
- Whenever you restore an automatic or manual backup the restored version will be a new RDS instance with a new domain name. 
## Encryption 
- Encryption at rest is done using the AWS Key Management Service (KMS). Once complete, data is encrypted along with automated backups, read replicas and snapshots. 
- You can't currently encrypt existing running RDS instances. Instead take a snapshot, make a copy, and then encrypt the copy. 
## Multi-AZ
- Allows you to have exact copy of production database in another Availability Zone. It handles replication for you, along with automatic synchronisation. 
- If production database becomes unavailable, AWS automatically switches over to the backup instance to allow a continued service. 
- This is why you always work with the DNS name of the instance in RDS, not the IP address. 
## Read Replicas 
- Allow you to have a read only copy of your production database. This is done through asynchronous replication from primary RDS instance to replicas. This should be used for read heavy database workloads. 
- Used for scaling, not disaster recovery. 
- Must have automatic backups turned on. 
- Can have up to 5 read replica copies of any database. 
- Each replica has its own DNS end point. 
- Read replicas can have Multi-AZ. Can create read replicas of Multi-AZ source databases. 
- Read replicas can be promoted to their own database, but this stops the replication process. 
- You can have read replicas in a second region. 
- Can do read replicas of read replicas but beware of the latency. 