// Linked list node to store each transaction

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public StringProperty typeProperty() {
        return new SimpleStringProperty(type);
    }

    public StringProperty amountProperty() {
        return new SimpleStringProperty(" " + String.format("%.2f", amount));
    }

    public StringProperty dateProperty() {
        // Optional: Replace with actual date if available. For now, use placeholder
        return new SimpleStringProperty("N/A");
    }

    public StringProperty balanceProperty() {
        return new SimpleStringProperty(" " + String.format("%.2f", postBalance));
    }

    @Override
    public String toString() {
        return type + amount + " | Balance after: " + postBalance;
    }
}
