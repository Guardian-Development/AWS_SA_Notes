# IAM (Identity Access Management)
Manager users and their levels of access to the AWS console. 
## Features
- Centralised control of your AWS account. 
- Shared access to AWS account. 
- Granular permissions. 
- Identity Federation (Facebook, Active Directory, Twitter etc).
- Multi-factor authentication. 
- Provide tempoary access for users/devices and services. 
- Set up own password rotation policy. 
- Integrates with many different AWS services.
- Supports PCI, DSS compliance. 
## Critical Terms 
- Users: end users. 
- Groups: collection of users under 1 set of permissions. 
- Roles: create roles and assign them to resources, such as EC2 given a role to write directly to S3. 
- Policies: a decoment that defines 1 or more permission policies. These are assigned to users, groups and roles. They come in the form of a policy document written in JSON. 
## Key Points
- When creating a user you will be given an access key and secret key. This can only be used for programmatic access to AWS (command line, SDK, etc), you must use the user password and username to login to the console. You can only view the credentials once, though new credentials can be regenerated for a user. 
- New users have no permissions when initially created. 
- You can add permissions directly to users, without using groups, if desired. 
- IAM exists outside of regions. 
- Root account is account used when initially setting up the AWS account. You should always setup multi-factor authentication on your root account.
- Power User Access: gives access to all AWS services except for managements of groups and users within IAM. 
## IAM Roles
- All roles are global and cannot be created for a specific region. 
- AWS roles are associated to EC2/resource which means you don't need to login with user credentials to perform actions. The instance itself has its list of priveledges. 
- Roles are powerful as they permission the resource not the user, and are therefore isolated if a resource is compromised. 
- You can attach a role to an EC2 instance while it is running, through the command line or in the console (this use to not be the case).

