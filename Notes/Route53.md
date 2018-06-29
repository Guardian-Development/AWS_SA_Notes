# Route53
Route53 is Amazons hosted DNS resolver. You can register and buy domain names from them. This is a global service.
## DNS Information
- Domain Registrars: is an authority that can assign a domain name directly under 1 or more top level domains. Domains are registered with InterNIC a service of ICANN, which enforces uniqeuness of domain names. Each domain then becomes registered in the WhoIS database. 
### SOA Records Store
- Name of service that supplied data for the zone. 
- Admin of zone (information about the person who registered). 
- Current version of data file. 
- Default number of seconds for the time-to-live file on resource records. 
### NS Records
- Name server records used by top level domain services to direct traffic to content DNS server which contains authorative DNS records. 
### A Records 
- A record stands for address, and is used to translate name of domain to an IP address. 
### TTL Records
- Length that a DNS record is cached on resolving server or user PC. Contains time to live in seconds. This is time it takes to propagate a DNS change through the internet. 
### CNAMES
- Canoncial name used to reolve one domain name to another. 
### Alias Records (Amazon Specific)
- Used to map resource record sets in your hosted zone to Elastic Load Balancers, CloudFront or S3 Buckets. 
- Works like CNAME as ca map one DNS name to another DNS name. 
- Key difference is that a CNAME can't be used for a naked domain name (missing www.) it must be a record or alias. 
- Alias can save time as Route53 automatically recognises changes in the record set that the alias refers to. 
- ELB's do not have a pre-defined IPv4 address, you resolve using DNS, which can be a problem is its a naked DNS name. 
- Alias record requests with Route53 are free, CNAMES etc are not. So nearly always want to use them. 
## Simple Routing Policy
- Default when you create a new record set. Most commonly used when you have a single resource that performs a given function. 
## Weighted Routing Policy
- Allows you to split your traffic based on different weights assigned. For instance 10% of traffic go to an ELB in US-EAST-1 with the rest going to EU-WEST-1. 
## Latency Routing Policy
- Route traffic based on the lowest network latency for your end user (which region gives them the fastest response time). 
## Failover Routing Policy
- Used for creating an active/passive setup. Primary site could be in EU-WEST-1 with secondary in EU-EAST-1 as a disaster recovery site. Route53 will use health checks to monitor your sites. 
## Geolocation Routing Policy
- Route traffic based on the location of users. So UK customers are routed to EU-WEST-1 instances for example. 