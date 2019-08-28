package webproject.vrekbank_applicatie.model;

public class Company {
    // variables
    private int companyId;
    private String companyName;
    private int KVKnr;
    private String sector;
    private int employeeId; // nog discussie of dit niet een object moet zijn ipv primitieve

    // constructors
    public Company() {
    }

    public Company(int companyId, String companyName, int KVKnr, String sector, int employeeId) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
        this.KVKnr = KVKnr;
        this.sector = sector;
        this.employeeId = employeeId;
    }

    // getters and setters
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public int getKVKnr() {
        return KVKnr;
    }
    public void setKVKnr(int KVKnr) {
        this.KVKnr = KVKnr;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
