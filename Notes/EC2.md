# EC2 (Elastic Cloud Compute)
Amaon Elastic Cloud Compute is a web service that provides resizable compute capacity in the cloud. Allows quick scaling, both up and down, as compute requirements change. Allows a user to pay for only the capacity they use. Allows development of failure resilient applications, isolated from common failure scenarios. 
## EC2 Options
- On Demand: pay per hour or second with no commitment. 
- Reserved: capacity reservation, with an upfront cost/commitment reducing overall cost (1-3 year terms). 
- Spot: bid price for instance capacity. If spot instance is terminated by AWS, you are not charged for the partial hour of usage. If you terminate the instance, you are charged for the full hour of use.
- Dedicated Hosts: physical EC2 server for you to use. Allows the use of existing server software licenses, such as HyperV.
### Types 
- F1: field programmable gate array. 
- I3: high speed storage. 
- G3: graphics intensive. 
- H1: high disk throughput. 
- T2: lowest cost, general purpose.
- D2: dense storage. 
- R4: memory optimised. 
- M5: general purpose.
- C5: compute optimised. 
- P3: graphics/general purpose GPU.
- X1: memory optimised.
## EBS (Elastic Block Storage)
Creates storage volumes and attach them to EC2 instances. Once attatched, can create file system, database, or use in any other way. Placed in a specific availability zone, where they are automatically replicated to protect you from failure of a single component. You can attach multiple drives to EC2 (with a root drive being used to run the OS). 
### Types
- General purpose SSD (GP2): upto 10,000 IOPS. General purpose for mix of price and performance optimisation. 
- Provisioned IOPS SSD (IO1): I/O intensive operations, such as a database, use if you need more than 10,000 IPOS with up to 20,000 IOPS supported. 
- Throughput Optimised HDD (ST1): used for Big Data, Data Warehousing, Log Processing. This cannot be used as a boot volume. 
- Cold HDD (SC1): lowest cost storage for infrequent access workloads e.g. file server. This cannot be used as a boot volume.
- Magnetic (Standard): lowest cost per gigabyte that is bootable. Good for infrequent access, or where low storage cost is important. 
## Notes for when Creating EC2 Instances
- AMI: amazon machine imahe. 
- 1 subnet can only span 1 availability zone. 
- EBS is deleted when its attached EC2 instance in temrinated by default. 
- A volume is a virtual hard disk, with root being the bootable device.
- A security group is a virtual firewall.
- There are 2 types of runtime checks that can be performed on an EC2 instance: 
    1. System Status Checks: checks instance is reachable by checking network packets are able to contact instance. 
    2. Instance Status Checks: checks that it can get traffic to the instance operating system. 
- Can't encrypt the default root device volumes provided by Amazon. Can use 3rd party software, or create your own AMI. Additional volumes can be encrypted.
- Termination protection is turned off by default. 