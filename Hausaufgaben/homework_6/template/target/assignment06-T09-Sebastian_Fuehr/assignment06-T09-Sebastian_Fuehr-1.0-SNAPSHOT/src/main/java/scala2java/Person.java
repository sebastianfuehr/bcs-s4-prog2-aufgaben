package scala2java;

public class Person {

    public String name = "";
    private int age = 0;

    // Primärer Konstruktor
    public Person() { }

    // Hilfskonstruktor 1
    public Person(String name) {
        this();
        this.name=name;
    }

    // Hilfskonstruktor 2
    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

}
