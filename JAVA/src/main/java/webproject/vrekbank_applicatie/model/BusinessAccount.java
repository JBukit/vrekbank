package webproject.vrekbank_applicatie.model;

import javax.persistence.*;

@Entity
public class BusinessAccount extends Account {

    // variables
    //private int companyId;
    private String companyName; // later vervangen door bovenstaande int, die gaat linken naar aparte bedrijventabel
    private String sector;
    @OneToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name = "fk_pinMachine")
    private PinMachine pinMachine;

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
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public PinMachine getPinMachine() {
        return pinMachine;
    }
    public void setPinMachine(PinMachine pinMachine) {
        this.pinMachine = pinMachine;
    }

    public void setSector(String sector) {
        this.sector = sector;

    }
}
