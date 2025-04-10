// Linked list node to store each transaction

public class TransactionNode {
    String type;
    double amount;
    TransactionNode next;

    public TransactionNode(String type,double amount) {
        this.type = type;
        this.amount = amount;
        this.next = null;
    }

    @Override
    public String toString() {
        return type + amount;
    }
}
