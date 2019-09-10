package webproject.vrekbank_applicatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import webproject.vrekbank_applicatie.model.Employee;

@Controller
public class EmployeeIndexController {

    @GetMapping (value = "employeeLogin")
    public String EmployeeLogin () {
        Employee employee = new Employee();
        return "EmployeeLogin";
    }

    @GetMapping (value = "employeeRegister")
    public String EmployeeIndexRegistrationHandler () {
        return "EmployeeRegistration";
    }

    @GetMapping (value = "MKBHighestBalance")
    public String MKBHighestBalanceHandler () {
        return "MKBHighestBalance";
    }

    @GetMapping (value = "MKBMostTransfers")
    public String MKBMostTransfersHandler () {
        return "MKBMostTransfers";
    }

    @GetMapping (value = "MKBSectorView")
    public String MKBSectorViewHandler () {
        return "MKBSectorView";
    }


}
