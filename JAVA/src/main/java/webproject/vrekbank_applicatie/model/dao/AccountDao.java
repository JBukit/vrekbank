package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Customer;

import java.util.List;

public interface AccountDao extends CrudRepository<Account, Integer> {

    // additional methodes to check/update in database

    public Account findByIban(String iban);

    public List<Account> findAccountsByOwner(Customer customer);

    public List<Account> findAccountsByAccountHolders (Customer customer);

}
