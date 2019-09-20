package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountHolderConfirmationData {

    @Id
    @GeneratedValue
    private int confirmationId;
    private String accountIban;
    private String accountHolderName;
    private int securityCode;

    public AccountHolderConfirmationData() {
        this("","",0);
        this.confirmationId = 0;
    }

    public AccountHolderConfirmationData(String accountHolderName, String accountIban, int securityCode) {
        this.accountHolderName = accountHolderName;
        this.accountIban = accountIban;
        this.securityCode = securityCode;
    }

    public int getConfirmationId() {
        return confirmationId;
    }
    public void setConfirmationId(int confirmationId) {
        this.confirmationId = confirmationId;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountIban() {
        return accountIban;
    }
    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }
    public int getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
