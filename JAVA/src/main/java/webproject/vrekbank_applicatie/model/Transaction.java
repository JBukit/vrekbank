package webproject.vrekbank_applicatie.model;

abstract class Transaction {
    // variables
    private int transactionNr;
    private String description;
    private String date;

    // constructors
    public Transaction() {
    }

    public Transaction(int transactionNr, String description, String date) {
        super();
        this.transactionNr = transactionNr;
        this.description = description;
        this.date = date;
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
}
