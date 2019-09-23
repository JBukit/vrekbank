package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"name", "firstName", "iban"})
public class AccountHolderController {

    @GetMapping (value = "addAccount")
    public String addAccountAsAccountHolder (Model model) {
        return "registerAccountAsAccountHolder";
    }

    @PostMapping(value = "registerAccountHolderConfirmation")
    public String confirmRegistrationNewAccountHolder (@RequestParam ("iban") String iban, @SessionAttribute ("firstName") String firstName, @RequestParam ("securityCode") int securityCode, Model model) {
        System.out.println("FirstName = " + firstName);
        System.out.println("iban = " + iban);
        System.out.println("securityCode = " + securityCode);

        // check in database of deze combi van naam, iban en code erin staat


        // zo ja:
        return "registerAccountHolderConfirmation";


        // zo nee:
//        return "registerAccountHolderError";
    }

}
