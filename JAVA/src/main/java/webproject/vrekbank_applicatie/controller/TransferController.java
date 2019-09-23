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
            transfer, Model model, @ModelAttribute Recipient recipient) {

        // in transferobject iban van betaler opnemen
        transfer.setDebitIban(iban);

        //alle velden verplicht; is vermoedelijk ook in html te regelen
        //eerst double in wrappperclass zetten, zodat getoetst kan worden op = null
        Double amountInWrapper = (Double) transfer.getTransferAmount();
        if (transfer.getCreditIban().equals("") || amountInWrapper == null || transfer.getTransferAmount() <= 0.0 ||
                transfer.getDate().equals("") || transfer.getDebitIban().equals("") || transfer.getDescription().equals("")) {
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

            // ingevoerde naam ontvanger (persoon/bedrijf) in tijdelijk object opnemen in model voor bevestigingsscherm
            model.addAttribute("recipient", recipient);


            //uit tranferobject schrijven naar database in 3 stappen.
            //1.check op rekening betaler
            boolean balanceOK = accountValidator.debitDeductionIsAllowed(transfer);

            //2. check op rekening ontvanger
            boolean creditIbanCorrect = accountValidator.creditIbanDoesExist(transfer.getCreditIban());

            //3. check op naam ontvanger
            boolean nameCorrect = accountValidator.receiverNameIsCorrect(transfer, recipient);

            // 4. If 1 tm 3 true, alle drie de mutaties op database uitvoeren
            if (balanceOK && creditIbanCorrect && nameCorrect) {

                accountValidator.updateDebitBalance(transfer);

                accountValidator.updateCreditBalance(transfer);

                transferValidator.saveTransfer(transfer);

                return "TransferConfirmation";

            } else if (!balanceOK) {
                model.addAttribute("IssueLackOfFunds", messageLackOfFunds);
                return "TransferFailed";
            } else if (!creditIbanCorrect) {
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