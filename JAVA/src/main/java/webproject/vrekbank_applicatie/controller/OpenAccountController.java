package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;

@Controller
public class OpenAccountController {
    @PostMapping(value = "OpenAccountConfirmation")
    public String OpenAccountOpenAccountConfirmationHandler() {

        // fill in new account/object

        //model.addAttribute("businessAccount", account.setBusinessAccount(account.isBusinessAccount());

        //account.setBalance(0);
        //account.setMinimumBalance(0);
        //account.setBusinessAccount(account.isBusinessAccount());


        // generate IBAN (including check that is does not yet exist) and add to new account/model?

        // add IBAN to new account object
        ///account.setIBAN(OUTPUT VAN METHODE?);

        // add accountinfo to database

        // go to OpenAccountConfirmationScreen
        return "OpenAccountConfirmation";
    }
}



