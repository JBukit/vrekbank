package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.dao.CustomerDao;

@Service
public class CustomerValidator {

    @Autowired
    CustomerDao customerDao;

    // no-args constructor

    public CustomerValidator() {
        super();
    }


    // methods to add, check, update, delete customers




}
