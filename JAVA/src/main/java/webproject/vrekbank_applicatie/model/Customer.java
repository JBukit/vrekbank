package webproject.vrekbank_applicatie.model;

//Klasse customer, voor rekeninghouders en gemachtigden.
//@author team 3, VrekBank, Jacco vd Heuvel



import java.util.zip.DataFormatException;

public class Customer {
// variabelen
    private int customerId;
    private String firstName;
    private String suffix;
    private String lastName;
    private String address;
    private String zipcode;
    private String city;
    private String email;
    private String sex;       // moet nog char worden
    private String dob;  // moet nog datatype date worden
    private int BSN;
    private String username;
    private String password;
    private int PIN;

    // methoden . Discussiepunt; mogen deze void zijn?
    //public PersonalAccount OpenPersonaAccount() {
   // }
// twee aparte methoden hiervoor?
    //public BusinessAccount OpenBusinessAccount() {
    // }


    // constructors

    public Customer() {
        this(0, "", "", "", "", "", "", "", "",
                "", 0, "", "", 0);
    }

    public Customer(int customerId, String firstName, String suffix, String lastName, String address, String zipcode,
                    String city, String email, String sex, String dob, int BSN, String username, String password, int PIN) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.suffix = suffix;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.sex = sex;
        this.dob = dob;
        this.BSN = BSN;
        this.username = username;
        this.password = password;
        this.PIN = PIN;
    }

    //getters and setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }
}