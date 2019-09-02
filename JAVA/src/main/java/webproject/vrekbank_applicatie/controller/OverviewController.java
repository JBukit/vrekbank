package webproject.vrekbank_applicatie.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class OverviewController {

    //
    @GetMapping("AcountSummary")
    public String hello(Model model) {
        model.addAttribute("name", "Tom");
        model.addAttribute("accountnumber", "123456");
        return "AccountSummary";
    }


}
