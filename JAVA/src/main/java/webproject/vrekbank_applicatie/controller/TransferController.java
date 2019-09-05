package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.TransferValidator;

@Controller
@SessionAttributes
public class TransferController {

    @Autowired
    TransferValidator transferValidator;

    @PostMapping(value = "TransferConfirmation")
    public String transferTransferConfirmation (@ModelAttribute Transfer transfer, Model model) {
//        transferValidator.saveTransfer(transfer);
        model.addAttribute("creditIban", transfer.getCreditIban());
        model.addAttribute("debitIban", transfer.getDebitIban());
//        model.addAttribute("creditIbanName", transfer.getCreditIbanName());
//        model.addAttribute("debitIbanName", transfer.getDebitIbanName());
        model.addAttribute("transferAmount",transfer.getTransferAmount());
        model.addAttribute("description", transfer.getDescription());
        model.addAttribute("date", transfer.getDate());
        return "TransferConfirmation";
    }

    @GetMapping(value = "accountsummary")
    public String transferAccountSummaryHandler() {
        return "AccountSummary";
    }

}
