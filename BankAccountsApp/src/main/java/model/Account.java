package model;

public class Account {
    private String routingNumber;
    private String accountNumber;
    private String bankName;
    private int balance;

    public Account() {}

    public Account(String routingNumber, String accountNumber, String bankName, int balance) {
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.balance = balance;
    }

    public String getRoutingNumber() { return routingNumber; }
    public void setRoutingNumber(String routingNumber) { this.routingNumber = routingNumber; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    @Override
    public String toString() { return accountNumber + " - " + bankName; }
}
