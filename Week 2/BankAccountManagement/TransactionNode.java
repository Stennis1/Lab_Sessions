// Linked list node to store each transaction

public class TransactionNode {
    String type;    // Deposit
    double amount;
    double postBalance;
    TransactionNode next;

    public TransactionNode(String type, double amount, double postBalance) {
        this.type = type;
        this.amount = amount;
        this.postBalance = postBalance;
        this.next = null;
    }

    @Override
    public String toString() {
        return type + amount + " | Balance after: " + postBalance;
    }
}
