package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessAccount extends Account {
    // variables
    //private int companyId;
    private String companyName; // later vervangen door bovenstaande int, die gaat linken naar aparte bedrijventabel
    private String sector;

    // constructors
    public BusinessAccount() {
    }

    public BusinessAccount(int accountId, String iban, double balance, double minimumBalance,
                           boolean businessAccount, String companyName, String sector) {
        super(accountId, iban, balance, minimumBalance, businessAccount);
        //this.companyId = companyId;
        this.companyName = companyName;
        this.sector = sector;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }


}
