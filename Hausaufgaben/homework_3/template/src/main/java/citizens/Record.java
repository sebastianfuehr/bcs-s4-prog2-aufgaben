package citizens;

/**
 * Represents one record (a line) from the table. 
 */
public class Record {
    public final int bezirk;
    public final String berzirkName;
    public final int ortsteil;
    public final String ortsteilName;
    public final String geschlecht;
    public final String staatsangehörigkeit;
    public final int minAlter;
    public final int maxAlter;
    public final int häufigkeit;

    public Record(int bezirk, String berzirkName, int ortsteil, String ortsteilName,
                  String geschlecht, String staatsangehörigkeit,
                  int minAlter, int maxAlter, int häufigkeit) {
        this.bezirk = bezirk;
        this.berzirkName = berzirkName;
        this.ortsteil = ortsteil;
        this.ortsteilName = ortsteilName;
        this.geschlecht = geschlecht;
        this.staatsangehörigkeit = staatsangehörigkeit;
        this.minAlter = minAlter;
        this.maxAlter = maxAlter;
        this.häufigkeit = häufigkeit;
    }

    public String toString() {
        return (berzirkName + ortsteilName + geschlecht + staatsangehörigkeit + minAlter + maxAlter);
    }
}
