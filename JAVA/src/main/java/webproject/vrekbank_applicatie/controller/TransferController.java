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

    private String messageLackOfFunds = "u lijdt aan een groots en meeslepend gebrek aan Euries op uw rekening...";
    private String messageDebitIbanUnknown = "het rekeningnummer van de ontvanger niet bij ons bekend is.";
    private String messageRecipientNameUnknown = "de opgegeven naam van de ontvanger niet klopt.";
    private String messageEmptyField = "een van de velden niet ingevuld was, of het te betalen bedrag niet hoger " +
            "dan 0 was.";


    @Autowired
    TransferValidator transferValidator;

    @Autowired
    AccountValidator accountValidator;

    // erg lang, nog opknippen en/of zaken verplaatsen naar service
    @PostMapping(value = "TransferConfirmation")
    public String transferTransferConfirmationHandler(@SessionAttribute("iban") String iban, @ModelAttribute Transfer
            transfer, Model model, @ModelAttribute Recipient recipient, Model model2) {

        // in transferobject iban van betaler opnemen
        transfer.setDebitIban(iban);

        //alle velden verplicht; vermoedelijk ook in html te regelen
        if (transfer.getCreditIban().equals("") || transfer.getTransferAmount() <= 0.0 || transfer.getDate().equals("") ||
                transfer.getDebitIban().equals("") || transfer.getDescription().equals("")) {
            model.addAttribute("IssueEmptyField", messageEmptyField);
            return "TransferFailed";
        } else {
            // doorgeven naar volgend scherm
            model.containsAttribute("iban");
            model.containsAttribute("firstName");

            // model vullen uit transferobject
            model.addAttribute("debitIban", iban); // betaler
            model.addAttribute("creditIban", transfer.getCreditIban()); // ontvanger
            model.addAttribute("transferAmount", transfer.getTransferAmount());
            model.addAttribute("description", transfer.getDescription());
            model.addAttribute("date", transfer.getDate());

            // ingevoerde naam ontvanger vanuit tijdelijk object opnemen in model voor bevestigingsscherm
            //nb tweede object van Model waarschijnlijk onnodig, nog opnemen in model?
            model2.addAttribute("personalName", recipient.getPersonalName());
            model2.addAttribute("companyName", recipient.getCompanyName());

            //uit tranferobject schrijven naar database in 3 stappen.
            //1.check op rekening betaler
            boolean balanceOK = accountValidator.debitDeductionIsAllowed(iban, transfer);

            //2. check op rekening ontvanger
            boolean debitIbanCorrect = accountValidator.creditIbanDoesExist(transfer.getCreditIban());

            //3. check op naam ontvanger
            boolean nameCorrect = accountValidator.receiverNameIsCorrect(transfer.getCreditIban(), transfer, recipient);

            // 4. If 1 tm 3 true, alle drie de mutaties op database uitvoeren
            if (balanceOK && debitIbanCorrect && nameCorrect) {

                accountValidator.updateDebitBalance(iban, transfer);

                accountValidator.updateCreditBalance(transfer.getCreditIban(), transfer);

                transferValidator.saveTransfer(transfer);

                return "TransferConfirmation";

            } else if (!balanceOK) {
                model.addAttribute("IssueLackOfFunds", messageLackOfFunds);
                return "TransferFailed";
            } else if (!debitIbanCorrect) {
                model.addAttribute("IssueDebitIban", messageDebitIbanUnknown);
                return "TransferFailed";
            } else {
                model.addAttribute("IssueRecipientName", messageRecipientNameUnknown);
                return "TransferFailed";
            }
        }
    }

    @GetMapping(value = "accountsummary")
    public String transferAccountSummaryHandler() {
        return "AccountSummary";
    }
}