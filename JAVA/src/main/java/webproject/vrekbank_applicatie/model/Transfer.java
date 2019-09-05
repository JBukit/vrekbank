package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transfer {

    // variables
    @Id
    @GeneratedValue
    private int transactionNr;
    private String description;
    private String date;
    private String debitIban;
    private String creditIban;
    private String debitIbanName;
    private String creditIbanName;
    private double transferAmount;

    // constructors
    public Transfer() {
        this(0,"","","","","","",0.0);
    }

    public Transfer(int transactionNr, String description, String date, String debitIban, String creditIban, String debitIbanName, String creditIbanName, double transferAmount) {
        super();
        this.transactionNr = transactionNr;
        this.description = description;
        this.date = date;
        this.debitIban = debitIban;
        this.creditIban = creditIban;
        this.debitIbanName = debitIbanName;
        this.creditIbanName = creditIbanName;
        this.transferAmount = transferAmount;
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

    public String getDebitIbanName() { return debitIbanName; }
    public void setDebitIbanName(String debitIbanName) { this.debitIbanName = debitIbanName; }
    public String getCreditIbanName() { return creditIbanName; }
    public void setCreditIbanName(String creditIbanName) { this.creditIbanName = creditIbanName; }

    public double getTransferAmount() { return transferAmount; }
    public void setTransferAmount(double transferAmount) { this.transferAmount = transferAmount; }
}
