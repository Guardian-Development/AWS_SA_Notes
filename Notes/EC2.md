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
## Raid, Volumes and Snapshots
- RAID: redundant array of independent disks. 
    - RAID 0: striped, no redundancy, good performance. 
    - RAID 1: mirrored, redundancy. 
    - RAID 5: good for reads, bad for writes, not reccommended by AWS on EBS. 
    - RAID 10: striped and mirrored, good for redundancy and performance. 
- To improve I/O you use RAID. 
- To take snapshot of RAID array:
    - Problem is you need to flush the application cache to disk, which you do by stopping the application from writing to disk. 
    - You can do this by freezing the file system, unmounting the RAID array or shutting down the EC2 instance. 
## Encrypt Root Device Volumes and Snapshots
1. Stop the instance.
2. Create snapshot of root volume. 
3. You can then copy the snapshot to an AMI, and there is an option to encrypt the copy. 
4. You can then see the copy as an AMI and use it as the boot device for EC2 instances.
- Encrypted AMI's (at rest) are always private as the key is held witin your AWS account for decryption.
- Snapshots of encrypted volumes are encrypted automatically. 
- Volumes restored from encrypted snapshots are encyrpted automatically. 
## AMI Types
- You can select AMI's based on: 
    - Region.
    - Operating System. 
    - Architecture (32 bit, 64 bit).
    - Launch permissions. 
    - Storage for root device. 
        - Instance store (EPHEMERAL STORAGE). 
        - EBS backed volumes. 
- For EBS volumes: the root device for an instance launched from this AMI is an EBS volume created from an EBS snapshot. 
- For instance store: the root device for an instance launched from this AMI is an instance store volume created from a template held within S3. 
- Instance store volumes cannot be stopped. If underlying host fails, you will loose all your data. 
- EBS backed instances can be stopped. You will not loose data on instance if it is stopped. 
- You can reboot both types of instance. 
- By default, both root volumes are deleted on termination, however EBS volumes you can tell AWS to keep the root device. 
## EC2 Instance Metadata
- To get meta-data on your EC2 instance, from within the instance:
    - curl http://169.254.169.254/latest/meta-data/
- This is RESTful so you can then use the resulting list of properties to make further calls to get the information about the EC2 instance, for instance /public-ip.