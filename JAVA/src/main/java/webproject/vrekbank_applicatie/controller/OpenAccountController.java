package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;

import static webproject.vrekbank_applicatie.model.Account.CreateIBAN;

@Controller
public class OpenAccountController {

    @Autowired
    AccountValidator validator;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    //NOG BEDENKEN: hoe account in DB koppelen aan klant (via session?)
    // HOE COMPANY invoegen in business rekening? Is dat eerder aan session geladen?
    // nu gedaan door in klasse om te zetten in string.


    // handler voor particulier rekening. later evt aan te passen tot 1 handler voor ook MKB,
    // die via bijv radio knop beide subklassen kan aanmaken

    @PostMapping(value = "OpenPersonalAccountConfirmation")
    public String OpenAccountOpenPersonalAccountConfirmationHandler(@ModelAttribute PersonalAccount personalAccount, Model model) {

        // fill in new account/object
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

        validator.saveAccount(personalAccount);

        // go to OpenAccountConfirmationScreen
        return "OpenPersonalAccountConfirmation";
    }

    // tweede handler voor MKB rekeningen. NOG DOEN: set company
    @PostMapping(value = "OpenBusinessAccountConfirmation")
    public String OpenAccountOpenBusinessAccountConfirmationHandler(@ModelAttribute BusinessAccount businessAccount,
                                                                    Model model) {
        // fill in new account/object
        businessAccount.setAccountId(0); // op nul zetten, wordt door DB overschreven
        businessAccount.setBalance(0);
        businessAccount.setMinimumBalance(0);
        businessAccount.setIBAN(CreateIBAN());
        businessAccount.setBusinessAccount(true);

        // nog koppelen met de input van de klant

        //opnemen in  model, in ieder geval om in bevestigingsscherm gegevens terug te kunnen geven.
        model.addAttribute("saldo", businessAccount.getBalance());
        model.addAttribute("minimumsaldo", businessAccount.getMinimumBalance());
        model.addAttribute("rekeningnummer", businessAccount.getIBAN());
        model.addAttribute("bedrijf", businessAccount.getCompanyName());

        // add accountinfo to database
        businessAccountValidator.saveBusinessAccount(businessAccount);

        // go to OpenAccountConfirmationScreen
        return "OpenBusinessAccountConfirmation";
    }
}



