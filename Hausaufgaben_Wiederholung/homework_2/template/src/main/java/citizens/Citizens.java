package citizens;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Citizens {

    /**
     * Returns the java file representation of the "EWR_Ortsteile_2016.csv" file.
     * @return java.io.File
     */
    public static File getCitizensFile() {
        return new File(Objects.requireNonNull(
                Citizens.class.getClassLoader()
                        .getResource("EWR_Ortsteile_Berlin_2015.csv")).getFile()
        );
    }

    /**
     * Reads csv file and returns array list, filled with citizen representations of the file.
     * @return List of Citizen entities.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Citizen> readFile() throws FileNotFoundException, IOException {
        File file = Citizens.getCitizensFile();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw e;
        }

        ArrayList<Citizen> list = new ArrayList<>();
        try {
            String line;
            int idx = 0;
            while ((line = br.readLine()) != null) {
                if (idx != 0) list.add(getCitizenFromString(line, idx));
                idx++;
            }
        } catch(IOException e) {
            throw e;
        }

        return list;
    }

    /**
     * Generates a citizen instance which represents the given string.
     * @param citString String to be converted into a Citizen instance.
     * @return Citizen
     */
    private static Citizen getCitizenFromString(String citString, int idx) {
        String[] arr = citString.split(";");
        return new Citizen(idx,
                Integer.parseInt(arr[0]),
                arr[1],
                Integer.parseInt(arr[2]),
                arr[3],
                Integer.parseInt(arr[4]),
                arr[5].charAt(0),
                arr[6],
                Double.parseDouble(arr[7].replace(',','.')));
    }


    protected static void addValuesToMap(HashMap<String, Double> map, Citizen cit) {
        if (map.containsKey(cit.getOrtsteilName())) {
            map.put(cit.getOrtsteilName(), map.get(cit.getOrtsteilName()) + cit.getHaeufigkeit());
        } else {
            map.put(cit.getOrtsteilName(), cit.getHaeufigkeit());
        }
    }

    protected static void printOrtsteile() {
        ArrayList<Citizen> list;
        try {
            list = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be fond.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<String> orte = new ArrayList<>();
        list.stream().forEach(cit -> {
            if (!orte.contains(cit.getOrtsteilName())) {
                orte.add(cit.getOrtsteilName());
            }
        });
        System.out.println("Ortsteile in EWR_Ortsteile_2016.csv:");
        orte.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
    }
}
