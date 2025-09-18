package app.Model;

import javax.swing.*;
import java.util.ArrayList;

public class Student extends Person {

    private ArrayList<Subject> subjectList;

    public Student(String name, byte age) {
        super(name, age);
        this.subjectList = new ArrayList<>();
    }

    public void addSubject(Subject subjectAdd) {
        try {
            if (this.subjectList.size() >= 4) {
                System.out.println("You cannot add more than 4 subjects");
                return;
            }

            for (Subject subject : subjectList) {
                if (subject.getNameSubject().equalsIgnoreCase(subjectAdd.getNameSubject())) {
                    System.out.println("This subject already exists");
                    return;
                }
            }

            subjectList.add(subjectAdd);
            System.out.println("Subject added successfully: " + subjectAdd.getNameSubject());

        } catch (Exception e) {
            System.out.println("Invalid note format");
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

    @Override
    public void showInformation() {
        StringBuilder info = new StringBuilder();
        info.append("\n=====================================\n");
        info.append("Student Information\n");
        info.append("=====================================\n");
        info.append("Name: ").append(getName()).append("\n");
        info.append("Age : ").append(getAge()).append(" years\n");
        info.append("-------------------------------------\n");
        info.append("-> Subjects:\n");

        if (subjectList.isEmpty()) {
            info.append("  -> No subjects enrolled\n");
        } else {
            for (Subject subject : subjectList) {
                info.append("  • ").append(subject.getNameSubject()).append("\n");
                info.append("    - Note  : ").append(subject.getNote()).append("\n");
                info.append("    - Status: ").append(subject.statusNote()).append("\n");
                info.append("    -----------------------------\n");
            }
        }

        info.append("\n-> Final Average: ")
                .append(String.format("%.2f", calculateAverage()))
                .append("\n");
        info.append("=====================================\n");

        System.out.println(info.toString());
        JOptionPane.showMessageDialog(null, info.toString());

    }
}
