// A user-friendly test terminal application

import java.time.LocalDate;
import java.util.Scanner;

public class BankAppConsoleTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        // Welcome a user to the Banking System
        System.out.println(" AmaliTech Bank Application ");

        //Creating a Bank Account
        System.out.println("=== Bank Account Creation ===\n");
        System.out.println("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.println("Enter Account Holder name: ");
        String name = sc.nextLine();

        System.out.println("Choose the Type of Bank Account: " +
                "1) Savings 2) Current 3) Fixed Account");
        int type = sc.nextInt();

        // Match case using switch statement for BankAccount Type selection
        switch (type) {
            case 1:
                System.out.println("Enter initial balance: ");
                double savingsBalance = sc.nextDouble();
                account = new SavingsAccount(accNo, name, savingsBalance);
                break;
            case 2:
                System.out.println("Enter deposit amount: ");
                double currentBalance = sc.nextDouble();
                System.out.println("Enter overdraft limit: ");
                double overdraft = sc.nextDouble();
                account = new CurrentAccount(accNo, name, currentBalance, overdraft);
                break;
            case 3:
                System.out.println("Enter Fixed deposit amount: ");
                double fdAmount = sc.nextDouble();
                System.out.println("Enter maturity date (days from today): ");
                int days = sc.nextInt();
                LocalDate maturity = LocalDate.now().plusDays(days);
                account = new FixedDepositAccount(accNo, name, fdAmount, maturity);
                break;
            default:
                System.out.println("Wrong input, please select a valid" +
                        " input between 1, 2 and 3");
                return;
        }

        // Main Operation loop
        int choice;
        do {
            System.out.print("\n --- Banking Menu ---");
            System.out.print("\n1. Deposit ");
            System.out.print("\n2. Withdraw ");
            System.out.print("\n3. Check Balance ");
            System.out.print("\n4. View previous transactions: ");
            System.out.print("\n5. Exit ");
            System.out.print("\nSelect an option: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter the amount to deposit: ");
                    double dp = sc.nextDouble();
                    account.deposit(dp);
                    break;

                case 2:
                    System.out.println("Enter the amount to withdraw: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;

                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 4:
                    System.out.println("How many recent transactions?: ");
                    int n = sc.nextInt();
                    account.printLastNTransactions(n);
                    break;

                case 5:
                    System.out.println("Thank you for using the " +
                            "AmaliTech Bank App. \nSee you soon :)");
                    break;

                default:
                    System.out.println("Invalid choice selection! Try again.");
            }

        } while (choice != 5);
        sc.close();
    }
}