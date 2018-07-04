# VPC Peering
A connection between 2 VPCs that enables you to route traffic between them using private IP addresses. Instances in both VPCs can communicate as if within the same network. 
- Can create VPC peering between your own VPCs, or with a VPC in another AWS account within a single region. 
- VPC peering has no single point of failure for communication or bandwidth bottlenecks. 
- VPC must have a different CIDR block range, or connection with fail (Cannot be matching or overlapping CIDR blocks). 
- Peering is not transitive, if A peers with B and B peers with C, A cannot speak to C. 
- Cannot create VPC peering connection between VPC in different region. 
 