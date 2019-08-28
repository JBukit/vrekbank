package webproject.vrekbank_applicatie.model;

import java.util.*;

public abstract class Account {
    // variables
    private int accountId;
    private String IBAN;
    private double balance;
    private double minimumBalance;
    private boolean businessAccount;

    private List<Customer> owners;
    private List<Customer> representatives;

    // constructors
    public Account() {
        owners = new ArrayList<Customer>();
        representatives = new ArrayList<Customer>();
    }

    public Account(int accountId, String IBAN, double balance,
                   double minimumBalance, boolean businessAccount) {
        this.accountId = accountId;
        this.IBAN = IBAN;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.businessAccount = businessAccount;
        owners = new ArrayList<Customer>();
        representatives = new ArrayList<Customer>();
    }

    // getters and setters
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getMinimumBalance() {
        return minimumBalance;
    }
    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
    public boolean isBusinessAccount() {
        return businessAccount;
    }
    public void setBusinessAccount(boolean businessAccount) {
        this.businessAccount = businessAccount;
    }
}
