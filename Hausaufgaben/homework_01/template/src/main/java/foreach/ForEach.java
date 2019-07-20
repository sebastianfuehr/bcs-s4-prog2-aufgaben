package foreach;

import java.util.Iterator;
import java.util.List;

public class ForEach {

    /*
    For-Each Schleifen funktionieren ganz ähnlich wie die gewöhnlichen
    For-Schleifen und sind quasi eine Weiterentwicklung. Im Gegensatz zu
    den normalen, verwenden ForEach Schleifen aber keine nach außen
    sichtbare Laufvariable oder Iterator-Objekt, sondern gibt in jeder
    Iteration immer direkt den Wert zurück. Das heißt aber auch, dass
    immer über eine entsprechende Datenstruktur iteriert werden muss die
    das ermöglicht.
     */

    static void forLoop1A(String[] names) {
        for(int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    static void forLoop1B(String[] names) {
        // TODO implement me!
        for(String selected: names) {
            System.out.println(selected);
        }
    }

    static void forLoop2A(List<String> names) {
        for(Iterator<String> it = names.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

    static void forLoop2B(List<String> names) {
        // TODO implement me!
        for(String selected: names) {
            System.out.println(selected);
        }
    }

    static void forLoop3A(int n) {
        int[] arrNumbers = new int[n];
        for (int i = 0; i < arrNumbers.length; i++) {
            arrNumbers[i] = i + 4;
        }

        for (int i: arrNumbers) {
            System.out.println(i);
        }
    }

    static void forLoop3B(int n) {
        int[] arrNumbers = new int[n];
        for (int i = 0; i < arrNumbers.length; i++) {
            arrNumbers[i] = i + 4;
        }
        // TODO implement me!
        for (int i=0; i<arrNumbers.length; i++) {
            System.out.println(arrNumbers[i]);
        }
    }
}
