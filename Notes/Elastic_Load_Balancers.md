# Elastic Load Balancers 
There are 3 types of load balancer. 
- Application Load Balancers: are best suited for load balancing of HTTP/HTTPS traffic. They operate at layer 7 (application layer) and are application aware. They ae intelligent, and you can create advanced request routing, sending specific requests to specific web servers. 
- Network Load Balancers: but suited for TCP traffic where extreme performance is required. They operate at at the layer 4 (connection layer) and can handle millions of requests per second maintaining low latency. 
- Classic Load Balancers (ELB): legacy balancer. You can use them for HTTP/HTTPS traffic, with layer 7 features such as X-Forwarding and sticky sessions but not as powerful as Application Load Balancers. You can also use at layer 4 purely based on TCP. 
## Load Balancer Errors 
- If an app stops responding a Classic Load Balancer responds with a 504 (gateway timeout), which is caused by not responding within its idle timeout period.
## X-Forwarded-For Header
- Load balancer uses internal IP to speak to downstream services. To keep client/user IP, you store this in the X-Forwarded-For header. 
## Notes on Load Balancers
- Instances monitored by the ELB are reported as InService or OutOfService. 
- Health checks check the instance health by talking to it. 
- ELB have their own DNS name, you are never given an IP address. 