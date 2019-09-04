package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.TransferDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferValidator {

    @Autowired
    TransferDao transferDao;

    public TransferValidator() {super();}

    public void saveTransfer (Transfer transfer) {
        transferDao.save(transfer);
    }

    //Method to find transfer by creditIban
    public List<Transfer> findByCreditIban (String creditIban){
        List <Transfer> transfers = new ArrayList<>();
        transfers = transferDao.findByCreditIban(creditIban);
        return transfers;
    }

    //Method to find transfer by deditIban
    public List <Transfer> findByDebitIban (String debitIban){
        List <Transfer> transfers = new ArrayList<>();
        transfers = transferDao.findByDebitIban(debitIban);
        return transfers;    }
}

