# AWS Organisations
Is an account management service that enables you to consolidate multiple AWS accounts into an organisation, which can then be centraly managed. 
## Consolidated Billing
- Setup a paying account, which cannot access resources of other accounts. 
- All linked accounts are independent (e.g. dev, test, prod account).
- Then get 1 monthly bill, which incorporates all linked accounts and shows breakdown of spending. 
## Advantages 
- One bill per account. 
- Easy to track charges and account costs. 
- Volume pricing discount. 
## Best Practices 
- Always enable multi-factor authentication on the root account. 
- Always use a strong password on the root account.   
- Paying account used for billing purposes only. 
## Thing to Note
- Only allowed to link up to 20 accounts (can raise by speaking to AWS). 
- Billing alarms can be at a root account level and an individual account level. 
- CloudTrail (auditing): is only per AWS account and region. Can consolidate logs using S3 bucket though. Setup S3 bucket CloudTrail in root account, then setup CloudTrail in linked accounts, pointing the output of the linked accounts to the root account bucket.  
## Exam Tips
- Consolidate billing across all accounts, giving you volume discounts on all your accounts. 
- Unused reserved EC2 instances can be used by other linked accounts within the organisation.  