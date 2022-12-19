# Dining Philosophers Problem
### Money Transfer System

## 1. Overall Description
When a client performs a transfer, they should choose the account they want to send the selected amount of money they want to transfer. If another client wants to send money to any of those accounts, they must wait until the other accounts have completed the transaction in order for the transfer to occur in the correct order without being intercepted.


#### 1.1 Assumptions and Dependencies
this system attempts to address issues like deadlock and starvation. Implementation By using JavaFX as our GUI and using OOP principles for more organized and clean code with good and clean graphics and animation.

## 2. Deadlock
#### 2.1 Example
The Deadlock issue appears when two customers start a transaction and they never get out from the critical section so that other customer can transfer to. 
#### 2.2 Solution
We solve this problem by using condition.await() and signalAll from the code 

## 3. Starvation
#### 2.1 Example
Starvation problem appears when a large number of customers transfer to the same account or having to wait too long for other customers to finish their transaction first so you can make your own transaction.

#### 2.2 Solution
We can solve this problem by setting an time interval so that after exceeding that time it shut down the transfer operation and ask the customer to try again later.
