package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;
import webproject.vrekbank_applicatie.model.dao.PersonalAccountDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalAccountValidator {

    @Autowired
    PersonalAccountDao personalAccountDao;

    public PersonalAccountValidator() {
        super();
    }

    // methods to add, check, update, delete customers

    public void savePersonalAccount(PersonalAccount personalAccount) {
        personalAccountDao.save(personalAccount);
    }

    public List<PersonalAccount> findAllPersonalAccountByCustomer (Customer customer) {
        List<PersonalAccount> accounts = personalAccountDao.findByOwner(customer);
        return accounts;
    }
}

