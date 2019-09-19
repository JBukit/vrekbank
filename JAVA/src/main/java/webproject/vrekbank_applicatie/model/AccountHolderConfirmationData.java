package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountHolderConfirmationData {

    @Id
    @GeneratedValue
    private int confirmationId;
    private int accountNumber;
    private String accountHolderName;
    private int securityCode;

    public AccountHolderConfirmationData() {
        this("",0,0);
        this.confirmationId = 0;
    }

    public AccountHolderConfirmationData(String accountHolderName, int accountNumber, int securityCode) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
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
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
