package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.model.Recipient;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.AccountDao;

import java.util.List;

@Service
public class AccountValidator {

    @Autowired
    AccountDao accountDao;

    public AccountValidator() {
        super();
    }

    // methods to add, check, update, delete customers

    public void saveAccount(Account account) {
        accountDao.save(account);
    }


    public Account prepareDeduction  (String iban, Transfer transfer) {
        //1 Rekening betaler ophalen uit database
        Account payingaccount = accountDao.findByIban(iban);
        // 2 Huidige balans van betaler uitlezen
        double balance = payingaccount.getBalance();
        // 3 Nieuw saldo uitrekenen
        double newBalance = balance - transfer.getTransferAmount();
        // 4 nieuw saldo in object zetten
        payingaccount.setBalance(newBalance);
        //5 geeft object met beoogd nieuw saldo terug
        return payingaccount;
    }

    public boolean debitDeductionIsAllowed(String iban, Transfer transfer) {
        //voorwaarde voldoende op rekening checken
        if (prepareDeduction(iban, transfer).getBalance() >= prepareDeduction(iban, transfer).getMinimumBalance()) {
            return true;
        } else {
            return false;
        }
    }

    public void UpdateDebitBalance (String iban, Transfer transfer){
            accountDao.save(prepareDeduction(iban, transfer));
    }

    //    schrijven naar rekening ontvanger
    public boolean UpdateCreditBalance(String iban, Transfer transfer, Recipient recipient) {
        //1 Rekening ontvanger ophalen uit database
        Account receivingaccount = accountDao.findByIban(iban);

        // voorwaarde rekeningnr ontvanger bestaat bij VrekBank en
        if (receivingaccount != null)
            // voorwaarde opgegeven naam ontvanger (achternaam zonder voorvoegsels, later te verrijken) staat ook in DB
            if (recipient.getRecipientName().equals(receivingaccount.getOwner().getLastName())) {

                //2 Huidige balans van ontvanger uitlezen
                double balance = receivingaccount.getBalance();

                //3 Nieuw saldo uitrekenen
                double newBalance = balance + transfer.getTransferAmount();

                //4 Nieuw saldo in object zetten
                receivingaccount.setBalance(newBalance);

                //5 Database aanpassen
                accountDao.save(receivingaccount);

                return true;
            } else {
                return false;
            }
        else {
            return false;
        }


    }
}

/*    public List<Account> findAccountsByCustomerId (int id) {
        accountDao.
    }*/


