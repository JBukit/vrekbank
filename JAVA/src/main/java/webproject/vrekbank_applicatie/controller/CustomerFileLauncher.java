package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.CustomerValidator;
import webproject.vrekbank_applicatie.service.PersonalAccountValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class CustomerFileLauncher {

    public static final int FILE_SIZE = 4000;
    public static final double MINIMUMBALANCE = 0.0;
    public static final boolean BUSINESSACCOUNT = false;
    public static List<Customer> customerList;

    @Autowired
    CustomerValidator validator;

    @Autowired
    PersonalAccountValidator personalAccountValidator;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    public void makeCustomerList() {
        Scanner customerReader;
        Scanner companyReader;
        customerList = new ArrayList<>();
        // Reads fakenames file, splits values and saves them as a customer object
        try {
            File fakeNames = new File("Namelist/FakeNames.csv");
            customerReader = new Scanner(fakeNames);
            File fakeCompanyNames = new File("Namelist/FakeCompanyNames.csv");
            companyReader = new Scanner(fakeCompanyNames);
            for (int i = 0; i < FILE_SIZE; i++) {
                String[] customerSplit = customerReader.nextLine().split(";");
                String firstName = customerSplit[0];
                System.out.println(firstName);
                String suffix = customerSplit[1];
                String lastName = customerSplit[2];
                String address = customerSplit[3];
                String zipcode = customerSplit[4];
                String city = customerSplit[5];
                String email = customerSplit[6];
                char sex = customerSplit[7].charAt(0);
                String dob = customerSplit[8];
                int BSN = Integer.parseInt(customerSplit[9]);
                String userName = customerSplit[10];
                String password = customerSplit[11];
                int PIN = Integer.parseInt(customerSplit[12]);
                Customer newCustomer = new Customer(0, firstName, suffix, lastName, address, zipcode, city, email, sex, dob, BSN, userName, password, PIN);
                validator.saveCustomer(newCustomer);

                // Creates a personal account with random iban and balance
                Account account = new PersonalAccount();
                double balance = account.randomBalance();
                String iban = Account.CreateIBAN();
                PersonalAccount newPersonalAccount = new PersonalAccount(0, iban, balance, MINIMUMBALANCE, BUSINESSACCOUNT);
                // Links personal account to the new customer by setting customer as a Owner and
                newPersonalAccount.setOwner(newCustomer);
                newPersonalAccount.addAccountHolder(newCustomer);
                personalAccountValidator.savePersonalAccount(newPersonalAccount);

                if (i <= 1000) {
                    Account businessAaccount = new BusinessAccount();
                    double businessBalance = account.randomBalance();
                    String businessIban = Account.CreateIBAN();
                    boolean businessaccount2 = true;
                    String[] companySplit = companyReader.nextLine().split(";");
                    String companyName = companySplit[0];
                    String sector = companySplit[1];
                    BusinessAccount newBusinessAccount = new BusinessAccount(0, businessIban, businessBalance, MINIMUMBALANCE,
                            businessaccount2, companyName, sector);
                    newBusinessAccount.setOwner(newCustomer);
                    newBusinessAccount.addAccountHolder(newCustomer);
                    newBusinessAccount.setAccountManager
                            (businessAccountValidator.assignEmployeeBasedOnSector(newBusinessAccount, sector));
                    businessAccountValidator.saveBusinessAccount(newBusinessAccount);
                }
            }
        } catch(FileNotFoundException geenfile){
                        System.out.println("Not found");
        }
    }
}
