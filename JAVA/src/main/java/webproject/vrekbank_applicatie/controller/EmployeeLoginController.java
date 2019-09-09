package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeLoginController {

    // handler voor checken of ingegeven login gegevens kloppen
    @PostMapping(value = "employeeOverview")
    public String EmployeeLoginOverviewHandler() {

        return "EmployeeOverview";
    }

}
