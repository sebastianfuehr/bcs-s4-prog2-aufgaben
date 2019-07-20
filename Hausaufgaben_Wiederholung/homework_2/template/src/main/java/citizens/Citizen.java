package citizens;

public class Citizen {

    private String bezName,
            ortsteilName,
            altersgruppe;
    private int bezirk,
            ortsteil,
            geschlecht,
            id;
    private double haeufigkeit;
    private char staat;

    public Citizen(int id, int bezirk, String bezName, int ortsteil, String ortsteilName, int geschlecht, char staat,
                   String altersgruppe, double haeufigkeit) {
        this.bezName = bezName;
        this.ortsteilName = ortsteilName;
        this.altersgruppe = altersgruppe;
        this.bezirk = bezirk;
        this.ortsteil = ortsteil;
        this.geschlecht = geschlecht;
        this.haeufigkeit = haeufigkeit;
        this.staat = staat;
        this.id = id;
    }

    public String getBezName() {
        return bezName;
    }

    public String getOrtsteilName() {
        return ortsteilName;
    }

    public String getAltersgruppe() {
        return altersgruppe;
    }

    public int getBezirk() {
        return bezirk;
    }

    public int getOrtsteil() {
        return ortsteil;
    }

    public int getGeschlecht() {
        return geschlecht;
    }

    public double getHaeufigkeit() {
        return haeufigkeit;
    }

    public char getStaat() {
        return staat;
    }
}
