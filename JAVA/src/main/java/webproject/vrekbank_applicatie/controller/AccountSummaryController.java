package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.AccountValidator;

import java.util.List;


@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class AccountSummaryController {

    @Autowired
    AccountValidator accountValidator;

    @GetMapping(value = "transaction")
    public String accountSummaryTransactionHandler(@ModelAttribute Transfer transfer, Model model) {
        model.containsAttribute("firstName");
        model.containsAttribute("iban");

        return "Transfer";
    }

    @GetMapping (value = "addAccountHolder")
    public String addAccountHolder (@SessionAttribute ("iban") String accountIban, Model model) {
        model.containsAttribute("firstName");
        model.containsAttribute("accountIban");

        List<Customer> accountHolders = accountValidator.findAllAccountHoldersByIban(accountIban);
        model.addAttribute("accountHolders", accountHolders);

        return "AddAccountHolder";
    }

    @PostMapping (value = "addAccountHolderConfirmation")
    public String addAccountHolderConfirmation (@SessionAttribute ("iban") String iban, @RequestParam (value = "nameNewAccountHolder") String nameNewAccountHolder , @RequestParam(value = "securityCode") int securityCode, Model model) {
        model.addAttribute("securityCode", securityCode);
        System.out.println(securityCode);
        model.containsAttribute("firstName");
        model.containsAttribute("iban");
        model.addAttribute("newAccountHolder", nameNewAccountHolder);
        accountValidator.saveAccountHolderConfirmationData(nameNewAccountHolder, iban,securityCode);

        return "AddAccountHolderConfirmation";
    }

}




