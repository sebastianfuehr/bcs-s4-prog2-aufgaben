package de.berlin.tu.semaphores;

public class Application {

    public static void main(String[] args) {
        Amt amt = new Amt();

        for (int i=0; i<30; i++) {
            Thread temp = new Thread(new Student(("Student2" + i), i, i+10, amt));
            temp.start();
        }
    }

}
