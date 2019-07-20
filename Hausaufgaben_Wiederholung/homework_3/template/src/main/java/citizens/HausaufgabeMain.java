package citizens;

import citizenlist.CitizenList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HausaufgabeMain {

    public static void main(String[] args) {
        List<Record> list = new ArrayList<>();
        try {
            list = Citizens.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Musterlösung HA 2 Ergebnisse: -----------------------------------------------");
        System.out.println("Deutsche Männer 25-35: " + Citizens.filterForCharlottenburgAndStuff(list));
        System.out.println("Am meisten Senioren: " + Citizens.seniors(list));
        System.out.println("Am meisten Kinder: " + Citizens.kids(list));
        System.out.println("-----------------------------------------------------------------------------");
    }

}
