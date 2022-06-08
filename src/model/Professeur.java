package model;

public class Professeur {
    private String nom;
    private String prenom;
    private String cin;
    private String cnss;
    private String email;
    private String code;

    public Professeur(String nom, String prenom, String cin, String cnss, String email, String code) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.cnss = cnss;
        this.email = email;
        code = code;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCnss() {
        return cnss;
    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        code = code;
    }
}

