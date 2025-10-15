package model;

import java.util.ArrayList;
import java.util.List;

public class AccountDirectory {
    private final List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() { return accounts; }

    public Account addAccount() {
        Account a = new Account();
        accounts.add(a);
        return a;
    }

    public void addAccount(Account a) { if (a != null) accounts.add(a); }

    public void deleteAccount(Account a) { accounts.remove(a); }

    public Account searchByAccountNumber(String accountNumber) {
        if (accountNumber == null) return null;
        for (Account a : accounts) {
            if (accountNumber.equals(a.getAccountNumber())) return a;
        }
        return null;
    }
}
