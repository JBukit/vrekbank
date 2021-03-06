package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.CustomerValidator;
import webproject.vrekbank_applicatie.service.TransferValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class OverviewController {

    private final int TOP10 = 10;

    @Autowired
    TransferValidator transferValidator;

    @Autowired
    CustomerValidator customerValidator;

    @Autowired
    AccountValidator accountValidator;

    @GetMapping(value = "openaccount")
    public String overviewOpenAccountHandler() {
        return "OpenAccount";
    }

    @GetMapping(value = "overview")
    public String overviewTransferHandler() {
        return "Overview";
    }

    //Show overview recent transactions
    @GetMapping(value = "rekening")
    public String overviewAccountSummaryHandler (@RequestParam String iban, Model model) {

        //pass name of logged in customer to show firstname on this page
        model.containsAttribute("firstName");

        model.addAttribute("balance", accountValidator.findByIban(iban).getBalance());

        //pass IBAN number that was selected in Overview.html
        model.addAttribute("iban", iban);
        System.out.println("De IBAN die wordt doorgegeven is " + iban);

        //Check transaction history for this IBAN
        List <Transfer> creditTransfers = new ArrayList<>();
        creditTransfers = transferValidator.findByCreditIban(iban);

/*      Below text for testing purposes
        for (int i = 0; i < creditTransfers.size(); i++) {
            System.out.println(creditTransfers.get(i).getDescription());
        }
*/

        List <Transfer> debitTransfers = new ArrayList<>();
        debitTransfers = transferValidator.findByDebitIban(iban);

/*      Below text for testing purposes
        for (int i = 0; i < debitTransfers.size(); i++) {
            System.out.println(debitTransfers.get(i).getDescription());
        }
*/

        List <Transfer> totalTransfers = new ArrayList<>();
        totalTransfers.addAll(creditTransfers);
        totalTransfers.addAll(debitTransfers);
        totalTransfers.sort(Comparator.comparing(Transfer::getDate));
        Collections.reverse(totalTransfers);

/*      Below text for testing purposes
        for (int i = 0; i < totalTransfers.size(); i++) {
            System.out.println(totalTransfers.get(i).getDate());
        }
*/

        //Show 10 most recent transactions
        List<Transfer> top10 = new ArrayList<>();
        int size = 0;
        if (totalTransfers.size() < TOP10) {
            size = totalTransfers.size();
        } else {
            size = TOP10;
        }
        for (int i = 0; i < size; i++ ) {
            top10.add(totalTransfers.get(i));
        }

        //pass on data from totalTransferList
        model.addAttribute("totalTransferList", top10);

/*      Below text for testing purposes
        Make list with transfers, to be added from database
        List<Transfer> transfers = new ArrayList<>();
        Add transfers to list
        transfers.add(new Transfer(1, "test", "10-10-2010", "NL56RABO32453515", "NL56RABO32453515", 50.50));
        transfers.add(new Transfer(2, "test2", "12-10-2019", "NL56RAB3413515", "NL56RABO353252", 20.50));
        model.addAttribute("transfers", transfers);
*/

        return "AccountSummary";
    }


    @PostMapping (value = "confirmationAccountHolder")
    public String confirmationAccountHolder (Model model) {

        return "ConfirmationAccountHolder";
    }


}
