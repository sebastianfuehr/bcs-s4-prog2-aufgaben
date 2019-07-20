package scala2java;

public class Person {
    /*
     Instanzvariablen sind in Scala default private und
     besitzen Getter- und Setter-Methoden. Mit dem
     Schlüsselwort private wird dafür gesorgt, dass
     keine Setter- und Getter-Methoden generiert werden.
     */
    private String name = "";
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

    public String name() {return name;}

    public void _name(String name) {this.name = name;}

}
