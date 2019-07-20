package functionalinterface;

import static java.lang.Character.isUpperCase;

public class ReadMeNot {

    public static void main(String[] args) {
        System.out.println("Funktionales Interface mit Strings:");
        // Es wird immer der erste String zurückgegeben.
        System.out.println(betterString("TestString1", "TestString2",
                new EigenesInterface() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        return true;
                    }
                }));
        System.out.println(betterString("s1","s2",(a,b)->true));
        // Es wird immer der längere String zurückgegeben. (Oder der erste, wenn beide gleich lang.
        System.out.println(betterString("TestString1","TestStringZwei",
                new EigenesInterface() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        return (string1.length()>string2.length());
                    }
                }));
        System.out.println(betterString("s1","s2",(a,b)->a.length()>b.length()));
        // Es wird der String mit den meisten Großbuchstaben zurückgegeben
        System.out.println(betterString("TestString1","Teststring2",
                new EigenesInterface() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        int amtUpperCaseString1 = 0,
                                amtUpperCaseString2 = 0;
                        char[] arr1 = string1.toCharArray(),
                                arr2 = string2.toCharArray();
                        for (char curr: arr1) if (isUpperCase(curr)) amtUpperCaseString1++;
                        for (char curr: arr2) if (isUpperCase(curr)) amtUpperCaseString2++;
                        return (amtUpperCaseString1 > amtUpperCaseString2);
                    }
                }));
        System.out.println(betterString("s1","s2",
                (a,b)->a.chars().filter(Character::isUpperCase).count() >
                b.chars().filter(Character::isUpperCase).count())); // Character::isUpperCase ersetzt c -> Character.isUpperCase(c)
        System.out.println(betterString("s1","s2",
                (a,b)->a.chars().filter(c->Character.isUpperCase(c)).count() >
                b.chars().filter(c->Character.isUpperCase(c)).count()));

        /*
        ----------------- Generische Typen mit Strings --------------
         */
        System.out.println("Funktionales, generisches Interface mit Strings:");
        // Es wird immer der erste String zurückgegeben.
        System.out.println(betterInstance("TestString1", "TestString2",
                new ZweitesInterface<String>() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        return true;
                    }
                }));
        System.out.println(betterInstance("s1","s2",(a,b)->true));
        // Es wird immer der längere String zurückgegeben. (Oder der erste, wenn beide gleich lang.
        System.out.println(betterInstance("TestString1","TestStringZwei",
                new ZweitesInterface<String>() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        return (string1.length()>string2.length());
                    }
                }));
        // Es wird der String mit den meisten Großbuchstaben zurückgegeben
        System.out.println(betterInstance("TestString1","Teststring2",
                new ZweitesInterface<String>() {
                    @Override
                    public boolean compare(String string1, String string2) {
                        int amtUpperCaseString1 = 0,
                                amtUpperCaseString2 = 0;
                        char[] arr1 = string1.toCharArray(),
                                arr2 = string2.toCharArray();
                        for (char curr: arr1) if (isUpperCase(curr)) amtUpperCaseString1++;
                        for (char curr: arr2) if (isUpperCase(curr)) amtUpperCaseString2++;
                        return (amtUpperCaseString1 > amtUpperCaseString2);
                    }
                }));
        System.out.println(betterInstance("s1","s2",
                (a,b)->a.chars().filter())); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /*
        ----------------- Generische Typen -----------------
         */
        System.out.println("Funktionales, generisches Interface mit anderen Datentypen:");
        // Es wird der größere Integer zurückgegeben.
        System.out.println(betterInstance(10, 15,
                new ZweitesInterface<Integer>() {
                    @Override
                    public boolean compare(Integer type1, Integer type2) {
                        return (type1 > type2);
                    }
                }));
        System.out.print("Array mit ");
        System.out.print(betterInstance(new int[] {1, 2, 3, 4}, new int[] {1,2,3},
                new ZweitesInterface<int[]>() {
                    @Override
                    public boolean compare(int[] type1, int[] type2) {
                        return (type1.length < type2.length);
                    }
                }).length);
        System.out.println(" Elementen.");
    }

    public static String betterString(String string1, String string2, EigenesInterface predicate){
        return (predicate.compare(string1, string2)) ? string1 : string2;
    }

    static <T> T betterInstance(T type1, T type2, ZweitesInterface<T> predicate) {
        return (predicate.compare(type1, type2)) ? type1 : type2;
    }

}
