package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;

import java.util.List;

public interface PersonalAccountDao extends CrudRepository<PersonalAccount, Integer> {
    // additional methodes to check/update in database

    public List<PersonalAccount> findByOwner(Customer customer);

    public List<PersonalAccount> findAll ();

}