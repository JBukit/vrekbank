package webproject.vrekbank_applicatie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    // variables
    @Id
    @GeneratedValue
    private int employeeId;
    private String firstName;
    private String suffix;
    private String lastName;
    private String userName;
    private String password;
    private String typeFunction;

    // constructors
    public Employee() {
    }

    public Employee(String firstName, String suffix, String lastName, String userName, String password, String function) {
        this.firstName = firstName;
        this.suffix = suffix;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.typeFunction = function;
    }

    // getters and setters
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTypeFunction() {
        return typeFunction;
    }
    public void setTypeFunction(String typeFunction) {
        this.typeFunction = typeFunction;
    }
}
