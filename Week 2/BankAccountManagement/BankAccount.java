// Abstract class for Common properties and shared logic

public abstract class BankAccount implements BankOperations {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;
    protected TransactionNode transactionHistoryHead; // Linked list

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistoryHead = null;
    }
    
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit: ", amount, balance); // Calls addTransaction method
        }
    }

    // Overloaded deposit method
    public  void deposit(double amount, String mode) {
        balance += amount;
        System.out.println("Deposited: " + amount + " via " + mode);
    }
    
    
    protected void addTransaction(String type, double amount, double postBalance) {
        TransactionNode newNode = new TransactionNode(type, amount, postBalance);
        newNode.next = transactionHistoryHead;
        transactionHistoryHead = newNode;
    }
    
    public abstract void withdraw(double amount);

    @Override
    public void printLastNTransactions(int n) {
        TransactionNode current = transactionHistoryHead; 
        int count = 0;

        while (current != null && count < n) {
            System.out.println(current);
            current = current.next;
            count++;
        }
    }

    public double getBalance() {
        return balance;
    }


}