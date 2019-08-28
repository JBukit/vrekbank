package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Customer;

@Controller
public class IndexController {

    @GetMapping(value = "login")
    public String indexLoginHandler() {
       Customer customer = new Customer();
        return "login";
    }

    @GetMapping(value = "")
    public String indexRegisterHandler() {
        return "Registration";
    }
}
