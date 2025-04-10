// Savings Account class

public class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 50.00;

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super (accountNumber, accountHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            addTransaction("Withdraw: ", amount, balance);
        } else {
            System.out.println("Insufficient balance! You don't have enough funds in your account to withdraw!");
        }
    }

}
