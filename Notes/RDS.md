# Relational Database Service
RDS is a service that hosts different relational database engines.

## Notes on RDS
- When you create an RDS instance it creates its own security group. If you need other services to speak to your RDS instance you need to go in to the security group for your RDS instance and allow traffic from the security group containing the instance that wishes to speak to RDS on the correct port (3306).