package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;

public interface BusinessAccountDao extends CrudRepository<Account, Integer> {
    // additional methodes to check/update in database

}