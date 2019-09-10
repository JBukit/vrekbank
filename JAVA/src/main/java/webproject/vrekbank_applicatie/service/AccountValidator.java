package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public boolean UpdateDebitBalance(String iban, Transfer transfer) {
        //1 Rekening betaler ophalen uit database
        Account payingaccount = accountDao.findByIban(iban);
        // 2 Huidige balans van betaler uitlezen
        double balance = payingaccount.getBalance();
        // 3 Nieuw saldo uitrekenen
        double newBalance = balance - transfer.getTransferAmount();
        //voorwaarde voldoende op rekening, dan 4 en 5 uitvoeren
        if (newBalance >= payingaccount.getMinimumBalance()) {
            // 4 nieuw saldo in object zetten
            payingaccount.setBalance(newBalance);
            // 5 schrijven naar db
            accountDao.save(payingaccount);
            return true;
        } else {
            return false;
        }
    }

    //    schrijven naar rekening ontvanger
    public boolean UpdateCreditBalance(String iban, Transfer transfer, Recipient recipient) {
        //1 Rekening ontvanger ophalen uit database
        Account receivingaccount = accountDao.findByIban(iban);

        // voorwaarde rekeningnr ontvanger bestaat bij VrekBank en
        if (receivingaccount != null &
            // voorwaarde opgegeven naam ontvanger (achternaam zonder voorvoegsels, later te verrijken) staat ook in DB
            recipient.getRecipientName().equals(receivingaccount.getOwner().getLastName()) ){

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

    }

}

/*    public List<Account> findAccountsByCustomerId (int id) {
        accountDao.
    }*/


