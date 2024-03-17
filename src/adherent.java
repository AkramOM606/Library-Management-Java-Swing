public class adherent {
    private int numero;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;

    public adherent() {}
    
    public adherent(int numero, String nom, String prenom, String adresse, String email) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Member [ID=" + numero + ", LastName=" + nom + ", FirstName=" + prenom + ", Address=" + adresse
                + ", Email=" + email + "]";
    }    
}