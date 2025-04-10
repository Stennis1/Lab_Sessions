// Unit testing of the Bank Management System
import java.time.LocalDate;

public class ConsoleRunnerTest {
    public static void main(String[] args) {

        // Create a Savings Account and perform operations
        System.out.println("\nTest cases for Savings Account");
        BankAccount savings = new SavingsAccount("GCB-001", "Stennis Smith",
                500.00);
        savings.deposit(500.00);    // Deposit 500.00
        savings.withdraw(500.00);   // Withdraw 500.00
        savings.withdraw(3500.00);
        System.out.println("Current Balance: " + savings.getBalance());
        savings.printLastNTransactions(5);

        System.out.println("\n");
        System.out.println("Test cases for Current Account");
        // Create a Current Account and perform operations
        BankAccount current = new CurrentAccount("GCB-002", "Emmanuel White",
                500.00, 1000.00);
        current.withdraw(500.00);
        current.withdraw(900.00); // Allow withdrawal
        current.withdraw(2000.00);  // Prevent overdraft exceeding
        System.out.println("Current Balance: " + current.getBalance());
        current.printLastNTransactions(5);


        System.out.println("\n");
        System.out.println("Test cases for Fixed Account");
        // Create a Fixed Deposit Account and perform operations
        BankAccount fixedDeposit = new FixedDepositAccount("GCB-003", "Roger Satsi",
                3000.00, LocalDate.now().minusDays(1)); // Maturity date set to yesterday
        fixedDeposit.withdraw(2500.00); // Withdrawal allowed
        System.out.println("Current Balance: " + fixedDeposit.getBalance());
        savings.printLastNTransactions(5);


        System.out.println("\n");
        BankAccount fixedDepositLocked = new FixedDepositAccount("GCB-003", "Roger Satsi",
                3000.00, LocalDate.now().plusDays(7)); // Maturity date reaches in a week
        fixedDepositLocked.withdraw(2000); // Withdrawal declined
        System.out.println("Current Balance: " + fixedDepositLocked.getBalance());
        savings.printLastNTransactions(5);
    }
}
