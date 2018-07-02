# AWS Best Practices
AWS best practices offer many benefits. 
## Business Benefits 
- Almost zero upfront costs. 
- Just in time infrastructure. 
- More efficient resource utilisation. 
- Usage based costing. 
- Reduced time to market. 
## Technical Benefits
- Automation through scriptable infrastructure. 
- Auto-scaling.
- Proactive scaling. 
- More efficient development lifecycle.
- Improved testability.
- Disaster recovery and business continuity. 
- Overflow traffic from local hardware to the cloud. 
## Design for Failure
- Be a pessimist when designing architectures in the cloud. Assume things will fail. Always design, implement, and deploy for automated recovery from failure. 
## Decouple Your Components
- Build components that do not have tight dependencies on each other, so if a component were to die, sleep, or remain busy, other components would continue to work. 
## Implement Elasticity 
- Proactive cyclic scaling: periodic scaling that occurs at fixed intervals (daily, weekly. monthly, etc).
- Proactive event based scaling: scaling just when you expect a big surge of traffic, due to a business event (such a product launch).
- Auto-scaling based on demand: monitoring services used to send triggers to take actions to scale up or down based on metrics. 
## Secure Applications
- Allow web traffic to web servers only. 
- Only allow SSH to app servers, with just web servers able to hit app servers internally. 
- Only permit app layer to speak to database.