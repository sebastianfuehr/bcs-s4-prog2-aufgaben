package citizens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * An implementation of the citizen-parser.
 */
public class Citizens {
    public static final String SEX_M = "1";
    public static final String SEX_F = "2";

    /**
     * This method will load and parse the CSV-Table into a
     * List of {@link Record}s.
     * 
     * @return a list of {@link Record} if successful.
     * @throws IOException
     */
    public static List<Record> load() throws IOException {
        ArrayList<Record> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(getCitizensFile()));
        reader.readLine(); // skip one
        String line;
        while((line = reader.readLine()) != null) {
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
            records.add(new Record(
                    bezirk, bezirkName, ortsteil,
                    ortsteilName, geschlecht, staatsangehörigkeit,
                    minAge, maxAge, häufigkeit
            ));
        }
        return records;
    }


    /**
     * Wie viele Männer zwischen 25 und 35 Jahren,
     * mit deutscher Staatsangehörigkeit leben in Ihrem Bezirk (Ortsteil)?
     *
     * @param records
     * @return
     */
    public static int filterForCharlottenburgAndStuff(List<Record> records) {
        int count = 0;
        for (Record record : records) {
            if (     record.geschlecht.equals(SEX_M) &&
                    (record.minAlter >= 25 && record.maxAlter <= 35) &&
                     record.staatsangehörigkeit.equals("D") &&
                     record.ortsteil == 401
                    ) {
                count += record.häufigkeit;
            }
        }
        return count;
    }

    /**
     * In welchem Ortsteil (!) von Berlin wohnten 2015 die meisten Senioren
     * ( alter ≥ 65 )?
     *
     * @param records
     * @return
     */
    public static String seniors(List<Record> records) {
        Map<Integer, String> ortsteile = new HashMap<>();
        Map<Integer, Long> seniorsDistribution = new HashMap<>();
        for (Record record : records) {
            ortsteile.put(record.ortsteil, record.ortsteilName);
            if (record.minAlter >= 65) {
                long v = seniorsDistribution.getOrDefault(record.ortsteil, 0l);
                seniorsDistribution.put(record.ortsteil, v + record.häufigkeit);
            }
        }
        return ortsteile.get(getKeyForMaxValue(seniorsDistribution));
    }

    /**
     * Find the greatest value from a Map from int to long. Then return the key.
     *
     * @param distribution map from ortsteil to count
     * @return the key with the greatest number.
     */
    private static int getKeyForMaxValue(Map<Integer, Long> distribution) {
        long max = Long.MIN_VALUE;
        int otMax = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Long> entry : distribution.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                otMax = entry.getKey();
            }
        }
        return otMax;
    }

    /**
     * In welchem die meisten Kinder im Grundschulalter, Kleinkinder und Babys (alter < 10))?
     *
     * @param records a list of {@link Record}s
     * @return the name of the ortsteil.
     */
    public static String kids(List<Record> records) {
        Map<Integer, String> ortsteile = new HashMap<>();
        Map<Integer, Long> childrenDistribution = new HashMap<>();
        for (Record record : records) {
            ortsteile.put(record.ortsteil, record.ortsteilName);
            if (record.maxAlter <= 10) {
                long v = childrenDistribution.getOrDefault(record.ortsteil, 0l);
                childrenDistribution.put(record.ortsteil, v + record.häufigkeit);
            }
        }
        return ortsteile.get(getKeyForMaxValue(childrenDistribution));
    }

    public static File getCitizensFile() {
        return new File(Objects.requireNonNull(
                Citizens.class.getClassLoader()
                        .getResource("EWR_Ortsteile_Berlin_2015.csv")).getFile()
        );
    }
}
