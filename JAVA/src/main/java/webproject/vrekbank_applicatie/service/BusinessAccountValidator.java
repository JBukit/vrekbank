package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Employee;
import webproject.vrekbank_applicatie.model.PinMachine;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import webproject.vrekbank_applicatie.model.dao.EmployeeDao;
import webproject.vrekbank_applicatie.model.dao.PinMachineDao;

@Service
public class BusinessAccountValidator {

    private final int TOP10 = 10;

    @Autowired
    BusinessAccountDao businessAccountDao;

    @Autowired
    EmployeeDao employeeDao;


    public BusinessAccountValidator() {
        super();
    }

    // methods to add, check, update, delete businessAccounts

    public void saveBusinessAccount(BusinessAccount businessAccount) {
        businessAccountDao.save(businessAccount);
    }

    public BusinessAccount findByPinMachine(PinMachine pinMachine) {
        return businessAccountDao.findByPinMachine(pinMachine);
    }

    public boolean exists(String iban) {
        return businessAccountDao.existsByIban(iban);

    }

    public boolean pinMachineExistsByIban(String iban) {
        if (businessAccountDao.findByIban(iban) != null)
            if (businessAccountDao.findByIban(iban).getPinMachine() != null) {
                return true;
            } else return false;
        else return false;
    }

    public BusinessAccount findByIban(String iban) {
        return businessAccountDao.findByIban(iban);
    }

    public List<BusinessAccount> findAllBusinessAccountByCustomer(Customer customer) {
        List<BusinessAccount> accounts = businessAccountDao.findByOwner(customer);
        return accounts;
    }

    public List<BusinessAccount> showTop10BusinessAccounts() {
        // make list with all accounts
        Iterable<BusinessAccount> iterable = businessAccountDao.findAll();
        List<BusinessAccount> allAccounts = new ArrayList<>();
        for (BusinessAccount account : iterable) {
            allAccounts.add(account);
        }
        // sort accounts based on descending balance
        allAccounts.sort(Comparator.comparing(BusinessAccount::getBalance).reversed());
        // make top10
        List<BusinessAccount> top10 = new ArrayList<>();
        // if less accounts in table then 10, size = size of allAcounts list
        int size = 0;
        if (allAccounts.size() < TOP10) {
            size = allAccounts.size();
        } else {
            size = TOP10;
        }
        for (int i = 0; i < size; i++) {
            top10.add(allAccounts.get(i));
        }
        return top10;
    }

    public String serialize(BusinessAccount businessAccount) {
        Gson gson = new Gson();
        String json = gson.toJson(businessAccount);
        return json;
    }

    public BusinessAccount deserialize(String json) {
        Gson gson = new Gson();
        BusinessAccount businessAccount = gson.fromJson(json, BusinessAccount.class);
        return businessAccount;
    }

    public List<BusinessAccount> findBusinessAccountsBySector(String sector) {

        // maak een lijst van alle business accounts in branche 'sector'

        List<BusinessAccount> sectorList = businessAccountDao.findBusinessAccountsBySector(sector);

        return sectorList;
    }

    public double findTotalBalanceBySector(String sector) {

        // maak een lijst van alle business accounts in branche 'sector' en tel de saldos op

        List<BusinessAccount> list = businessAccountDao.findBusinessAccountsBySector(sector);

        double totalBalance = 0.0;
        for (BusinessAccount account : list) {
            totalBalance += account.getBalance();
        }

        return totalBalance;
    }

    public List<BusinessAccount> findAccountsWhereCustomerIsAccountHolder(Customer customer) {
        List<BusinessAccount> accounts = new ArrayList<>();
        accounts = businessAccountDao.findAccountsByAccountHolders(customer);
        return accounts;
    }

    public boolean pinMachineExists(String iban) {
        if (businessAccountDao.findByIban(iban).getPinMachine() != null) {
            return true;
        } else {
            return false;
        }
    }

    public String assignAccountManagerBasedOnSector(BusinessAccount businessAccount, String sector) {
        System.out.println("start methode assignAccountManager");
        Employee nameAccountManager = new Employee();
        nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
        System.out.println(nameAccountManager.getFirstName());

        switch (sector) {
            case "Bouw":
                nameAccountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Financien":
                nameAccountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Handel":
                nameAccountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Horeca":
                nameAccountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Industrie":
                nameAccountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Landbouw":
                nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Logistiek":
                nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Onderwijs":
                nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "IT":
                nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Zorg":
                nameAccountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
        }

        System.out.println("AccountManager = " + nameAccountManager.getUserName());
        businessAccount.setAccountManager(nameAccountManager);

        return nameAccountManager.getUserName();
    }

    //hieronder functie aangepast tot Employee teruggeven, ipv de daaruit gehaalde String. Met gebruik van onderstaande
    //kan bovenstaande evt verkort worden
    public Employee assignEmployeeBasedOnSector(BusinessAccount businessAccount, String sector) {
        System.out.println("start methode assignAccountManager");
        Employee accountManager = new Employee();
        accountManager = employeeDao.findEmployeeByUserName("Elleke");
        System.out.println(accountManager.getFirstName());

        switch (sector) {
            case "Bouw":
                accountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Financien":
                accountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Handel":
                accountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Horeca":
                accountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Industrie":
                accountManager = employeeDao.findEmployeeByUserName("Tristan");
                break;
            case "Landbouw":
                accountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Logistiek":
                accountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Onderwijs":
                accountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "IT":
                accountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
            case "Zorg":
                accountManager = employeeDao.findEmployeeByUserName("Elleke");
                break;
        }

        return accountManager;
    }
}
