package webproject.vrekbank_applicatie.controller;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
        // nog controleren; attrictuten van deze twee objecten kunnen we waarschijnlijk ook in 1 modelobject zetten?

        // in transferobject iban van betaler opnemen

        transfer.setDebitIban(iban);

        // doorgeven naar volgend scherm
        model.containsAttribute("iban");
        model.containsAttribute("firstName");

        // model vullen uit transferobject
        model.addAttribute("debitIban", iban); // betaler
        model.addAttribute("creditIban", transfer.getCreditIban()); // ontvanger
        model.addAttribute("transferAmount", transfer.getTransferAmount());
        model.addAttribute("description", transfer.getDescription());
        model.addAttribute("date", transfer.getDate());

        // recipientname tijdelijk vastleggen
        model2.addAttribute("recipientName", recipient.getRecipientName());


        //uit tranferobject schrijven naar database in 3 stappen. Nb: de checks staan in de update-functies.

        //1.check op rekening betaler

        accountValidator.debitDeductionIsAllowed(iban, transfer);

        //2. check op rekening ontvanger

        // 3. If 1 en 2 true, alle drie de mutaties op database uitvoeren

        accountValidator.UpdateDebitBalance(iban, transfer);

        accountValidator.UpdateCreditBalance(transfer.getCreditIban(), transfer, recipient);

        transferValidator.saveTransfer(transfer);

        return "TransferConfirmation";
    }




//                //toevoegen links naar specifieke foutmeldingspagina?



    @GetMapping(value = "accountsummary")
    public String transferAccountSummaryHandler() {
        return "AccountSummary";
    }
}