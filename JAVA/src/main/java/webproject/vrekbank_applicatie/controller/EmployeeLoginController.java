package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.Employee;
import webproject.vrekbank_applicatie.model.PersonalAccount;
import webproject.vrekbank_applicatie.service.EmployeeValidator;
import webproject.vrekbank_applicatie.service.PersonalAccountValidator;

import java.util.List;

@Controller
@SessionAttributes({"name"})
public class EmployeeLoginController {

    private final String FUNCTION_HEAD_PERSONAL = "Hoofd Particulieren";
    private final String FUNCTION_HEAD_MKB = "Hoofd MKB";

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    PersonalAccountValidator personalAccountValidator;

    // handler voor checken of ingegeven login gegevens kloppen
    @PostMapping(value = "employeeOverview")
    public String EmployeeOverviewHoofdParticulieren(@ModelAttribute Employee loggedInEmployee, Model model) {
        // find employee based on inlog-information (userName en PW)
        Employee employee = employeeValidator.findEmployeeByUserName(loggedInEmployee.getUserName());
        model.addAttribute("name", loggedInEmployee.getUserName());

        // if function = Hoofd Particuliern, show 10 accounts with highest balance
        if (employee.getTypeFunction().equals(FUNCTION_HEAD_PERSONAL)) {
            List<PersonalAccount> top10 = personalAccountValidator.showTop10PersonalAccounts();
            model.addAttribute("top10", top10);
            return "EmployeeOverview";
        }


        if (employee.getTypeFunction().equals(FUNCTION_HEAD_MKB)) {
            return "MKBOverview";
        }
        return "EmployeeOverview";
    }

}
