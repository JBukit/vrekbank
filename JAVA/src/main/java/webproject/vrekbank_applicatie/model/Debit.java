package webproject.vrekbank_applicatie.model;

public class Debit extends Transfer {
    // variables
    private String IBAN;
    private double amount;

    // constructors
    public Debit() {
    }

    public Debit(int transactionNr, String description, String date, String IBAN, double amount) {
        super(transactionNr, description, date);
        this.IBAN = IBAN;
        this.amount = amount;
    }

    // getters and setters
    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
