# CloudWatch
A tool for providing performance metrics of running services. Standard monitoring updates every 5 minutes, with detailed monitoring updating every 1 minute.
- CloudWatch is for monitoring AWS services, CloudTrail is for auditing.
- Metrics available by default on CloudWatch: 
    - CPU related.
    - Disk related.
    - Network related. 
    - Status checks. 
## Features
- Dashboards: see what is happening within AWS environment. 
- Alarms: set alarms when thresholds are hit. 
- Events: help respond to state changes in AWS resources. When resources change state they send an event to an event stream, which can then be pattern matched on actions, or actions can be triggered periodically.
- Logs: help aggregate, monitor and store logs. To get logs, you install an agent on the service, which then sends logs to CloudWatch. 
