package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.service.CustomerValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CustomerFileLauncher {

    public static final int FILE_SIZE = 4000;
    public static List<Customer> customerList;

    @Autowired
    CustomerValidator validator;

    public void makeCustomerList() {
        Scanner customerReader;
        customerList = new ArrayList<>();
        try {
            File fakeNames = new File("Namelist/FakeNames.csv");
            customerReader = new Scanner(fakeNames);
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
            }
        } catch (FileNotFoundException geenfile) {
            System.out.println("Not found");
        }
    }
}
