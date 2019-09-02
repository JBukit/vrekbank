package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.Id;


public class BusinessAccount extends Account {
    // variables
    private int companyId;

    // constructors
    public BusinessAccount() {
    }

    public BusinessAccount(int accountId, String IBAN, double balance, double minimumBalance,
                           boolean businessAccount, int companyId) {
        super(accountId, IBAN, balance, minimumBalance, businessAccount);
        this.companyId = companyId;
    }

    // getters and setters
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
