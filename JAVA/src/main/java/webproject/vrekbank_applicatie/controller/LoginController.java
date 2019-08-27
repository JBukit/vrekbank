package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping(value = "Overview")
    public String loginHandler () {
        return "Overview";
    }
}
