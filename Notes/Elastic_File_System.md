# Elastic File System
Is a file storage system for EC2 instances. Storage capacity is elastic, growing and shrinking automatically as you add and remove files, giving EC2 instances the storage they need. 
## Features
- Supports network file system v4 protocol (NFSv4). 
- Only pay for storage that you use (no pre-provisioning). 
- Can scale up to petabytes. 
- Supports thousands of concurrent NFS connections. 
- Data stored is accessible across multiple availability zones within a region. 
- Read after write consistency. 
- Use as a file server, making it a centralised repository for code/files served by multiple EC2 instances. You can then restrict permissions at a file/directory level to control access across all instances. 
- EBS can only be mounted to one EC2 instance whereas EFS cna be mounted by multiple EC2 instances. 