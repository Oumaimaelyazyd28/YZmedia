package model;

public class Document {
    private  String titre;
    private String auteur;
    private String nbrPage;
    private String type;
    private  String isbn;
    private  String editeur;
    private  String url;
    private  String anneeEdition;

    public Document(String titre, String auteur, String nbrPage, String type, String isbn, String editeur, String url, String anneeEdition) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbrPage = nbrPage;
        this.type = type;
        this.isbn = isbn;
        this.editeur = editeur;
        this.url = url;
        this.anneeEdition = anneeEdition;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(String nbrPage) {
        this.nbrPage = nbrPage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String  getAnneeEdition() {
        return anneeEdition;
    }

    public void setAnneeEdition(String anneeEdition) {
        this.anneeEdition = anneeEdition;
    }
}

