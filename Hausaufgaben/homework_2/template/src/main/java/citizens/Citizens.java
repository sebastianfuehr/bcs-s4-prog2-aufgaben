package citizens;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Citizens {

    public static void main(String[] args) {
        // 2.2.1
        ArrayList<CitizenGroup> list = readCitizensFile();
        // 2.2.2
        System.out.println("2.2.2: "
                + countEntriesWith(list,
                1,
                25,
                35,
                'D',
                "Spandau",
                "Spandau"));
        // 2.2.3
        analysingMethod(list);
        //printCitizens(list);
    }

    /*
    public static String getCityPartWithMostPeople(ArrayList<CitizenGroup> list) {
        String cityPart
    }
     */

    public <R> R reduce(ArrayList<CitizenGroup> list, Aggregation<CitizenGroup, R> aggregation) {
        R curr = aggregation.initial();
        for (CitizenGroup currListEl: list) {
            curr = aggregation.apply(curr, currListEl);
        }
        return curr;
    }

    /**
     * The Aggregation interface
     *
     * @param <T> The type of the elements to be transformed (typically the same as the Table's T type)
     * @param <R> The return type (could be anything)
     */
    interface Aggregation<CitizenGroup, R> {
        /**
         * The accumulator's initial value.
         *
         * @return
         */
        R initial(); // the initial value

        /**
         * Returns the next accumulator value
         *
         * @param accumulator the current accumulator value
         * @param next the next element
         * @return the next accumulator value
         */
        R apply(R accumulator, CitizenGroup next);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Task 2.2.2
     * Gives back the amount of citizens who match the search criteria.
     *
     * @param arrlist Array list of CitizenGroup objects.
     * @param sex
     * @param agegrp_min
     * @param agegrp_max
     * @param state
     * @param district
     * @param city
     * @return counter Amount of people who matched the search criteria.
     */
    private static double countEntriesWith(ArrayList<CitizenGroup> arrlist, int sex, int agegrp_min, int agegrp_max, char state,
                                          String district, String city) {
        double counter = 0;
        for (CitizenGroup cit: arrlist) {
            if (cit.getDistrict_name().equals(district) &&
                    cit.getCity_name().equals(city) &&
                    cit.getSex() == sex &&
                    cit.getAgegrp_min() >= agegrp_min &&
                    cit.getAgegrp_max() <= agegrp_max &&
                    cit.getState() == state) {
                counter += cit.getOccurrences();
            }
        }
        return counter;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Task. 2.2.1
     * Reads file from getCitizensFile() method and gives back its
     * entries as CitizenGroup objects in an array list.
     * @return list Array list of CitizenGroup objects.
     */
    private static ArrayList<CitizenGroup> readCitizensFile() {
        ArrayList<CitizenGroup> list = new ArrayList<>();
        File file = getCitizensFile();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(getCitizensFile()));
            String line;
            System.out.println("Starting to read file...");
            String[] columns = reader.readLine().split(";"); //maybe useful later
            while ((line = reader.readLine()) != null) {
                String[] citStr = line.split(";");
                int locMin, locMax;
                if (citStr[6].split("_").length != 2) {
                    locMin = 95;
                    locMax = 200;
                } else {
                    locMin = Integer.parseInt(citStr[6].split("_")[0]);
                    locMax =Integer.parseInt(citStr[6].split("_")[1]);
                }
                try {
                    list.add(new CitizenGroup(Integer.parseInt(citStr[0]),
                            citStr[1],
                            Integer.parseInt(citStr[2]),
                            citStr[3],
                            Integer.parseInt(citStr[4]),
                            citStr[5].charAt(0),
                            locMin,
                            locMax,
                            Double.parseDouble(citStr[7].replace(",","."))));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Finished reading. Closing BufferedReader.");
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
        return list;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    Mit HashMap schöner gelöst!
    Wäre theoretisch in 2 Zeilen in einer Methode möglich.
     */

    private static void analysingMethod(ArrayList<CitizenGroup> list) {
        System.out.println("Senioren: " + getCityWithMostCitizens(list, 65, 200));
        System.out.println("Kinder im Grundschulalter: " + getCityWithMostCitizens(list, 5, 10));
        System.out.println("Kleinkinder: " + getCityWithMostCitizens(list, 0, 5));
    }

    private static String getCityWithMostCitizens(ArrayList<CitizenGroup> list, int agegrp_min, int agegrp_max) {
        ArrayList<CitizenGroup> grpList = getListOfCitizenGroupsWhoMatch(list, agegrp_min, agegrp_max);
        ArrayList<City> cities = new ArrayList<>();
        for (CitizenGroup citGrp: grpList) {
            Boolean found = false;
            for (City currCity: cities) {
                if (citGrp.getCity_name().equals(currCity.getName())) {
                    currCity.addAmtOfCitizens(citGrp.getOccurrences());
                    found = true;
                    break;
                }
            }
            if (!found) cities.add(new City(citGrp.getCity_name(), citGrp.getOccurrences()));
        }
        City max = cities.get(0);
        for (City city: cities) {
            if (city.getAmtCitizens() > max.getAmtCitizens()) max = city;
        }
        return max.getName();
    }

    /**
     * Returns an array list with all CitizenGroup object who math the search criteria.
     *
     * @param list List to query.
     * @param agegrp_min
     * @param agegrp_max
     * @return
     */
    private static ArrayList<CitizenGroup> getListOfCitizenGroupsWhoMatch(ArrayList<CitizenGroup> list, int agegrp_min, int agegrp_max) {
        ArrayList<CitizenGroup> newList = new ArrayList<>();
        for (CitizenGroup cit: list) {
            if (cit.getAgegrp_max() <= agegrp_max && cit.getAgegrp_min() >= agegrp_min) newList.add(cit);
        }
        return newList;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static File getCitizensFile() {
        return new File(Objects.requireNonNull(
                Citizens.class.getClassLoader()
                        .getResource("EWR_Ortsteile_2016.csv")).getFile()
        );
    }

    public static void printCitizens(ArrayList<CitizenGroup> list) {
        System.out.println("Total number of entries: "+list.size());
        for (CitizenGroup cit : list) {
            System.out.println(cit.toString());
        }
    }

}
