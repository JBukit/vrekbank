package webproject.vrekbank_applicatie.model;

public class AddPinMachineRequest {

    private String iban;

    public AddPinMachineRequest(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
