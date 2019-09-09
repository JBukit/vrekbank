package webproject.vrekbank_applicatie.model;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    // variables
    @Id
    @GeneratedValue
    private int accountId;

    private String iban;
    private double balance;
    private double minimumBalance;
    private boolean businessAccount;

    @ManyToOne
    private Customer owner;

    //@ManyToMany (mappedBy = "accountsOwned")
    //private List<Customer> owners;


    //@ManyToMany (mappedBy = "personalAccountsOwned")
//    private List<Customer> owners;

//    @ManyToMany
//    private List<Customer> representatives;

    // constructors
    public Account() {
        //owners = new ArrayList<Customer>();
//        representatives = new ArrayList<Customer>();
    }

    public Account(int accountId, String iban, double balance,
                   double minimumBalance, boolean businessAccount) {
        this.accountId = accountId;
        this.iban = iban;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.businessAccount = businessAccount;
        //this.owners = new ArrayList<>();
//        this.representatives = new ArrayList<>();
    }

    // getters and setters
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getMinimumBalance() {
        return minimumBalance;
    }
    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
    public boolean isBusinessAccount() {
        return businessAccount;
    }
    public void setBusinessAccount(boolean businessAccount) {
        this.businessAccount = businessAccount;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
    //    public List<Customer> getOwners() {     return owners;}

//    public void setOwners(List<Customer> owners) {this.owners = owners;}

//    public List<Customer> getRepresentatives() {
//        return representatives;
//    }

//    public void setRepresentatives(List<Customer> representatives) {
//        this.representatives = representatives;
//    }

    // methode om IBAN aan te maken
    public static String CreateIBAN() {
        //variables
        final String TWO_ZEROS = "00";
        final int SUBTRACTION_NUMBER = 98;
        final int MAX_CONTROLNR = 10;

        final int CORRECTIONS_LETTER_TO_NUMBER = 55; // A = 10, B = 11, enz..
        final int NUMBERS_IN_ACCOUNTNUMBER = 10;
        String nameBank = "VREK";
        String numberBank = "";
        int bankNumber;
        String bankNationality = "NL";
        String numberBankNationality = "";
        int bankNumberNationality;
        String iban = null;

       // omzetten nameBank in getal
       for (int i = 0; i < nameBank.length();i++) {
           int getal = nameBank.charAt(i)-CORRECTIONS_LETTER_TO_NUMBER;
           numberBank +=String.valueOf(getal);
       }
       bankNumber = Integer.parseInt(numberBank);

        // omzetten nationaliteit in getal
       for (int i = 0; i < bankNationality.length();i++) {
           int getal = bankNationality.charAt(i)-CORRECTIONS_LETTER_TO_NUMBER;
           numberBankNationality += String.valueOf(getal);
       }
       bankNumberNationality = Integer.parseInt(numberBankNationality);


        //Create random account number
        for (int i = 0; i < 1; i++) {
            long randomAccountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

            //Concatenate all numbers to get iban in String
            StringBuilder sb = new StringBuilder();
            sb.append(bankNumber);
            sb.append(randomAccountNumber);
            sb.append(bankNumberNationality);
            sb.append(TWO_ZEROS);

            //Convert StringBuilder to String
            String ibanString = sb.toString();

            //Convert String to number
            BigInteger ibanBigInteger = new BigInteger(ibanString);

            //Determine remaining number
            BigInteger remainingNumberBigInteger = ibanBigInteger.mod(new BigInteger("97"));

            //Convert BigInteger to int
            int remainingNumberInt = remainingNumberBigInteger.intValue();

            //Determine control number
            int controlNumber = SUBTRACTION_NUMBER - remainingNumberInt;

            //Construct final iban number
            StringBuilder sbFinal = new StringBuilder();
            sbFinal.append(bankNationality);
            if (controlNumber < MAX_CONTROLNR) {
                sbFinal.append("0" + controlNumber);
            } else {
                sbFinal.append(controlNumber);
            }
            sbFinal.append(nameBank);
            sbFinal.append(randomAccountNumber);

            //Convert StringBuilder to String
            String ibanFinalString = sbFinal.toString();
            iban = ibanFinalString;
        }
        return iban;
    }
}
