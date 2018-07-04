# Exam Tips
## Big Data
- Kinesis: used to consume big data. 
- RedShift: used for business intelligence on big data.
- Elastic Map Reduce: used for big data processing. 
## EBS Backed vs Instance Store
- EBS backed volumes are persistent. 
- Instance store backed volumes are not persistent (ephemeral). 
- EBS volumes can be dettached and reattached to other EC2 instances. 
- Instance store volumes cannot be stopped, if they are data is wiped. 
- EBS therefore used for long term data storage. 
## OpsWorks
- Orchestration tool that uses Chef. 
## SWF Actors
- Workflow starters: can initialise a workflow. 
- Deciders: control flow of activity tasks in a workflow execution. If something finishes or fails, deciders decided what to do next. 
- Activity Workers: carry out the activity tasks. 
## EC2 Get Public IP Address
- Query the instance meta-data (not user-data):
    - curl/wget http://169.254.169.254/latest/meta-data   
