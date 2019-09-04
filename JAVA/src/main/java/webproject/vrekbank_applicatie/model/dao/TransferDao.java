package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Transfer;

import java.util.List;

public interface TransferDao extends CrudRepository<Transfer, Integer> {

        // additional methodes to check/update in database

        public List <Transfer> findByCreditIban (String creditIban);
        public List <Transfer> findByDebitIban (String debitIban);


}

