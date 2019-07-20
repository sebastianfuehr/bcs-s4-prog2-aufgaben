package scala2java;

public class User {

    /*
    Wenn Parameter in der Klasse in Scala übergeben
    werden, sind die Instanzvariablen automatisch
    private! Außerdem heißen die Values dann genau
    so wie im "Klassenkopf".
     */
    private final String name;
    private final String n;

    // Primärer Konstruktor
    public User(String n) {
        this.name = n;
        this.n = n;
    }

    public void greet(String str) {
        System.out.println("Hello from "+name);
    }

    @Override
    public String toString() {
        return ("User " + name);
    }

    // Getter nicht vergessen! In Scala heißen die
    // Getter-Methoden genau so wie die Variable.
    public String name() {return name;}

}
