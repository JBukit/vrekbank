package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;

import java.util.List;

@Service
public class BusinessAccountValidator {

    @Autowired
    BusinessAccountDao businessAccountDao;

    public BusinessAccountValidator() {
        super();
    }

    // methods to add, check, update, delete customers

    public void saveBusinessAccount(BusinessAccount businessAccount) {
        businessAccountDao.save(businessAccount);
    }

    public List<BusinessAccount> findAllBusinessAccountByCustomer (Customer customer) {
        List<BusinessAccount> accounts = businessAccountDao.findByOwner(customer);
        return accounts;
    }
}
