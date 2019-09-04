package webproject.vrekbank_applicatie.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Transfer;


@Controller
@SessionAttributes("name")
public class AccountSummaryController {


    @GetMapping(value = "transaction")
    public String accountSummaryTransactionHandler(@ModelAttribute Transfer transfer, Model model) {
        model.containsAttribute("name");
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

}




