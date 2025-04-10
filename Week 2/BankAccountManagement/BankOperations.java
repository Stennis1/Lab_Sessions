// Creating Interface for common banking methods

public interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    void printLastNTransactions(int n);
}
