package model;

public class Kindel {
    private String kindel;
    private String mac;
    private String model;
    private String version;

    public Kindel(String kindel, String mac, String model, String version) {
        this.kindel = kindel;
        this.mac = mac;
        this.model = model;
        this.version = version;
    }

    public String getKindel() {
        return kindel;
    }

    public void setKindel(String kindel) {
        this.kindel = kindel;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
