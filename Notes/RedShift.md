# RedShift
Is a fast and powerful, fully managed, petabyte scale data warehousing service in the cloud. It costs around a tenth of other data warehousing solutions.
## Date Warehousing General Information
Data warehousing is used for business intelligence, which is pulling in large amounts of complex data. Usually used to perform large queries over the data. 
### OLTP vs OLAP
- Online transaction processing differs from online analytics processing in terms of the type of queries you will run. 
- OLTP is a single transaction on a row, OLAP works over multiple rows and is complex. Therefore, data warehousing databases use different types of architecture, both from a data storage perspecitve and an infrastructure perspective. 
- Relational database = OLTP.
- Data warehousing database: OLAP.
## RedShift Configuration
- Single node (160GB). 
- Multi-node: 
    - Leader node which manages client connections and receives queries. 
    - Compute node which store data and perform queries and computations (up to 128 compute nodes). 
## RedShift Features
- Columnar Data Storage: organises data by columns not rows. This is ideal as often used for queries that aggregate large datasets. As columns procesed and data is stored sequentially on the storage media, this reduces I/O operations and increases performance. 
- Advanced Compression: columnar data stores can be compressed much more efficiently as same data types stored sequentially on disk. RedShift uses multiple compression techniques, sampling data on input to find most appropriate compression. RedShift also reduces storage as it doesn't require indexes or views of the data to be created. 
- Massively Parallel Processing: automatically distributes data and query loads across all nodes. It i easy to add nodes to maintain performance as data sizes increase. 
- Pricing: calculated through compute node hours. You are billed for 1 unit per node, per hour. Only charged for compute nodes and not for leader node. Also charged for backups and data transfer within the VPC. 
- Security: data encrypted in transit through SSL, encrypted at rest using AES-256. RedShift, by default, takes care of key management, however you can manage your own key through HSM. 
- Availability: currently only available in 1 Availability Zone. Cn create snapshots to move to new Availability Zone in the event of an outage.  
