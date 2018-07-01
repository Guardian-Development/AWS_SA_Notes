# Simple Notification Service (SNS)
Web service that makes it easy to setup, operae and send notifications from the cloud. Allows you to publish messages from an application and immediatly deliver them to subscribers of the application.
## Features
- Can deliver messages to mobile devices, SMS Text Messages, SQS, or to any HTTP end point. 
- Can also trigger AWS Lambda functions. Lambda functions get executed being passed the contents of the message. 
- Can group multiple recipients using topics. One topic can support delivery to multiple end point types. e.g. Android and SMS. 
- All messages published to SNS are stored redundantly across multiple availability zones. 
- Mostly used around auto scaling groups, triggering notifications when instances are added or removed etc. 
## Benefits
- Push based delivery system (no polling). 
- Simple APIs and easy to integrate. 
- Flexible message delivery system over multiple transport protocols. 
- Inexpensive, pay-as-you-go model. 
- Has point and click interface through the AWS console. 
## SNS vs SQS
- Both messaging services. 
- SNS push, SQS polling. 