package webproject.vrekbank_applicatie.model;

public class Transfer {
    // variables
    private int transactionNr;
    private String description;
    private String date;
    private String debitIban;
    private String creditIban;
    private double transferamount;

    // constructors
    public Transfer() {
    }

    public Transfer(int transactionNr, String description, String date, String debitIban, String creditIban, double transferamount) {
        super();
        this.transactionNr = transactionNr;
        this.description = description;
        this.date = date;
        this.debitIban = debitIban;
        this.creditIban = creditIban;
        this.transferamount = transferamount;
    }

    // getters and setters
    public int getTransactionNr() {
        return transactionNr;
    }
    public void setTransactionNr(int transactionNr) {
        this.transactionNr = transactionNr;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDebitIban() { return debitIban; }
    public void setDebitIban(String debitIban) { this.debitIban = debitIban; }
    public String getCreditIban() { return creditIban; }
    public void setCreditIban(String creditIban) { this.creditIban = creditIban; }
    public double getTransferamount() { return transferamount; }
    public void setTransferamount(double transferamount) { this.transferamount = transferamount; }
}
