# Cross Account Access
If you use seperate AWS accounts, for instance for dev and production, using cross account access you can sign in to the console using your IAM user name, then switch the console to manage another account without having to re-authenticate.
## Steps to Setup
1. Identify account numbers.
2. Create a group in IAM - dev.
3. Create a user in IAM - dev.
4. Log in to production.
5. Create a shared policy. 
6. Create the user account role. 
7. Login to dev account. 
8. Create inline policy. 
9. Apply to developer group. 
10. Login as user. 
11. You can now switch accounts. 