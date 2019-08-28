package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping(value = "Registration")
    public String indexRegisterHandler() {
        return "RegistrationConfirmation";
    }
}
