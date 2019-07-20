package scala2java;

public class User {

    public final String name;

    // Primärer Konstruktor
    public User(String n) {
        this.name = n;
    }

    public void greet(String str) {
        System.out.println("Hello from "+name);
    }

    public String toString() {
        return ("User " + name);
    }

}
