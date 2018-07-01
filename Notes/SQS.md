# Simple Queue Service (SQS)
Gives you access to a message queue which can be used to store messages while they wait for processing. It is a distributed queue, giving a tempoary repository for messages. This decouples applications so they run independently. 
- Queue acts like a buffer between producers and consumers, allowing it to handle different rates of production and consumption. 
## Types of Queue
2 types of queue.
### Standard
- Nearly an unlimited number of transactions per second. 
- Guarantees messages delivered atleast once. 
- Messages might be delivered out of order and more than once.
- Provides best effort ordering.
### FIFO
- First in First out delivery and exactly once processing. 
- No duplicates. 
- Supports messafe groups that allow multiple ordered message groups within a single queue. 
- FIFO queue's are limited to 300 transactions per second. 
## SQS Key Facts
- Pull based system not pushed based system. 
- Messages can be up to 256kb in size. 
- Messages kept in queue from 1 minute to 14 days. 
- Default retention period is 4 days. 
- SQS guarantees at least once processing.
## Visibility Timeout
- Amount of time SQS message is invisible in the queue after a reader picks it up. 
- If message isn't processed within the timeout, it will appear back in the queue. 
- Default visibility is 30 seconds, maximum is 12 hours. 
- Increase timeout if it takes longer to process than the timeout. 
## SQS Long Polling
- Short polling returns immediatly, long polling only returns when there is a message, or the poll times out. This can save money.