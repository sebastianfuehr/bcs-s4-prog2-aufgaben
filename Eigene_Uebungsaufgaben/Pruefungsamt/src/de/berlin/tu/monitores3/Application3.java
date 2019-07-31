package de.berlin.tu.monitores3;

import java.util.ArrayList;

public class Application3 {

    public static final ListGuard students = new ListGuard(new ArrayList<>());
    private static int id = 0;

    public static void main(String[] args) {
        Thread pruefungsamt = new Thread(new Pruefungsamt());
        pruefungsamt.start();

        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1);
                System.out.println("Student2-"+id+" ist zum Prüfungsamt gegangen.");
                new Student3(("Student2-"+id)).goToAdministrationOffice();
                id++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

/* Consumer */
class Pruefungsamt implements Runnable {
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (Application3.students) {
                try {
                    while (Application3.students.isEmpty()) Application3.students.wait();
                    Thread.sleep(1000);
                    Student3 temp = Application3.students.poll();
                    System.out.println(temp.getName()+" wurde bearbeitet.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Application3.students.notify();
            }
        }
    }
}