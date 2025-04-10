## **Features to Implement:**

- **Multiple Account Types with Unique Behaviors**
    - Implement the different types of accounts, each with distinct rules.
    - Example: Savings accounts require a minimum balance, current accounts allow overdrafts, and fixed deposit accounts restrict withdrawals until maturity.
  

- **Banking Operations with Polymorphism**
    - Support core banking operations: **deposit, withdrawal, and balance inquiry**.
    - Use **method overriding** to define different behaviors for each account type.
    - Implement **method overloading** for transaction processing variations.
  

- **Transaction History Management**
    - Maintain a **linked list** to store and manage transaction records efficiently.
    - Allow customers to **view their last N transactions** for financial tracking.


- **Use of Abstract Classes & Interfaces**
    - Design an **interface** for common banking operations.
    - Implement an **abstract class** as a blueprint for all account types to ensure consistency.


- **Account Creation & Constructor Chaining**
    - Implement **constructor chaining** for structured account initialization.
    - Use this **keyword** to distinguish instance variables from method parameters.


- **Dynamic Account Features & Flexibility**
    - Enable **interest calculation** for savings and fixed deposit accounts.
    - Allow **overdraft limits** for current accounts with predefined conditions.
    - Ensure easy extensibility for future account types and banking features


- **User Interface with JavaFX**
    - Develop a **JavaFX-based UI** to allow customers to create accounts, deposit, withdraw, and view transactions interactively.
    - Display transaction history in a structured format within the UI.
    - Implement event handling to respond to user actions in real time.

## **Steps**:

## Folder Structure 
BankAccountManagement/  
├── Main.java                  # JavaFX launcher  
├── MainUI.java                # JavaFX layout and event handling  
├── BankAccount.java           # Abstract base class  
├── BankOperations.java        # Interface with deposit/withdraw/print methods  
├── SavingsAccount.java        # Savings account subclass   
├── CurrentAccount.java        # Current account subclass    
├── FixedDepositAccount.java   # Fixed deposit subclass  
├── Transaction.java           # A single transaction object  
├── TransactionHistory.java    # Linked list to manage transactions  
├── ConsoleRunner.java         # (Optional) Simple console-based tester