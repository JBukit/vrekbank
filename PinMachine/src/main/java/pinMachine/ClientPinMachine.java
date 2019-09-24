package pinMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//    @Entity
public class ClientPinMachine {
    //pin voor dagelijkse identificatie, uit 8 cijfers
//        @Id
    private int dailyConnectIdentifier;

    // pin alleen om te koppelen, uit 5 cijfers

    private int addIdentifier;

    private String iban;

    public ClientPinMachine() {
    }

    public ClientPinMachine(int dailyConnectIdentifier, int addIdentifier, String iban) {
        this.dailyConnectIdentifier = dailyConnectIdentifier;
        this.addIdentifier = addIdentifier;
        this.iban = iban;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}

