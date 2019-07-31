package de.berlin.tu.monitores2;

import java.util.ArrayList;
import java.util.List;

public class Amt2 {

    private List<Student2> student2s = new ArrayList<>();

    public boolean canStudentEnter() {
        return (student2s.size() <= 4);
    }

    public void addStudent(Student2 student2) {
        student2s.add(student2);
    }

    public void removeStudent(Student2 student2) {
        student2s.remove(student2);
    }

}