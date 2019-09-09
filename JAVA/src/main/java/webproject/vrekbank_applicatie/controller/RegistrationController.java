package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.service.CustomerValidator;

//@author team 3 vrekbank, Sven Spithoven

@Controller
public class RegistrationController {

    @Autowired
    CustomerValidator validator;

    @PostMapping(value = "RegistrationConfirmation")
    public String registrationConfirmationHandler(@ModelAttribute Customer customer, Model model) {
        validator.saveCustomer(customer);
        model.addAttribute("firstname", customer.getFirstName());
        model.addAttribute("username", customer.getUsername());
        return "RegistrationConfirmation";
    }
}
