package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.CustomerValidator;
import webproject.vrekbank_applicatie.service.PersonalAccountValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class CustomerFileLauncher {

    public static final int CUSTOMER_FILE_SIZE = 4000;
    public static final int COMPANYNAMES_FILE_SIZE = 1000;
    public static final double MINIMUMBALANCE = 0.0;

    @Autowired
    CustomerValidator validator;

    @Autowired
    PersonalAccountValidator personalAccountValidator;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    public void makeCustomerList() {
        Scanner customerReader;
        Scanner companyReader;
        // Reads fakenames file, splits values and saves them as a customer object
        try {
            File fakeNames = new File("Namelist/FakeNames.csv");
            customerReader = new Scanner(fakeNames);
            File fakeCompanyNames = new File("Namelist/FakeCompanyNames.csv");
            companyReader = new Scanner(fakeCompanyNames);
            for (int i = 0; i < CUSTOMER_FILE_SIZE; i++) {
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
                PersonalAccount newPersonalAccount = new PersonalAccount(0, iban, balance, MINIMUMBALANCE, false);
                // Links personal account to the new customer by setting customer as a Owner and Holder
                newPersonalAccount.setOwner(newCustomer);
                newPersonalAccount.addAccountHolder(newCustomer);
                personalAccountValidator.savePersonalAccount(newPersonalAccount);

                // Creates a business account with random iban and balance
                if (i <= COMPANYNAMES_FILE_SIZE) {
                    double businessBalance = account.randomBalance();
                    String businessIban = Account.CreateIBAN();
                    String[] companySplit = companyReader.nextLine().split(";");
                    String companyName = companySplit[0];
                    String sector = companySplit[1];
                    BusinessAccount newBusinessAccount = new BusinessAccount(0, businessIban, businessBalance, MINIMUMBALANCE,
                            true, companyName, sector);
                    // Links business account to the new customer by setting customer as a Owner and Holder
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
