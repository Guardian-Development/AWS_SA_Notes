# Lambda 
Compute service where you can upload your code and create a Lambda function. Lambda takes care of provisioning and managing the servers that run your code. You can use Lambda for: 
- Event driven compute service: where code is run in response to events. 
- As a compute service: to run your code in response to HTTP requests using API Gateway or API calls with the AWS SDK. 
## Supported Languages
- C#.
- Go.
- Java.
- Node.js.
- Python. 
## Triggers 
- API Gateway.
- AWS IoT.
- Alexa Skills Kit. 
- Alexa Smart Home. 
- CloudFront. 
- CloudWatch Events, Logs. 
- Code Commit. 
- Cognito Sync Trigger.
- DynamoDB. 
- Kinesis. 
- S3.
- SNS.
## Lambda Notes
- Priced based on number of requests, with first 1 million being free. Also charged on invocation time to nearest 100Mms, with price dependent on the amount of memory allocated. 
- Functions can't execute for over 5 minutes.
- No servers. 
- Continous scaling (out not up) automatically. 
- Super cheap. 
- Lambda functions are independent. 
- Architectures can get very complicated, AWS X-Ray allows debugging. 
- Lambda functions can do things globally. 