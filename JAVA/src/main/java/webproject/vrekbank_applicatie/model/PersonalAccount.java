package webproject.vrekbank_applicatie.model;

public class PersonalAccount extends Account {
    // variables

    // constructors
    public PersonalAccount() {
    }

    // getters and setters
    public PersonalAccount(int accountId, String IBAN, double balance,
                           double minimumBalance, boolean businessAccount) {
        super(accountId, IBAN, balance, minimumBalance, businessAccount);
    }
}