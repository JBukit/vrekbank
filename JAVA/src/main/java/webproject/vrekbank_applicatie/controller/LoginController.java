package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;
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
        if (customer.equals(customer1)) {

            // add Customer-name to html page
            model.addAttribute("name", customer.getFirstName());

            // make lists with accounts, to be added from database
            List<PersonalAccount> accounts = new ArrayList<>();

            // add accounts of customer (from database, now by hand)
            accounts.add(new PersonalAccount(1, "NL1", 10, 0, false));
            accounts.add(new PersonalAccount(2, "NL2", 20, 0, false));
            accounts.add(new PersonalAccount(3, "NL3", 30, 0, false));

            //model.addAttribute("accounts",accounts);

            // split PersonalAccount objects in 2 separate list (IBAN and balance)
            List<String> IBANs = new ArrayList<>();
            for (int i = 0; i < accounts.size(); i++) {
                IBANs.add(accounts.get(i).getIBAN());
            }
            List<Double> balances = new ArrayList<>();
            for (int i = 0; i < accounts.size(); i++) {
                balances.add(accounts.get(i).getBalance());
            }

            // add lists to .html
            model.addAttribute("IBAN", IBANs);
            model.addAttribute("balance", balances);

            return "Overview";
        }
        // if check = not ok, go back to Login
        return "Login";
    }


}
