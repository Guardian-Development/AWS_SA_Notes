# Aurora
Is a MySQL compatible, relational database engine that combines speed and availability of high end commercial databases with the cost effectiveness of open source databases. It provides up to 5 times better performance than MySQL, costing a tenth of the price when delivering similar performance and avabilability. 
## Scaling
- Start with 10GB memory, auto scaling in 10GB increments.
- Compute resurces can scale up to 32 CPUs and 244GB of RAM.
- 2 copies of your data contained in each Availability Zone, with mimimum of 3 AZ (giving 6 copies of your data). 
- Can transparently handle the loss of up to 2 copies of data without effecting the database write availability, and up to 3 copies of the data without affecting database read availability. 
- Storage is self healing, data blocks are continously scanned for errors and repaired automatically. 
## Replicas 
2 types are made available. 
- Aurora replicas (up to 15): if database fails this will automatically fall over to a replica. 
- MySQL read replicase (up to 5): failure won't automatically fall over to replica. 