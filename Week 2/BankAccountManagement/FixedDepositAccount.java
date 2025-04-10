// Fixed Account class
import java.time.LocalDate;

public class FixedDepositAccount extends BankAccount {
    private final LocalDate maturityDate;

    public FixedDepositAccount(String accountNumber, String accountHolder,
                               double balance, LocalDate maturityDate) {
        super (accountNumber, accountHolder, balance);
        this.maturityDate = maturityDate;
    }

    @Override
    public void withdraw(double amount) {
        if (LocalDate.now().isAfter(maturityDate)) {
            if (amount <= balance) {
                balance -= amount;
                addTransaction("Withdraw: ", amount, balance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Withdrawal is not allowed before maturity date!");
        }
    }

}
