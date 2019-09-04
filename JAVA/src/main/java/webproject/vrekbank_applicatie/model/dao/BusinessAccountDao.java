package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;

public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {
    // additional methodes to check/update in database

}