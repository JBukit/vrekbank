package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;

import static webproject.vrekbank_applicatie.model.Account.CreateIBAN;

@Controller
public class OpenAccountController {

    // handler voor particulier rekening. later evt aan te passen tot 1 handler voor ook MKB,
    // die via bijv radio knop beide subklassen kan aanmaken
    @PostMapping(value = "OpenAccountConfirmation")
    public String OpenAccountOpenAccountConfirmationHandler(@ModelAttribute PersonalAccount personalAccount, Model model) {

        // fill in new account/object

        //model.addAttribute("businessAccount", account.setBusinessAccount(account.isBusinessAccount());

        personalAccount.setAccountId(0); // op nul zetten, wordt door DB overschreven
        personalAccount.setBalance(0);
        personalAccount.setMinimumBalance(0);
        personalAccount.setIBAN(CreateIBAN());
        personalAccount.setBusinessAccount(false);

        //opnemen in  model, in ieder geval om in bevestigingsscherm gegevens terug te kunnen geven.
        model.addAttribute("saldo", personalAccount.getBalance());
        model.addAttribute("minimumsaldo", personalAccount.getMinimumBalance());
        model.addAttribute("rekeningnummer", personalAccount.getIBAN());





        // add accountinfo to database

        // go to OpenAccountConfirmationScreen
        return "OpenAccountConfirmation";
    }
}



