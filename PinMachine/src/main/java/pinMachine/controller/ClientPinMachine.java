package pinMachine.controller;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientPinMachine {
    //pin voor dagelijkse identificatie, uit 8 cijfers

    @Id
    private long dailyConnectIdentifier;

    // pin alleen om te koppelen, uit 5 cijfers

    private int addIdentifier;

    private String iban;

    public ClientPinMachine() {
        super();
        this.dailyConnectIdentifier = 0;
        this.addIdentifier = 0;
        this.iban = "";
    }

    public ClientPinMachine(int dailyConnectIdentifier, int addIdentifier, String iban) {
        super();
        this.dailyConnectIdentifier = dailyConnectIdentifier;
        this.addIdentifier = addIdentifier;
        this.iban = iban;
    }

    public long getDailyConnectIdentifier() {
        return dailyConnectIdentifier;
    }

    public void setDailyConnectIdentifier(long dailyConnectIdentifier) {
        this.dailyConnectIdentifier = dailyConnectIdentifier;
    }

    public int getAddIdentifier() {
        return addIdentifier;
    }

    public void setAddIdentifier(int addIdentifier) {
        this.addIdentifier = addIdentifier;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}

