package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.service.AccountValidator;

import java.util.List;


@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class AccountSummaryController {

    @Autowired
    AccountValidator accountValidator;



    @GetMapping(value = "transaction")
    public String accountSummaryTransactionHandler(@ModelAttribute Transfer transfer, Model model) {
        model.containsAttribute("firstName");


        model.containsAttribute("iban");

        return "Transfer";

////
//    Transfer transfer1 = new Transfer();
//
//    //Maak een if-statement:    transferamount > 0   &&   balance -transferamount >=  minimumbalance   &&
//
////    .addAttribute("name", .getUsername());
//
//        //Controlepunten:
//        //0. Alle verplichte velden ingevuld?
//        //1. Bedrag dat wordt overgemaakt moet groter zijn dan 0
//        //2. Geen roodstand
//        //3. Iban ontvanger moet valide zijn, bestaat de iban?
//        //Indien niet aan een voorwaarde voldaan: Pagina wordt herladen en gebruiker moet het opnieuw invullen
//
//    }

    }

    @GetMapping (value = "addAccountHolder")
    public String addAccountHolder (@SessionAttribute ("iban") String accountIban, Model model) {
        model.containsAttribute("firstName");
        model.containsAttribute("accountIban");
//
//        List<Customer> accountHolders = accountValidator.findAllAccountHoldersByAccountId(accountIban);
//        model.addAttribute("accountHolders", accountHolders);

        return "addAccountHolder";
    }

}




