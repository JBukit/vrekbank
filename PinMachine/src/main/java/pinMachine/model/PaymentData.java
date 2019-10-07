package pinMachine.model;

//bla
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentData {

    @Id
    @GeneratedValue
    private int paymentId;
    private String iban;
    private int pin;
    private double paymentAmount;


    public PaymentData() {
        super();
        this.paymentId = 0;
        this.iban = "";
        this.pin = 0;
        this.paymentAmount = 0.0;
    }
    public PaymentData(String iban, int pin, double paymentAmount) {
        super();
        this.paymentId = 0;
        this.iban = iban;
        this.pin = pin;
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
