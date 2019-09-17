package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BusinessAccountValidator {

    private final int TOP10 = 10;

    @Autowired
    BusinessAccountDao businessAccountDao;

    public BusinessAccountValidator() {
        super();
    }

    // methods to add, check, update, delete customers

    public void saveBusinessAccount(BusinessAccount businessAccount) {
        businessAccountDao.save(businessAccount);
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

    public List<BusinessAccount> findBusinessAccountsBySector(String sector) {

        // maak een lijst van alle business accounts in branche 'sector'

        List<BusinessAccount> sectorList = businessAccountDao.findBusinessAccountsBySector(sector);

        // geef gevulde lijst terug als 'sectorList'

        return sectorList;


    }
}


