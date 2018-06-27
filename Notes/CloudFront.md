# CloudFront CDN
Content delivery network (CDN) is a system of distributed services hat deliver web pages/content to a user based on the geographical location of the user, the origin of the content and the content delivery service. 

CloudFront is used to deliver your entire website (including streaming and interactive content) using a global network of edge locations. Requests are automatically routed to the nearest edge location. This works well with other AWS services but can also be used with a non-AWS server as the source of the content. 
## Key Terms
- Edge locations: location where content will be cached, seperate to regions and availability zones. These have both read and write capabilities with CloudFront. 
- TTL (Time to Live): objects are cached for a set amount of time known as the TTL. You can clear cached items, but this is a charged service. 
- Origin: origin of files the CDN will distribute. This can be an S3 bucket, an EC2 instance, an Elastic Load Balancer or Route 53. 
- Distribution: name given to the CDN which consists of a collection of edge locations. 2 types of distribution: 
    - Web Distribution: typically used for websites. 
    - RTMP: used for media streaming. 
## Notes on Creating CloudFront Distributions
- Restrict access to CloudFront distibutions using signed URL's or signed cookies. 
- Can use an origin path to limit the CloudFront source of distribution to a sub folder within an S3 bucket. 
- Can add web application firewalls (WAF) to CloudFront if desired. 
- Can use alternate domain names to route to CloudFront instance. 
- Once created, use CloudFront DNS name to access files, rather than directly accessing the S3 bucket or source. 
- Can restrict content to only certain countries, which can be done by whitelisting or blacklisting countries. 