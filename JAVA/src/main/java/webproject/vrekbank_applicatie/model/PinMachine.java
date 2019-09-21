package webproject.vrekbank_applicatie.model;

import javax.persistence.*;

@Entity
public class PinMachine {
    //pin voor dagelijkse identficatie, uit 8 cijfers
    @Id
    private int dailyConnectIdentifier;

    // pin alleen om te koppelen, uit 5 cijfers

    private int addIdentifier;

    @OneToOne(mappedBy = "pinMachine")
    private BusinessAccount businessAccount;

    public PinMachine(int dailyConnectIdentifier, int addIdentifier, BusinessAccount businessAccount) {
        this.dailyConnectIdentifier = dailyConnectIdentifier;
        this.addIdentifier = addIdentifier;
        //  this.businessAccount = businessAccount;
    }

    public int getDailyConnectIdentifier() {
        return dailyConnectIdentifier;
    }

    public void setDailyConnectIdentifier(int dailyConnectIdentifier) {
        this.dailyConnectIdentifier = dailyConnectIdentifier;
    }

    public int getAddIdentifier() {
        return addIdentifier;
    }

    public void setAddIdentifier(int addIdentifier) {
        this.addIdentifier = addIdentifier;
    }

    public BusinessAccount getBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(BusinessAccount businessAccount) {
        this.businessAccount = businessAccount;
    }
}

