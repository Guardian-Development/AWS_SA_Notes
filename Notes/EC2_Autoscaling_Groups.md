# Autoscaling Groups
- Autoscaling Groups allow you to create and manage a set range of EC2 instances. They spread the instances across availability zones and if the instances die they will resurrect them. 
- Autoscaling Groups are given a launch configuration which tells the group what to run in order to create a new instance. 
- The Autoscaling Group can be given alarms, such as CPU usage above 80%, which triggers it to add more instances to the group. This can also be done to remove instanced from the group. 