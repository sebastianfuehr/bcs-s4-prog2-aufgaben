package scala2java;

public class Person2 {

    // Alle Attribute im Klassenkopf sind automatisch Vals.
    // Außer man schreibt var davor.
    private final String name;
    private final int age;

    // Primärer Konstruktor
    public Person2(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name() {return name;}

    public int age() {return age;}

}
