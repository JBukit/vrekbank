package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.CustomerValidator;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    CustomerValidator customerValidator;

    @PostMapping(value = "Overview")
    public String loginOverviewHandler (@ModelAttribute Customer customer, Model model) {
        // find customer in database by Username of login
        Customer c = customerValidator.findCustomerByUsername(customer.getUsername());

        // if check = ok, proceed:
        if (customer.getUsername().equals(c.getUsername()) && customer.getPassword().equals(c.getPassword())) {

            // add Customer-name to html page to show username in page
            model.addAttribute("name", customer.getUsername());

            // make lists with accounts, to be added from database
            List<PersonalAccount> personalAccounts = new ArrayList<>();
            List<BusinessAccount> businessAccounts = new ArrayList<>();

            // add accounts of customer (from database, now by hand)
            personalAccounts.add(new PersonalAccount(1, "NL1", 10, 0, false));
            personalAccounts.add(new PersonalAccount(2, "NL2", 20, 0, false));
            personalAccounts.add(new PersonalAccount(3, "NL3", 30, 0, false));



            model.addAttribute("personalAccounts",personalAccounts);
            model.addAttribute("businessAccounts", businessAccounts);

            return "Overview";
        }
        // if check = not ok, go back to Login
        return "Login";
    }
}
