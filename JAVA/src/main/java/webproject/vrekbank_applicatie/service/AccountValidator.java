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


    public Account prepareDeduction(String iban, Transfer transfer) {
        //1 Rekening betaler ophalen uit database
        Account payingAccount = accountDao.findByIban(iban);
        // 2 Huidige balans van betaler uitlezen
        double balance = payingAccount.getBalance();
        // 3 Nieuw saldo uitrekenen
        double newBalance = balance - transfer.getTransferAmount();
        // 4 nieuw saldo in object zetten
        payingAccount.setBalance(newBalance);
        //5 geeft object met beoogd nieuw saldo terug
        return payingAccount;
    }

    public boolean debitDeductionIsAllowed(String iban, Transfer transfer) {
        //voorwaarde voldoende op rekening checken
        if (prepareDeduction(iban, transfer).getBalance() >= prepareDeduction(iban, transfer).getMinimumBalance()) {
            return true;
        } else {
            return false;
        }
    }

    public void updateDebitBalance(String iban, Transfer transfer) {
        accountDao.save(prepareDeduction(iban, transfer));
    }


    public Account prepareAddition(String iban, Transfer transfer) {
        // 1. Rekening ontvanger ophalen
        Account receivingAccount = accountDao.findByIban(iban);
        // 2. Hoeveel geld op rekening ontvanger bepalen
        double balance = receivingAccount.getBalance();
        // 3. Nieuw saldo = oud saldo + bijschrijving
        double newBalance = balance + transfer.getTransferAmount();
        // 4. Muteer het object
        receivingAccount.setBalance(newBalance);
        return receivingAccount;
    }

    public boolean creditAdditionIsAllowed(String iban, Transfer transfer, Recipient recipient) {
        // 1. Controleer of iban ontvanger in de database voorkomt
        if (accountDao.findByIban(iban) != null) {
            if (accountDao.findByIban(iban).getOwner().getLastName().equals(recipient.getPersonalName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void updateCreditBalance(String iban, Transfer transfer) {
        prepareAddition(iban, transfer);
        accountDao.save(prepareAddition(iban, transfer));
    }
}



