package webproject.vrekbank_applicatie.model;


// Deze klasse uiteindelijk gebruiken als entiteit in businessaccount. Sector is daar nu nog een string;
// uiteindelijk moet gebruiker via een pulldown de bij ons bekende sectoren kunnen kiezen

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sector {

    //variables
    @Id
    @GeneratedValue
    private int sectorId;
private String sectorName;

//constructors

    public Sector() {
    super();
    }

    public Sector(String sectorName) {
        super();
        this.sectorName = sectorName;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
}
