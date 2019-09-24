package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webproject.vrekbank_applicatie.model.Customer;
import webproject.vrekbank_applicatie.model.Employee;

@Controller
public class IndexController {

    @Autowired
    CustomerFileLauncher customerFileLauncher;

    @GetMapping(value = "login")
    public String indexLoginHandler() {
       Customer customer = new Customer();
        return "login";
    }

    @GetMapping(value = "registration")
    public String indexRegistrationHandler(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "registration";
    }

    @GetMapping(value = "initdb")
    public String initdb(Model model){
        customerFileLauncher.makeCustomerList();
        return "login";
    }

//    @GetMapping (value = "employeeIndex")
//    public String indexEmployeeHandler () {
//        return "EmployeeIndex";
//    }
}
