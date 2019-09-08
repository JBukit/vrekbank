package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PersonalAccount extends Account {
    // variables

    // constructors
    public PersonalAccount() {
    }

    // getters and setters
    public PersonalAccount(int accountId, String iban, double balance,
                           double minimumBalance, boolean businessAccount) {
        super(accountId, iban, balance, minimumBalance, businessAccount);
    }
}
