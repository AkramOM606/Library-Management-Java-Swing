public class pret {
    private String pretdate;
    private int idadherant;
    private int idoeuvre;

    public pret() {}

    public pret(String pretdate, int idadherant, int idoeuvre) {
        this.pretdate = pretdate;
        this.idadherant = idadherant;
        this.idoeuvre = idoeuvre;
    }
    
    public String getPretdate() {
        return pretdate;
    }
    
    public void setPretdate(String pretdate) {
        this.pretdate = pretdate;
    }
    
    public int getIdadherant() {
        return idadherant;
    }
    
    public void setIdadherant(int idadherant) {
        this.idadherant = idadherant;
    }
    
    public int getIdoeuvre() {
        return idoeuvre;
    }
    
    public void setIdoeuvre(int idoeuvre) {
        this.idoeuvre = idoeuvre;
    }

    public String toString() {
        return "Lending [LendingDate=" + pretdate + ", MemberID=" + idadherant + ", BookID=" + idoeuvre + "]";
    }
}