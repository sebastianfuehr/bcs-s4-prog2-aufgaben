package de.berlin.tu.monitores3;

public class Student3 {

    private String name;

    public Student3(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public void goToAdministrationOffice() {
        synchronized (Application.students) {
            try {
                while (Application.students.size() > 4) Application.students.wait();
                Application.students.put(this);
                System.out.println(name+" ist dran!");
                Application.students.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
