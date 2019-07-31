package de.berlin.tu.monitores2;

/* Is like a producer. */
public class Application2 {

    public static void main(String[] args) {
        Amt2 amt2 = new Amt2();

        for (int i=0; i<20; i++) {
            new Thread(new Student2("Student2-"+i, amt2)).start();
        }
        System.out.println("Alle Studenten wollen gleichzeitig zum Prüfungsamt.");

    }

}