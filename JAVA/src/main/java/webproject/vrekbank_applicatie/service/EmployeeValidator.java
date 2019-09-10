package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.Employee;
import webproject.vrekbank_applicatie.model.dao.EmployeeDao;

@Service
public class EmployeeValidator {

    @Autowired
    EmployeeDao employeeDao;

    public void saveEmployee (Employee employee) {
        employeeDao.save(employee);
    }

    public Employee findEmployeeByUserName (String userName) {
        return employeeDao.findEmployeeByUserName(userName);
    }

}
