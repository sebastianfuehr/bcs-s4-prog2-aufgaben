package de.berlin.tu.semaphores;

public class Student implements Runnable {

    public Student(String name, int matrNr, int age, Amt amt) {
        this.name = name;
        this.matrNr = matrNr;
        this.age = age;
        this.amt = amt;
    }

    private String name;
    private int matrNr;
    private int age;
    private Amt amt;

    @Override
    public void run() {
        System.out.println("Ich, "+name+", gehe zum Prüfungsamt.");
        amt.workOnStudent(this);
    }

    public String getName() {return name;}

}
