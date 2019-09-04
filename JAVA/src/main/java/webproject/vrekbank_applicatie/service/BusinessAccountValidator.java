package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;

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
}
