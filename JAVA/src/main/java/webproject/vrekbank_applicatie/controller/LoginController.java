package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping (value = "AccountOverview")
    public String OverviewHandler() {


        return "Overview";
    }
}
