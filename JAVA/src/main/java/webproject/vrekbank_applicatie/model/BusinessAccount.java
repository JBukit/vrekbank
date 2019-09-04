package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessAccount extends Account {
    // variables
    //private int companyId;
    private String companyName; // later vervangen door bovenstaande int, die gaat linken naar aparte bedrijventabel

    // constructors
    public BusinessAccount() {
    }

    public BusinessAccount(int accountId, String IBAN, double balance, double minimumBalance,
                           boolean businessAccount, int companyId) {
        super(accountId, IBAN, balance, minimumBalance, businessAccount);
        //this.companyId = companyId;
        this.companyName = companyName;
    }

    // getters and setters
    //public int getCompanyId() {return companyId;
    //}
    //public void setCompanyId(int companyId) {
    //  this.companyId = companyId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
