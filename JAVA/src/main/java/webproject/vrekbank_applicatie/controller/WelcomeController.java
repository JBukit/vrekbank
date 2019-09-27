package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping (value = "home")
    public String welcomePage () {
        System.out.println("start welcompage");

        return "index";
    }
}
