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
## Security Groups
- Security groups are virtual firewalls.
- 1 EC2 instance can have multiple security groups assigned to it.
- Inbound Rules: applied to packets going in to the EC2 instance.
- Outbound Rules: applied to packets leaving the EC2 instance.
- Any change to a security group is applied immediatly.
- Security groups are stateful: anything you allow inbound will be automatically allowed outbound. Allow input traffic for TCP on port 80, automatically allows outbound TCP traffic on port 80. 
- Default security group allows all traffic on all ports to any other instance within the security group. 
- All inbound traffic is blocked by default.
- All outbound traffic is allowed by default. 
- You can have any number of EC2 instances within a Security Group. 
- You can only specify allow rules, you cannot specify deny rules.
## Upgrading EBS Volumes 
- EBS volumes have to be in the same availability zone as the EC2 instance that mounts them. 
- You can modify a volume of any type other than magnetic storage. 
- To move a volume to another availablity zone: 
    1. Take a snapshot of the volume.
    2. Go to snapshots and select it. 
    3. Actions -> Create Volume. Then you can change the volume type and its availability zone. 
- You can copy snapshots to other regions. From a snapshot, you can then create another imgae, which provides an EBS and creates an AMI, which can then be launched. 
- An AMI can then be moved between regions. 
- Volumes exist on EBS and are just a virtual hard disk. 
- Snapshots exist in S3, and are a point-in-time copy of the volume. 
- Snapshots are incremental and only record the block that have changed since the previous snapshot. 
- You can create AMI's and snapshhots while the original image is still running.
- You can change the EBS volume size and type on the fly. 
- Snapshots of encrypted volumes are encrypted automatically. 
- Can only share snapshots with others publicly if they are not encrypted. 