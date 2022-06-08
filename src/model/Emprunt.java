package model;

public class Emprunt {
    private String user;
    private String cin;
    private String name;
    private String kindel;
    private String rendre;

    public Emprunt(String user, String cin, String name, String kindel, String rendre) {
        this.user = user;
        this.cin = cin;
        this.name = name;
        this.kindel = kindel;
        this.rendre = rendre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindel() {
        return kindel;
    }

    public void setKindel(String kindel) {
        this.kindel = kindel;
    }

    public String getRendre() {
        return rendre;
    }

    public void setRendre(String rendre) {
        this.rendre = rendre;
    }
}
