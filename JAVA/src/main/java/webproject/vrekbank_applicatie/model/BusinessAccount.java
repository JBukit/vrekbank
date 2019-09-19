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
    private int addPinMachineIdentifier5;
    private int pinMachineIdentifier8;
    private boolean pinMachineIsAdded;

    // constructors
    public BusinessAccount() {
    }

    public BusinessAccount(int accountId, String iban, double balance, double minimumBalance,
                           boolean businessAccount, String companyName, String sector, int addPinMachineIdentifier,
                           int pinMachineIdentifier, boolean pinMachineIsAdded) {
        super(accountId, iban, balance, minimumBalance, businessAccount);
        //this.companyId = companyId;
        this.companyName = companyName;
        this.sector = sector;
        this.addPinMachineIdentifier5 = addPinMachineIdentifier5;
        this.pinMachineIdentifier8 = pinMachineIdentifier8;
        this.pinMachineIsAdded = pinMachineIsAdded;
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

    public int getAddPinMachineIdentifier5() {
        return addPinMachineIdentifier5;
    }

    public void setAddPinMachineIdentifier5(int addPinMachineIdentifier5) {
        this.addPinMachineIdentifier5 = addPinMachineIdentifier5;
    }

    public int getPinMachineIdentifier8() {
        return pinMachineIdentifier8;
    }

    public void setPinMachineIdentifier8(int pinMachineIdentifier8) {
        this.pinMachineIdentifier8 = pinMachineIdentifier8;
    }

    public boolean isPinMachineIsAdded() {
        return pinMachineIsAdded;
    }

    public void setPinMachineIsAdded(boolean pinMachineIsAdded) {
        this.pinMachineIsAdded = pinMachineIsAdded;
    }
}
