package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.PersonalAccount;

public interface PersonalAccountDao extends CrudRepository<PersonalAccount, Integer> {
    // additional methodes to check/update in database

}