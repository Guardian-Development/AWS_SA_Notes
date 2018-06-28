# Serverless Website Example
Hosting a website using serverless technology. This should make a requests to Route53 to get the DNS name, which will then be a website hosted in S3. This website will then call API Gateway, which in turn calls Lmabda, in order to return some dynamic content for the website. 

## Steps
1. Create a bucket in S3 (if you want to use Route53 this bucket name must be the same as the DNS name entry for instance staticwebsitetest.com). 
2. Enable static website hosting within the bucket, using index.html and error.html as the appropriate documents. 
3. (Optional) If you want to use Route53 go over to the service and register your domain name, paying the fee for the DNS registration. If you don't use this, just use the S3 URL for all future steps.
4. Go over to Lambda and create the Lambda function using the Author from scratch option. Give your Lambda function an appropriate name, and use Python 3.6 as the language. Create a new role from template, using the policy template Simple Microservice Permissions. Open your Lambda function source code, and copy the Lambda.py contents into the Lambda function. 
5. Add a trigger to the function, selecting API Gateway as the source.
6. Click create new API Gateway. 
7. Give the API Gateway a suitable name, with open security (remember to delete this once you are done). 
8. Open the API Gateway you created in AWS console. 
9. Delete the existing end point created by Lambda. Create a new GET end point, connecting it to Lambda function. Use Lambda proxy integration, and select the Lambda function you created. Click save. 
10. Deploy the API, and take note of the endpoint invocation URL.
11. Open up index.html locally. Replace the API_GATEWAY_URL with the invocation URL for the GET endpoint in the javascript function. 
12. Upload your index.html and error.html to your S3 bucket, and make them public files. 
13. If using Route53, go to your DNS entry. Click create record set, with Alias set to true. Set the Alias target to be your S3 bucket. 
14. It should all work now if you visit Route53 DNS name, or the S3 bucket name if you're not using it. 

