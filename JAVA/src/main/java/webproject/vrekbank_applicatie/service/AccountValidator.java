package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Account;
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

    public void saveAccount (Account account) {
        accountDao.save(account);
    }

/*    public List<Account> findAccountsByCustomerId (int id) {
        accountDao.
    }*/


}
