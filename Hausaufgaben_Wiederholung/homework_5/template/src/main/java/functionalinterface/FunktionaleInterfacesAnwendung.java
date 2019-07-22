package functionalinterface;

import java.util.Arrays;

public class FunktionaleInterfacesAnwendung {

    public static void main(String[] args) {
        // Erster Teil der Aufgabe
        System.out.println(betterString("String1", "String2", (a, b) -> true));
        System.out.println(betterString("String1Lang", "String2",
                (a, b) -> (a.length() > b.length()) ? true : false));
        System.out.println(betterString("String1Lang", "String2",
                (a, b) -> (countUpperCaseLetters(a) >= countUpperCaseLetters(b)) ? true : false));


        // Zweiter Teil der Aufgabe -- Generisches, funktionales Interface
        GenerischesInterface<Integer> interface1 = new GenerischesInterface<Integer>() {
            @Override
            public boolean compare(Integer t1, Integer t2) {
                return t1 > t2;
            }
        };
        System.out.println(betterInstance(10, 15, interface1));

        GenerischesInterface<String[]> interface2 = new GenerischesInterface<String[]>() {
            @Override
            public boolean compare(String[] t1, String[] t2) {
                return t1.length > t2.length;
            }
        };
        String[] arr1 = {"Kurzes", "Array"};
        String[] arr2 = {"Etwas", "längeres", "Array"};
        System.out.println(betterInstance(arr1, arr2, interface2).length);
    }

    /**
     *
     * @param string1 Erster zu vergleichender String
     * @param string2 Zweiter zu vergleichender String
     * @param eigenesInterface Functional Interface zum Vergleichen zweier Strings
     * @return
     */
    static String betterString(String string1, String string2, EigenesInterface eigenesInterface) {
        return eigenesInterface.compare(string1, string2) ? string1 : string2;
    }

    static int countUpperCaseLetters(String word) {
        return (int) word.chars().filter(Character::isUpperCase).count();
    }

    static <T> T betterInstance(T t1, T t2, GenerischesInterface<T> generischesInterface) {
        return generischesInterface.compare(t1, t2) ? t1 : t2;
    }

}
