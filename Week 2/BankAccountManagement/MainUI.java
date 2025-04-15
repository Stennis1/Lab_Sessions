import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.LocalDate;

public class MainUI extends VBox {
    private final ComboBox<String> accountTypeBox;
    private final TextField accountNumberField, nameField, balanceField, overdraftField;
    private final DatePicker maturityPicker;
    private final Button createBtn, depositBtn, withdrawBtn;
    private  final TextField amountField;

    private TableView<TransactionNode> transactionTable;
    private ObservableList<TransactionNode> transactionList = FXCollections.observableArrayList();

    private BankAccount currentAccount;

    // Profile Section
    private VBox profileSection;
    private Label accountNumberLabel, accountHolderLabel, balanceLabel, accountTypeLabel;

    public MainUI() {
        setSpacing(10);
        setPadding(new Insets(20));

        // --- Input Fields ---
        accountTypeBox = new ComboBox<>();
        accountTypeBox.getItems().addAll("Savings", "Current", "Fixed Deposit");
        accountTypeBox.setPromptText("Select Account Type");

        accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        nameField = new TextField();
        nameField.setPromptText("Account Holder Name");

        balanceField = new TextField();
        balanceField.setPromptText("Initial Balance");

        overdraftField = new TextField();
        overdraftField.setPromptText("Overdraft Limit (Current only)");
        overdraftField.setVisible(false);

        maturityPicker = new DatePicker();
        maturityPicker.setPromptText("Maturity Date (FD only)");
        maturityPicker.setVisible(false);

        createBtn = new Button("Create Account");
        depositBtn = new Button("Deposit");
        withdrawBtn = new Button("Withdraw");

        amountField = new TextField();
        amountField.setPromptText("Amount");

        depositBtn.setDisable(true);
        withdrawBtn.setDisable(true);

        // --- TableView for Transaction History ---
        transactionTable = new TableView<>();
        transactionTable.setPrefHeight(200);

        TableColumn<TransactionNode, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> data.getValue().typeProperty());

        TableColumn<TransactionNode, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(data -> data.getValue().amountProperty());

        TableColumn<TransactionNode, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> data.getValue().dateProperty());

        transactionTable.getColumns().addAll(typeCol, amountCol, dateCol);
        transactionTable.setItems(transactionList);

        // --- Profile Section ---
        profileSection = new VBox(10);
        profileSection.setPadding(new Insets(20));
        profileSection.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        accountNumberLabel = new Label("Account Number: ");
        accountHolderLabel = new Label("Account Holder: ");
        balanceLabel = new Label("Balance: ");
        accountTypeLabel = new Label("Account Type: ");

        profileSection.getChildren().addAll(accountNumberLabel, accountHolderLabel, balanceLabel, accountTypeLabel);

        // Layout Setup
        getChildren().addAll(
                new Text("Create Account:"),
                accountTypeBox,
                accountNumberField,
                nameField,
                balanceField,
                overdraftField,
                maturityPicker,
                createBtn,
                new Separator(),
                new Text("Account Actions:"),
                amountField,
                depositBtn,
                withdrawBtn,
                new Separator(),
                new Text("Transaction History:"),
                transactionTable,
                new Separator(),
                new Text("Account Profile:"),
                profileSection
        );

        accountTypeBox.setOnAction(e -> {
            String selected = accountTypeBox.getValue();
            overdraftField.setVisible("Current".equals(selected));
            maturityPicker.setVisible("Fixed Deposit".equals(selected));
        });

        createBtn.setOnAction(e -> createAccount());
        depositBtn.setOnAction(e -> doDeposit());
        withdrawBtn.setOnAction(e -> doWithdraw());
    }

    private void createAccount() {
        try {
            String accNum = accountNumberField.getText();
            String name = nameField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            String type = accountTypeBox.getValue();

            switch (type) {
                case "Savings":
                    currentAccount = new SavingsAccount(accNum, name, balance);
                    break;
                case "Current":
                    double limit = Double.parseDouble(overdraftField.getText());
                    currentAccount = new CurrentAccount(accNum, name, balance, limit);
                    break;
                case "Fixed Deposit":
                    LocalDate date = maturityPicker.getValue();
                    currentAccount = new FixedDepositAccount(accNum, name, balance, date);
                    break;
                default:
                    throw new IllegalArgumentException("Please select a valid account type.");
            }

            transactionList.clear();
            refreshTransactions();

            depositBtn.setDisable(false);
            withdrawBtn.setDisable(false);

            // Update Profile Section
            updateProfile();

        } catch (Exception e) {
            showError("Error creating account: " + e.getMessage());
        }
    }

    private void doDeposit() {
        if (currentAccount != null) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                refreshTransactions();

                // Update Profile Section after Deposit
                updateProfile();
            } catch (Exception e) {
                showError("Deposit Error: " + e.getMessage());
            }
        }
    }

    private void doWithdraw() {
        if (currentAccount != null) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                refreshTransactions();

                // Update Profile Section after Withdrawal
                updateProfile();
            } catch (Exception e) {
                showError("Withdrawal Error: " + e.getMessage());
            }
        }
    }

    private void refreshTransactions() {
        transactionList.clear();
        TransactionNode current = currentAccount.transactionHistoryHead;
        while (current != null) {
            transactionList.add(current);
            current = current.next;
        }
    }

    private void updateProfile() {
        if (currentAccount != null) {
            accountNumberLabel.setText("Account Number: " + currentAccount.accountNumber);
            accountHolderLabel.setText("Account Holder: " + currentAccount.accountHolder);
            balanceLabel.setText("Balance: " + currentAccount.getBalance());
            accountTypeLabel.setText("Account Type: " + currentAccount.getClass().getSimpleName());
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
