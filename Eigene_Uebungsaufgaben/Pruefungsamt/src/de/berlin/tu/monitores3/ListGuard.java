package de.berlin.tu.monitores3;

import java.util.List;

public class ListGuard {

    public ListGuard(List<Student3> list) {
        student3s = list;}

    private List<Student3> student3s;

    public Student3 poll() {
        return student3s.remove(0);
    }

    public void put(Student3 student3) {
        student3s.add(student3);
    }

    public int size() {return student3s.size();}

    public boolean isEmpty() {return student3s.isEmpty();}

}
