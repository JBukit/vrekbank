package webproject.vrekbank_applicatie.model;

// Ietwat sneue klasse, alleen om een naam op te vangen uit een betalingsopdracht, om die daarna te kunnen vergelijken
// met naam rekeninghouder van de rekening waar men heen wil overboeken.
// Geen Spring annotatie want dit hoeft niet in de database.

// @authors JvdH, TRW

public class Recipient {

    public String recipientName;



    public Recipient(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
