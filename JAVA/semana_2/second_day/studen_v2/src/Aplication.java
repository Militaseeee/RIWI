import app.Model.Student;
import app.Model.Subject;

import javax.swing.*;

public class Aplication {

    public void execute() {

        for (int i = 1; i <= 3; i++) {
            JOptionPane.showMessageDialog(null, "=== Student Data " + i + " ===");

            String name = JOptionPane.showInputDialog(null, "Enter the student's name:");
            byte age = Byte.parseByte(JOptionPane.showInputDialog(null, "Enter the student's age:"));

            Student student = new Student(name, age);

            for (int j = 1; j <= 3; j++) {
                String subjectName = JOptionPane.showInputDialog(null, "Enter the name of the subject " + j + ":");
                double note = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the note of " + subjectName + ":"));

                student.addSubject(new Subject(subjectName, note));
            }

            student.showInformation();
        }
    }
}