package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping(value = "RegistrationConfirmation")
    public String registrationConfirmationHandler() {
        return "RegistrationConfirmation";
    }
}
