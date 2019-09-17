package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Recipient;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.AccountDao;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;

import java.util.List;

@Service
public class AccountValidator {

    @Autowired
    AccountDao accountDao;


    @Autowired
    BusinessAccountDao businessAccountDao;

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
        // 4. Muteer het object en geef nieuwe versie terug
        receivingAccount.setBalance(newBalance);
        return receivingAccount;
    }

    // deze te complexe functie nog opsplitsen. Daarmee ook differentieren in output naar gebruiker
//    public boolean creditAdditionIsAllowed(String iban, Transfer transfer, Recipient recipient) {
//        if (accountDao.findByIban(iban) != null) {
//            if (!accountDao.findByIban(iban).isBusinessAccount() &&
//                    accountDao.findByIban(iban).getOwner().getLastName().equals(recipient.getPersonalName())) {
//                return true;
//            } else if (businessAccountDao.findByIban(iban).getCompanyName().equals(recipient.getCompanyName())) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    public boolean creditIbanDoesExist(String iban) {
        if (accountDao.findByIban(iban) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean receiverNameIsCorrect(String iban, Transfer transfer, Recipient recipient) {
        if (!creditIbanDoesExist(iban)) {
            return false;
        }
        else if (!accountDao.findByIban(iban).isBusinessAccount() &&
                accountDao.findByIban(iban).getOwner().getLastName().equals(recipient.getPersonalName())) {
            return true;
        } else if (businessAccountDao.findByIban(iban).getCompanyName().equals(recipient.getCompanyName())) {
            return true;
        } else {
            return false;
        }
    }

    public void updateCreditBalance(String iban, Transfer transfer) {
        accountDao.save(prepareAddition(iban, transfer));
    }
}