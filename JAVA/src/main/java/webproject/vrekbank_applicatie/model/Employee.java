package webproject.vrekbank_applicatie.model;

public class Employee {
    // variables
    private int employeeId;
    private String firstName;
    private String suffix;
    private String lastName;
    private String userName;
    private String password;

    // constructors
    public Employee() {
    }

    public Employee(int employeeId, String firstName, String suffix, String lastName, String userName, String password) {
        super();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.suffix = suffix;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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
}
