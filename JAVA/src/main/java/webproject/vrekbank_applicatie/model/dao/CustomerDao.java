package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Customer;

import java.util.List;

public interface CustomerDao extends CrudRepository<Customer, Integer> {

    // additional methodes to check/update in database

    public Customer findByUsername(String name);


}
