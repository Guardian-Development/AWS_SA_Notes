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

