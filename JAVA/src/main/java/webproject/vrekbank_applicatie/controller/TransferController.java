package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.Recipient;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.TransferValidator;

@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class TransferController {

    @Autowired
    TransferValidator transferValidator;

    @Autowired
    AccountValidator accountValidator;

    @PostMapping(value = "TransferConfirmation")
    public String transferTransferConfirmationHandler(@SessionAttribute("iban") String iban, @ModelAttribute Transfer
            transfer, Model model, @ModelAttribute Recipient recipient, Model model2) {

        // in transferobject iban van betaler opnemen

        transfer.setDebitIban(iban);

        // doorgeven naar volgend scherm
        model.containsAttribute("iban");
        model.containsAttribute("firstName");

        // Controle in twee voorwaarden ( beiden moeten gelden, volgorde willekeurig);

        // 1. over te boeken bedrag is beschikbaar.
        // pseudocode: if saldo - transferamount >= minimumsaldo

        // 2. if Combinatie iban en voor?naam ontvanger bestaat (VREK klant)

        // in volgende ronde moet dit uitgebreid worden; if ontvanger geen vrekklant, dan de IBAN kan bestaan check.

        // model vullen uit transferobject
        model.addAttribute("debitIban", iban); // betaler
        model.addAttribute("creditIban", transfer.getCreditIban()); // ontvanger
        model.addAttribute("transferAmount", transfer.getTransferAmount());
        model.addAttribute("description", transfer.getDescription());
        model.addAttribute("date", transfer.getDate());

        // recipientname tijdelijk vastleggen
        model2.addAttribute("recipientName", recipient.getRecipientName());

        //uit tranferobject schrijven naar database in 3 stappen (volgorde?)

        //1.update tabel betaler(debitIban)
        accountValidator.UpdateDebitBalance(iban, transfer);

        //2. update tabel ontvanger (crebitiban)  // deze apart of opnemen in stap 1?
        accountValidator.UpdateCreditBalance(transfer.getCreditIban(), transfer, recipient);


        //3. insert in tabel transfer
        transferValidator.saveTransfer(transfer);

        return "TransferConfirmation";
    }


    @GetMapping(value = "accountsummary")
    public String transferAccountSummaryHandler() {
        return "AccountSummary";
    }
}

