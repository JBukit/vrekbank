package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.service.AccountValidator;

@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class AccountHolderController {

    @Autowired
    AccountValidator accountValidator;

    @GetMapping (value = "addAccount")
    public String addAccountAsAccountHolder (Model model) {
        return "RegisterAccountAsAccountHolder";
    }

    @PostMapping(value = "registerAccountHolderConfirmation")
    public String confirmRegistrationNewAccountHolder (@RequestParam ("iban") String iban, @SessionAttribute ("firstName") String firstName, @SessionAttribute ("name") String userName, @RequestParam ("securityCode") int securityCode, Model model) {
        if (accountValidator.correctAccountHolderConfirmationData(iban,firstName,securityCode)) {
            accountValidator.addAccountHolderToAccount(iban,userName);
            accountValidator.deleteAccountHolderConfirmationData(iban);
            return "RegisterAccountHolderConfirmation";
        } else {
                    return "RegisterAccountHolderError";
        }
    }

}
