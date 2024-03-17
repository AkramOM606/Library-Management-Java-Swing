public class oeuvre {
    private int id;
    private String titre;
    private String categorie;
    private String auteur;
    private String editeur;
    private int launchdate;
    private String statut;
    public oeuvre() {}

    public oeuvre(int id, String titre, String categorie, String auteur, String editeur, int launchdate,String statut) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.auteur = auteur;
        this.editeur = editeur;
        this.launchdate = launchdate;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getCategorie() {
        return categorie;
    }
    
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public String getAuteur() {
        return auteur;
    }
    
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    public String getEditeur() {
        return editeur;
    }
    
    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
    
    public int getLaunchdate() {
        return launchdate;
    }
    
    public void setLaunchdate(int launchdate) {
        this.launchdate = launchdate;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + titre + ", Category=" + categorie + ", Author=" + auteur
                + ", Publisher=" + editeur + ", ReleaseDate=" + launchdate + ", Status=" + statut + "]";
    }
}