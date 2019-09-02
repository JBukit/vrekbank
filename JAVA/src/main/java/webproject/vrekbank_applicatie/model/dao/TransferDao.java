package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Transfer;

public interface TransferDao extends CrudRepository<Transfer, Integer> {

        // additional methodes to check/update in database

    }

