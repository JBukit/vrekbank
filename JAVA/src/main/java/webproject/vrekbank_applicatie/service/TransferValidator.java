package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.TransferDao;

@Service
public class TransferValidator {

    @Autowired
    TransferDao transferDao;

    public TransferValidator() {super();}

    public void saveTransfer (Transfer transfer) {
        transferDao.save(transfer);
    }
}

