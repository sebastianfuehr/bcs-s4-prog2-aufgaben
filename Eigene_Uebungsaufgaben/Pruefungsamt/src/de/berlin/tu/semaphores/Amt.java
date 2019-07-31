package de.berlin.tu.semaphores;

import java.util.concurrent.Semaphore;

public class Amt {

    private Semaphore sem = new Semaphore(4);

    public void workOnStudent(Student student) {
        try {
            sem.acquire();
            System.out.println(String.format("Student2 %s wurde bearbeitet.", student.getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

}
