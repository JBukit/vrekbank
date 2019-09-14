package webproject.vrekbank_applicatie.model;

// Ietwat sneue klasse, alleen om een naam op te vangen uit een betalingsopdracht, om die daarna te kunnen vergelijken
// met naam rekeninghouder van de rekening waar men heen wil overboeken.
// Geen Spring annotatie want dit hoeft niet in de database.

// @authors JvdH, TRW

public class Recipient {

    //variabelen
    private String personalName;
    private String companyName;

//// constructor voor betaling naar priverekening
//    public Recipient(String personalName) {
//        this.personalName = personalName;
//        this.companyName = null;
//    }
////constructor voor betaling naar priverekening
//    public Recipient(String companyName) {
//        this.personalName = null;
//        this.companyName = companyName;
//    }

    // constructors

    public Recipient() {
        this.personalName = null;
        this.companyName = null;
    }

    public Recipient(String personalName, String companyName) {
        this.personalName = personalName;
        this.companyName = companyName;
    }

    //getters and setters

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
