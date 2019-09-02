package webproject.vrekbank_applicatie.model;

import java.math.BigInteger;
import java.util.*;

public abstract class Account {
    // variables
    private int accountId;

    private String IBAN;
    private double balance;
    private double minimumBalance;
    private boolean businessAccount;

    private List<Customer> owners;
    private List<Customer> representatives;

    // constructors
    public Account() {
        owners = new ArrayList<Customer>();
        representatives = new ArrayList<Customer>();
    }

    public Account(int accountId, String IBAN, double balance,
                   double minimumBalance, boolean businessAccount) {
        this.accountId = accountId;
        this.IBAN = IBAN;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.businessAccount = businessAccount;
        owners = new ArrayList<Customer>();
        representatives = new ArrayList<Customer>();
    }

    // getters and setters
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
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
        String IBAN = null;

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

            //Concatenate all numbers to get IBAN in String
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
            IBAN = ibanFinalString;
        }
        return IBAN;
    }
}
