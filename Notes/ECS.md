# Elastic Container Service (ECS)
ECS is a highly scalable container management service. Makes it easy to run, stop and manage docker containers on a cluster of EC2 instances. ECS lets you launch and stop container-based applications with API calls, while allowing you to query cluster state. 
- ecs is a regional service that you use in one or more AZs. 
- Removes need to manage own cluster or worry about scalaing. 
- Create consistent deployment and build experience. 
- Images can be stored in a registry, such as DockerHub or Amazon ECR. 
    - ECR is a container registry that is secure, scalable and reliable. Can use IAM to restrict access to images and repositories. 
## Docker
- Docker packages software in units called containers. This allows you to easily package application code, configurations and dependencies, giving environment consistency. 
- Deliver highly reliable, infinetly scalable solutions. 
- Containerisation benefits:
    - Escape from dependency hell.
    - Consistent progression between environments (dev, test, prod). 
    - Isolation: app A issues do not effect app B. 
    - Much better resource management. 
    - Extreme code portability. 
## ECS Task Definitions 
- Required to run Docker containers in ECS.
- These are JSON files that describe one or more containers that make-up an application. 
- Parameters you can specify include: 
    - Which Docker image to use, CPU allocation, whether containers are linked together, networking mode of containers, port mappings, pass environment variables, IAM roles for permissions etc. 
- ECS lets you run a specified number of instances of a task definition simultaneously in an ECS cluster. 
- Basically auto-scaling groups for ECS. 
## ECS Clusters
Logical grouping of container instances that you can place tasks on. You can create multiple clusters to keep your resources seperate. 
### Concepts
- Can contain multiple different containers instance types.
- Clusters are region specific. 
- Container instances can only be part of one cluster at a time. 
- Use IAM plicies to restrict users access to specific clusters. 
## ECS Scheduling
- Service scheduler: ensures specified number of tasks are always running and reschedules tasks that fail. Can also ensure tasks are registered against an ELB.
- Custom scheduler: can create own scheduler. Leverage 3rd party schedulers such as Blux, 
## ECS Container Agent 
- Allows container instances to connect to cluster. You can install it on any EC2 instance that supports the ECS specification (only supported on EC2 instances). 
- Linux based, will not work on Windows. 
## ECS Security 
- IAM Roles:
    - EC2 instances use an IAM role to access ECS. 
    - EC2 tasks use an IAM role to access services/resources. 
- Security groups attach at the instance level, not the container or task level. 
- You can access OS of EC2 instances in your ECS cluster. 
## ECS Limits
- Soft limits: 
    - Clusters per region = 1000. 
    - Instances per cluster = 1000. 
    - Services per cluster = 1000. 
- Hard limits: 
    - One load balancer per service. 
    - 1000 tasks per service. 
    - Max 10 containers per task definition. 
    - Max 10 tasks per instance. 