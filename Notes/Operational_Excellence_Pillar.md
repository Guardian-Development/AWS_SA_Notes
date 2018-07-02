# Operational Excellence
Operational practices and procedures used to manage production workloads. This includes how planned changes are executed, as well as responding to unexpected operational events. Change execution should be automated, documented, tested and regularily reviewed. 
## Design Principles
- Perform operations with code. 
- Align operation processing with business objectives. 
- Make regular, small, incremental changes. 
- Test for responses to unexpected events. 
- Learn from operational events and failures. 
- Keep operational procedures current. 
## Preparation
- Preparation is required to drive operation excellence.Operation checkpoints ensure that workloads are ready for production. 
- Workloads should have: 
    - Runbooks: operational guides that can be refered to in order to perform daily tasks.  
    - Playbooks: guidance for responding to unexpected operational events (response plans, escalation path, stakeholder notification). 
- To support this: 
    - CloudFormation: ensure environments contain all required resources when deploying to production. This reduces human error between dev, test and prod. 
    - Auto-scaling: automatically respond when business related events effect operational needs. 
    - AWS Config: track and respond to changed in your AWS workflows and environments. 
    - Tagging: allows resources to be easily identified. 
## Operations 
- Should be standardised and managable on a routinly basis. 
- Should rely on automation, small frequent changes, quality assurance and testing, and change review. 
- Changes should not require downtime or manual input. 
- You can setup CI/CD within AWS.
## Responses
- Responses to unexpected events should be automated. 
- Alerts should be quick, with correct escalations in place. 
- Failed deployments should be rolled back. 
## Key AWS Services
- Preparation: AWS Config, Auto-Scaling, SQS.
- Operations: Code Commit, Code Deploy, Code Pipeline, Cloud Trail. 
- Responses: CloudWatch, Alarms, SNS. 