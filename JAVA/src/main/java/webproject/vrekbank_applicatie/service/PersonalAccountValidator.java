package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.*;
import webproject.vrekbank_applicatie.model.dao.PersonalAccountDao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class PersonalAccountValidator {

    private final int TOP10= 10;

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

    public List<PersonalAccount> showTop10PersonalAccounts() {
        // make list with all accounts
        Iterable<PersonalAccount> iterable = personalAccountDao.findAll();
        List<PersonalAccount> allAccounts = personalAccountDao.findAll();
        for (PersonalAccount account : iterable) {
            allAccounts.add(account);
        }
        // sort accounts based on descending balance
        allAccounts.sort(Comparator.comparing(PersonalAccount::getBalance).reversed());
        // make top10
        List<PersonalAccount> top10 = new ArrayList<>();
        // if less accounts in table then 10, size = size of allAcounts list
        int size = 0;
        if (allAccounts.size() < TOP10) {
            size = allAccounts.size();
        } else {
            size = TOP10;
        }
        for (int i = 0; i < size; i++ ) {
            top10.add(allAccounts.get(i));
        }
        return top10;
    }
}

