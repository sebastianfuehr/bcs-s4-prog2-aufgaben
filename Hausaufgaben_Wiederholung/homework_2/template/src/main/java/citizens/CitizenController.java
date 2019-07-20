package citizens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CitizenController {

    public static void main(String[] args) {
        ArrayList<Citizen> list = new ArrayList<>();
        try {
            list = Citizens.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.2.2
        System.out.println(String.format(
         "Männer zwischen 25 und 25 Jahren mit deutscher Staatsangehörigkeit in Ortsteil Charlottenburg: %f",
            list.stream().filter(c ->
                    c.getGeschlecht() == 1
                    && (c.getAltersgruppe().equals("25_30") || c.getAltersgruppe().equals("30_35"))
                    && c.getStaat() == 'D'
                    && c.getOrtsteilName().equals("Charlottenburg"))
                    .map(Citizen::getHaeufigkeit)
                    .reduce((e, acc) -> acc+e).get()));

        // 2.2.3
        HashMap<String, Double> map1 = new HashMap<>();
        list.stream().filter(cit -> cit.getAltersgruppe().equals("65_70") || cit.getAltersgruppe().equals("70_75")
                || cit.getAltersgruppe().equals("75_80") || cit.getAltersgruppe().equals("80_85")
                || cit.getAltersgruppe().equals("85_90") || cit.getAltersgruppe().equals("90_95"))
                .forEach(cit -> Citizens.addValuesToMap(map1, cit));
        System.out.println(String.format(
                "Ortsteil von Berlin in dem 2016 die meisten Senioren wohnten: %s",
                map1.entrySet().stream().reduce((acc, e) -> {
                    if (e.getValue() > acc.getValue()) {
                        return e;
                    } else {
                        return acc;
                    }
                }).get().getKey()
        ));

        HashMap<String, Double> map2 = new HashMap<>();
        list.stream().filter(cit -> cit.getAltersgruppe().equals("00_05") || cit.getAltersgruppe().equals("05_10"))
                .forEach(cit -> Citizens.addValuesToMap(map2, cit));
        System.out.println(String.format(
                "Ortsteil von Berlin in dem 2016 die meisten Kinder wohnten: %s",
                map2.entrySet().stream().reduce((acc, e) -> {
                    if (e.getValue() > acc.getValue()) {
                        return e;
                    } else {
                        return acc;
                    }
                }).get().getKey()
        ));

        // Citizens.printOrtsteile();
    }

}
