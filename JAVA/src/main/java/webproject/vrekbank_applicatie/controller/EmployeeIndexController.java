package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Employee;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.EmployeeValidator;

import java.util.List;

@Controller
@SessionAttributes({"name"})
public class EmployeeIndexController {

    private final String FUNCTION_HEAD_MKB = "Hoofd MKB";

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    BusinessAccountValidator BusinessAccountValidator;


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
    public String MKBHighestBalanceHandler (@SessionAttribute("name") String name,
                                            @ModelAttribute Employee loggedInEmployee, Model model) {
    // find employee based on inlog-information (userName en PW)
        System.out.println("start");
        Employee employee = employeeValidator.findEmployeeByUserName(name);
        System.out.println(loggedInEmployee.getUserName());
        System.out.println(employee.getTypeFunction());

        // if function = Hoofd MKB, show 10 accounts with highest balance
        if (employee.getTypeFunction().equals(FUNCTION_HEAD_MKB)) {
            List<BusinessAccount> top10 = BusinessAccountValidator.showTop10BusinessAccounts();
            model.addAttribute("top10", top10);
            return "MKBHighestBalance";
        }
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
