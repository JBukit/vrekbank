package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @PostMapping(value = "Overview")
    public String loginOverviewHandler (@ModelAttribute Customer customer, Model model) {
        // check login-information in database, for now default Customer
        Customer customer1 = new Customer (0, "", "", "", "", "", "", "", 'z',
                "", 0, "Donald", "1", 0);

        // if check = ok, proceed:
        if (customer.getUsername().equals(customer1.getUsername()) && customer.getPassword().equals(customer1.getPassword())) {

            // add Customer-name to html page
            model.addAttribute("name", customer.getUsername());

            // make lists with accounts, to be added from database
            List<PersonalAccount> personalAccounts = new ArrayList<>();
            List<BusinessAccount> businessAccounts = new ArrayList<>();

            // add accounts of customer (from database, now by hand)
            personalAccounts.add(new PersonalAccount(1, "NL1", 10, 0, false));
            personalAccounts.add(new PersonalAccount(2, "NL2", 20, 0, false));
            personalAccounts.add(new PersonalAccount(3, "NL3", 30, 0, false));

            businessAccounts.add(new BusinessAccount(1,"NL1",500,0,true,1));
            businessAccounts.add(new BusinessAccount(2,"NL2",600,0,true,2));

            model.addAttribute("personalAccounts",personalAccounts);
            model.addAttribute("businessAccounts", businessAccounts);

/*            // split PersonalAccount objects in 2 separate list (IBAN and balance)
            List<String> IBANs = new ArrayList<>();
            for (int i = 0; i < personalAccounts.size(); i++) {
                IBANs.add(personalAccounts.get(i).getIBAN());
            }
            List<Double> balances = new ArrayList<>();
            for (int i = 0; i < personalAccounts.size(); i++) {
                balances.add(personalAccounts.get(i).getBalance());
            }

            // add lists to .html
            model.addAttribute("IBAN", IBANs);
            model.addAttribute("balance", balances);*/

            return "Overview";
        }
        // if check = not ok, go back to Login
        return "Login";
    }


}
