package de.berlin.tu.monitores1;

import java.util.LinkedList;
import java.util.Queue;

public class Amt {

    public static Queue<Student> students = new LinkedList<>();

    public void workOnStudent() throws InterruptedException {

        synchronized (students) {

            while (students.isEmpty()) {
                System.out.println("waiting since empty");
                students.wait();
            }

            Student student = students.poll();
            Thread.sleep(500);
            System.out.println(student.name + " wurde bearbeitet.");
            students.notify();
        }
    }

    public void addStudent(Student student) throws InterruptedException {
        synchronized (students) {
            while (students.size() > 4) {
                System.out.println("cant add more");
                students.wait();
            }
            students.add(student);
            students.notify();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while(true) {
                    new Amt().workOnStudent();
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                Student student = new Student("Basti");
                try {
                    new Amt().addStudent(student);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
