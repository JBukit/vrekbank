package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webproject.vrekbank_applicatie.model.Customer;

@Controller
public class IndexController {

    @GetMapping(value = "login")
    public String indexLoginHandler() {
       Customer customer = new Customer();
        return "login";
    }

    @GetMapping(value = "registration")
    public String indexRegistrationHandler(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "registration";
    }
}
