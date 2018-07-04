# Security Token Service (STS)
Grants users limited and tempoary access to AWS resources. 
- Users can come from 3 sources:
    - Federation (Active Directory):
        - Uses security assertion markup language (SAML). 
        - Grants tempoary access based off AD credentials. Doesn't have to be a user in IAM. 
        - Single sign on allows users to log in to AWS console without assigning IAM credential. 
    - Federation with mobile apps: 
        - Use Facebook/Google/OpenID provider to login. 
    - Cross Account Access: 
        - Users from one AWS account access resources in another. 
- Security Token Service gives you 4 things:
    - Access key, secret access key, token and duration. 
## Key Terms
- Federation: combining/joining a list of users in one domain with a list of users in another domain. 
- Idetity Broker: a service that takes an identity from A and joins it to B (federates it). 
- Identity Store: services like Facebook or AD.
- Identities: a user of a service like Facebook or AD.
## Steps 
![alt text](Images/STS_Workflow.png)
- Develop an identity broker to communicate with LDAP and AWS STS. 
- Identity broker authenticates with LDAP then with AWS STS.
- Application then gets temporaty access to AWS resources. 
## Active Directory Integration
- Can sign in with AD using SAML. 
- Authenticate against AD, pass authentication response to AWS SignIn, which then redirects you to logged in AWS console. 