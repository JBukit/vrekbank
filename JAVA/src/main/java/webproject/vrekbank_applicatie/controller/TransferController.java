package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.TransferValidator;

@Controller
@SessionAttributes({"name", "iban"})
public class TransferController {

    @Autowired
    TransferValidator transferValidator;

    @PostMapping(value = "TransferConfirmation")
    public String transferTransferConfirmation (@SessionAttribute("iban") String iban, @ModelAttribute Transfer transfer, Model model) {

// poging tot doorgeven naar volgend scherm
        model.containsAttribute("iban");
        model.containsAttribute("name");

        // model vullen uit transferobject
        model.addAttribute("debitIban", iban); // betaler

        model.addAttribute("creditIban", transfer.getCreditIban()); // ontvanger

//        model.addAttribute("creditIbanName", transfer.getCreditIbanName());
//        model.addAttribute("debitIbanName", transfer.getDebitIbanName());

        model.addAttribute("transferAmount",transfer.getTransferAmount());
        model.addAttribute("description", transfer.getDescription());
        model.addAttribute("date", transfer.getDate());

        //uit tranfer object schriijven naar database
       // transferValidator.saveTransfer(transfer);

        return "TransferConfirmation";

    }

    @GetMapping(value = "accountsummary")
    public String transferAccountSummaryHandler() {
        return "AccountSummary";
    }

}
