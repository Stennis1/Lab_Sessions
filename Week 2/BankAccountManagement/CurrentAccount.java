// Current Account class

public class CurrentAccount extends BankAccount {
    private final double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            addTransaction("Withdraw: ", amount, balance);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }

}
