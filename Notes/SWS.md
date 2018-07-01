# Simple Workflow Service (SWS)
Is a web service that coordinates work across distributed application components. Allows applications to be designed as a coordinated set of tasks. 
- Tasks represent invocations of various processing steps in an application. 
- SWF workflows are programs that interact with SWF to get tasks, process received tasks, and returns the results. 
- SWF decider is a program that controls the coordination of tasks (ordering, concurrency, scheduling). 
- SWF brokers the interaction between workers and deciders, giving the decider a consistent view into the progress of tasks and to initiate new tasks. 
- SWF stores tasks, assigns them to workers and deciders, giving the decider a consitent view into the progress of tasks and to initiate new tasks. 
- SWF stores tasks, assign them to workers when they are ready, and monitors their progress. Ensures task is assigned only once and is never duplicated. 
- Since SWF maintains application state, workers and deciders run independently and scale quickly. 
- Worklow AWS activity types and workflow execution itself is all stored in a domain. Domain isolate a set of types, executions, and task lists from others within the same account. 
- Maximum workflow can be 1 year and the value is always measured in seconds. 
## SWF vs SQS
- SWF is a task orientated API, SQS is message orientated. 
- SWF guarantees that a task is assigned only once and is never duplicated. SQS requires the handling of duplicated messages and the chance of processing duplicates. 
- SWF keeps track of all tasks and events, with sqs you need to implement your own application level tracking. 