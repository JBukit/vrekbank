package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.CustomerValidator;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"name", "iban"})
public class OverviewController {

    @Autowired
    AccountValidator accountValidator;

    @Autowired
    CustomerValidator customerValidator;

    @GetMapping(value = "openaccount")
    public String overviewOpenAccountHandler() {
        return "OpenAccount";
    }

    //Show overview recent transactions
    @GetMapping(value = "rekening")
    public String loginOverviewHandler (@RequestParam String iban, Model model) {

        //pass name of logged in customer to show username on this page
        model.containsAttribute("name");

        //pass IBAN number that was selected in Overview.html
        model.addAttribute("iban", iban);
        System.out.println("De IBAN die wordt doorgegeven is " + iban);

        //Check transaction history for this IBAN

        //Below text for testing purposes
        //Make list with transfers, to be added from database
        List<Transfer> transfers = new ArrayList<>();

        //Add transfers to list
        transfers.add(new Transfer(1, "test", "10-10-2010", "NL56RABO32453515", "NL56RABO32453515", 50.50));
        transfers.add(new Transfer(2, "test2", "12-10-2019", "NL56RAB3413515", "NL56RABO353252", 20.50));

        model.addAttribute("transfers", transfers);

        return "AccountSummary";
    }

}
