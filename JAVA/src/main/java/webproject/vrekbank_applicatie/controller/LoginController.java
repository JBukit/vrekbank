package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Account;
import webproject.vrekbank_applicatie.model.PersonalAccount;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @GetMapping(value = "Overview")
    public String loginOverviewHandler (Model model) {
        // make lists with accounts, to be added from database
        List<PersonalAccount> accounts = new ArrayList<>();

        // add accounts of customer (from database, now by hand)
        accounts.add(new PersonalAccount(1,"NL1",10,0,false));
        accounts.add(new PersonalAccount(2,"NL2",20,0,false));
        accounts.add(new PersonalAccount(3,"NL3",30,0,false));

        // add list to .html
        model.addAttribute("allAccounts",accounts);

        return "Overview";
    }

}
