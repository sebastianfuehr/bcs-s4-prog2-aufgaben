package streams;

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

    public static Record fromLine(String line) {
        String[] split = line.split(";");
        int bezirk = Integer.parseInt(split[0]);
        String bezirkName = split[1];
        int ortsteil = Integer.parseInt(split[2]);
        String ortsteilName = split[3];
        String geschlecht = split[4];
        String staatsangehörigkeit = split[5];
        String[] ageSplit = split[6].split("_");
        int minAge = (ageSplit.length > 1) ? Integer.parseInt(ageSplit[0]) : 95;
        int maxAge = (ageSplit.length > 1) ? Integer.parseInt(ageSplit[1]) : Integer.MAX_VALUE;
        int häufigkeit = Integer.parseInt(split[7].split(",")[0]);
        return new Record(
                bezirk, bezirkName, ortsteil,
                ortsteilName, geschlecht, staatsangehörigkeit,
                minAge, maxAge, häufigkeit
        );
    }

    @Override
    public String toString() {
        return "Record{" +
                "bezirk=" + bezirk +
                ", berzirkName='" + berzirkName + '\'' +
                ", ortsteil=" + ortsteil +
                ", ortsteilName='" + ortsteilName + '\'' +
                ", geschlecht='" + geschlecht + '\'' +
                ", staatsangehörigkeit='" + staatsangehörigkeit + '\'' +
                ", minAlter=" + minAlter +
                ", maxAlter=" + maxAlter +
                ", häufigkeit=" + häufigkeit +
                '}';
    }
}
