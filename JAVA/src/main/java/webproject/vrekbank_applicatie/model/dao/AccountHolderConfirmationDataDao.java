package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.AccountHolderConfirmationData;

public interface AccountHolderConfirmationDataDao extends CrudRepository<AccountHolderConfirmationData,Integer> {

    public AccountHolderConfirmationData getAccountHolderConfirmationDataByAccountIban (String iban);



}
