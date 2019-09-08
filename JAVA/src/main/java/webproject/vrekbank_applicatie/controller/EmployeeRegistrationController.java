package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.vrekbank_applicatie.model.Employee;
import webproject.vrekbank_applicatie.service.EmployeeValidator;

@Controller
public class EmployeeRegistrationController {

    @Autowired
    EmployeeValidator employeeValidator;

    @PostMapping (value = "EmployeeRegistrationConfirmation")
    public String EmployeeRegistrationHandler (@ModelAttribute Employee employee, Model model) {
        employeeValidator.saveEmployee(employee);
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("userName", employee.getUserName());
        return "EmployeeRegistrationConfirmation";
    }

}
