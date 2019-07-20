package foreach;

import java.util.Iterator;
import java.util.List;

public class ForEach {
    static void forLoop1A(String[] names) {
        for(int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    static void forLoop1B(String[] names) {
        // TODO implement me!
    }

    static void forLoop2A(List<String> names) {
        for(Iterator<String> it = names.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

    static void forLoop2B(List<String> names) {
        // TODO implement me!
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
    }
}
