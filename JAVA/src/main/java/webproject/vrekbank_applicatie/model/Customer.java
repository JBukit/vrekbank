package webproject.vrekbank_applicatie.model;

//Klasse customer, voor rekeninghouders en gemachtigden.
//@author team 3, VrekBank, Jacco vd Heuvel

public class Customer {

    private int customerId;
    private String name;
    private String password;

    // constructors

    public Customer() {
        this(0, "", "");
    }

    public Customer(int customerId, String name, String password) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
    }

    //getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}