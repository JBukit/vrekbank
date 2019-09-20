package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;


import java.util.List;

public interface BusinessAccountDao extends CrudRepository<BusinessAccount, Integer> {
    // additional methodes to check/update in database

    public BusinessAccount findByIban(String iban);

    public List<BusinessAccount> findByOwner(Customer customer);

    public List<BusinessAccount> findAll ();

    public List<BusinessAccount> findBusinessAccountsBySector(String sector);

    public Double findTotalBalanceBySector(String sector);

    //Check if iban exists in the table BusinessAccount to check if it's a business account
    public boolean existsByIban(String iban);

}