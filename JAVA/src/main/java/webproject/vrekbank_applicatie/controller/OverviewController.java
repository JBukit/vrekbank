package webproject.vrekbank_applicatie.controller;

//@author team 3 vrekbank, jacco vd heuvel

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class OverviewController {

    @GetMapping(value = "openaccount")
    public String overviewOpenAccountHandler() {
        return "OpenAccount";
    }
}