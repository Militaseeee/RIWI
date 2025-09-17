package app.Model;

import javax.swing.*;
import java.util.ArrayList;

public class Student extends Person {

    private ArrayList<Subject> subjectList;

    public Student(String name, byte age) {
        super(name, age);
        this.subjectList = new ArrayList<>();
    }

    public void addSubject() {

        try {
            if (this.subjectList.size() >= 4) {
                JOptionPane.showMessageDialog(null, "You cannot add more than 4 subjects");
                return;
            }

            String nameSubject = JOptionPane.showInputDialog(null, "Type the subject: ");
            double noteSubject = Double.parseDouble(JOptionPane.showInputDialog(null, "Type the note each subject:"));

            nameSubject = nameSubject.toLowerCase();

            for (Subject subject : subjectList) {
                if (subject.getNameSubject().equals(nameSubject)) {
                    JOptionPane.showMessageDialog(null, "This subject already exists");
                    return;
                }
            }

            Subject showSubject = new Subject(nameSubject, noteSubject);
            subjectList.add(showSubject);
            JOptionPane.showMessageDialog(null, "Subject added successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid format");
        }
    }

    public double calculateAverage() {
        if (subjectList.isEmpty()) {
            return 0;
        }

        double add = 0;
        for (Subject avergare : subjectList) {
            add += avergare.getNote();
        }
        return add / subjectList.size();

    }

}
