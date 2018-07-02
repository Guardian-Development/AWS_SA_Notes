# Reliability Pillar
Ability of a system to recover from service or infrastructure outage, as well as the ability to dynamically acquire computing resources to meet demand. 
## Design Principles
- Test recovery procedures. 
- Automatically recover from failure. 
- Scale horizontally to increase system availability. 
- Stop geussing capacity. 
## Foundations
- Before architecting a system, you need to make sure you have the prerequisite foundations. 
- AWS handles most of this for you, as the cloud is designed to be limitless. AWS handles networking and compute requirements. However, limits are in place to stop customers from over provisioning. 
## Change Management
- Be aware of change so you can proactively plan around it. Monitoring allows you to detect any changes and react. Use CloudWatch to monitor environment and use auto-scaling services to automate change responses in production. 
## Failure Management 
- Architect systems with assumption failure will occur. You should become aware of these failures, how they occured, how to respond and then prevent them from happening again. 
## Key AWS Services
- Foundations: IAM, VPC.
- Change Management: CloudTrail.
- Failure Management: CloudFormation. 