package de.berlin.tu.monitores2;

public class Student2 implements Runnable {

    public String name;
    public Amt2 amt2;

    public Student2(String name, Amt2 amt2) {
        this.name = name;
        this.amt2 = amt2;
    }

    @Override
    public void run() {
        synchronized (amt2) {
            try {
                while (!amt2.canStudentEnter()) amt2.wait();
                amt2.addStudent(this);
                System.out.println("Student2 " + name + " wird bearbeitet.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (amt2) {
            amt2.removeStudent(this);
            System.out.println("Student2 "+name+" wurde bearbeitet.");
            amt2.notify();
        }
    }

}
