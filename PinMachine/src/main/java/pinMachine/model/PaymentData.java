package pinMachine.model;

public class PaymentData {

    private String iban;
    private int pin;
    private int paymentAmount;


    public PaymentData() {
        super();
        this.iban = "";
        this.pin = 0;
        this.paymentAmount = 0;
    }
    public PaymentData(String iban, int pin, int paymentAmount) {
        super();
        this.iban = iban;
        this.pin = pin;
        this.paymentAmount = paymentAmount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
