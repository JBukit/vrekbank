package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.CustomerValidator;
import webproject.vrekbank_applicatie.service.PersonalAccountValidator;

import static webproject.vrekbank_applicatie.model.Account.CreateIBAN;

@Controller
@SessionAttributes("name")
public class OpenAccountController {

    int welkomstcadeau = 100;
    int standaardminimum = 0;


    //@Autowired
    //AccountValidator validator;

    @Autowired
    PersonalAccountValidator personalAccountValidator;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    @Autowired
    CustomerValidator customerValidator;

    //NOG BEDENKEN: hoe account in DB koppelen aan klant (via session?)
    // HOE COMPANY invoegen in business rekening? Is dat eerder aan session geladen?
    // nu gedaan door in klasse om te zetten in string.

    @PostMapping(value = "OpenPersonalAccountConfirmation")
    public String OpenAccountOpenPersonalAccountConfirmationHandler(@SessionAttribute("name") String name,
                                                                    @ModelAttribute PersonalAccount personalAccount,
                                                                    Model model) {

        //Customer dezeklant = new Customer();
        //ArrayList<> lijstje = new ArrayList<Customer> (dezeklant);

        // fill in new account/object
        personalAccount.setAccountId(0); // op nul zetten, wordt door hibernate bij DB overschreven
        personalAccount.setBalance(welkomstcadeau);
        personalAccount.setMinimumBalance(standaardminimum);
        personalAccount.setIban(CreateIBAN());
        personalAccount.setBusinessAccount(false);

        Customer user = customerValidator.findCustomerByUsername(name);
        // zet in betreffende lijst
        //personalAccount.getOwners().add(user);
        personalAccount.setOwner(user);
        personalAccount.addAccountHolder(user);

        // lijst eigenaren in account beschrijven met de klant. Daarnaast deze klant toevoegen in het lijstje rekeningen
        //in die klant

        //opnemen in  model, in ieder geval om in bevestigingsscherm gegevens terug te kunnen geven.
        model.addAttribute("saldo", personalAccount.getBalance());
        model.addAttribute("minimumsaldo", personalAccount.getMinimumBalance());
        model.addAttribute("rekeningnummer", personalAccount.getIban());

        // add accountinfo to database

        personalAccountValidator.savePersonalAccount(personalAccount);

        // go to OpenAccountConfirmationScreen
        return "OpenPersonalAccountConfirmation";
    }

    // tweede handler voor MKB rekeningen
    @PostMapping(value = "OpenBusinessAccountConfirmation")
    public String OpenAccountOpenBusinessAccountConfirmationHandler(@SessionAttribute("name") String name,
                                                                    @ModelAttribute BusinessAccount businessAccount,
                                                                    Model model) {
        // fill in new account/object.
        businessAccount.setAccountId(0); // op nul zetten, wordt door DB overschreven
        businessAccount.setBalance(100);
        businessAccount.setMinimumBalance(0);
        businessAccount.setIban(CreateIBAN());
        businessAccount.setBusinessAccount(true);

        // assign accountManager based on sector
        String accountManager = businessAccountValidator.assignAccountManagerBasedOnSector(businessAccount,businessAccount.getSector());

        //opnemen in  model, om in bevestigingsscherm gegevens terug te kunnen geven.
        model.addAttribute("saldo", businessAccount.getBalance());
        model.addAttribute("minimumsaldo", businessAccount.getMinimumBalance());
        model.addAttribute("rekeningnummer", businessAccount.getIban());
        model.addAttribute("bedrijf", businessAccount.getCompanyName());
        model.addAttribute ("sector", businessAccount.getSector());
        model.addAttribute("accountManager", accountManager);

        Customer user = customerValidator.findCustomerByUsername(name);

        // koppel customer aan account
        businessAccount.setOwner(user);
        businessAccount.addAccountHolder(user);

        // add accountinfo to database
        businessAccountValidator.saveBusinessAccount(businessAccount);

        // go to OpenAccountConfirmationScreen
        return "OpenBusinessAccountConfirmation";
    }

    @PostMapping(value = "Index")
    public String OpenAccountIndexHandler() {
        return "Index";
    }

    @GetMapping(value = "Overview")
    public String OverviewHandler() {
        return "Overview";
    }
}