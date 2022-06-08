package model;


public abstract class Documents {

    private  String titre;
    private String auteurs;
    private  String isbn;
    private  int anneeEdition;
    private  String editeur;
    private  String url;

    public Documents(String mTitre, String mAuteur, String mIsbn, int mAnneeEdition, String mEditeur, String mUrl){
        this.titre = new String(mTitre);
        this.auteurs = new String(mAuteur);
        this.isbn = new String(mIsbn);
        this.anneeEdition = mAnneeEdition;
        this.editeur = new String(mEditeur);
        this.url = new String(mUrl);
    }
    public Documents(){

    }

    public Documents(Documents d){
        this.titre = d.getTitre();
        this.auteurs = d.getAuteurs();
        this.isbn = d.getIsbn();
        this.anneeEdition = d.getAnneeEdition();
        this.editeur = d.getEditeur();
        this.url = d.getUrl();
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnneeEdition() {
        return anneeEdition;
    }

    public String getEditeur() {
        return editeur;
    }

    public String getUrl() {
        return url;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuteurs(String auteurs) {
        this.auteurs = auteurs;
    }

    public void setAnneeEdition(int anneeEdition) {
        this.anneeEdition = anneeEdition;
    }



    @Override
    public String toString() {
        return "Document{titre = "+titre+"," +
                "auteurs = "+auteurs+", " +
                "isbn = "+isbn+", " +
                "anneeEdition = "+anneeEdition+", " +
                "editure = "+editeur+", URL = "+url+"}";
    }

    public abstract String toSring();

    /*public void finzlize(){
        System.out.println(this.toString());
        System.out.println("Ce document va etre detruit");
    }*/


}

