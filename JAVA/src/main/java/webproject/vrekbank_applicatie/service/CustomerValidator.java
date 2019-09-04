package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.dao.CustomerDao;

import java.util.List;

@Service
public class CustomerValidator {

    @Autowired
    CustomerDao customerDao;

    // no-args constructor

    public CustomerValidator() {
        super();
    }


    // methods to add, check, update, delete customers
    public void saveCustomer (Customer customer) {
        customerDao.save(customer);
    }

    public Customer findCustomerByUsername (String name) {
        Customer c = customerDao.findByUsername(name);
        return c;
    }
}
