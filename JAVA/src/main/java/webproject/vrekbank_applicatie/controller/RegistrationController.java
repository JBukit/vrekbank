package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Customer;

@Controller
public class RegistrationController {

    @PostMapping(value = "RegistrationConfirmation")
    public String registrationConfirmationHandler(@ModelAttribute Customer customer, Model model) {
        return "RegistrationConfirmation";
    }
}
