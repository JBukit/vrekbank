package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    public Employee findEmployeeByUserName (String userName);


}
