package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.AccountDao;

import java.util.List;

@Service
public class AccountValidator {

    @Autowired
    AccountDao accountDao;

    public AccountValidator() {
        super();
    }

    // methods to add, check, update, delete customers

    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    // iban leidt tot account id, leidt tot balance

    public void UpdateBalance (String iban, Transfer transfer) {
    //1
      Account a = accountDao.findByIban(iban);
        // 2
        double balance = a.getBalance();
        // 3
        double newBalance = balance - transfer.getTransferAmount();
        // 4
        a.setBalance(newBalance);
        // 5 schrijven naar db
        accountDao.save(a);

    }
    // schrijven naar rekening ontvanger in zelfde functie of separaat?


/*    public List<Account> findAccountsByCustomerId (int id) {
        accountDao.
    }*/

}
