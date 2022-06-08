package model;

public class Etudiant {
    private String nom;
    private String prenom;
    private String cin;
    private String cne;
    private String email;
    private String Code;

    public Etudiant(String nom, String prenom, String cin, String cne, String email, String code) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.cne = cne;
        this.email = email;
        Code = code;
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

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
