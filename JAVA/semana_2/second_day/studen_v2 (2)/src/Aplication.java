import app.Model.Student;
import app.Model.Subject;

import javax.swing.*;

public class Aplication {

    public void execute() {

        for (int i = 1; i <= 3; i++) {
            JOptionPane.showMessageDialog(null, "=== Student Data " + i + " ===");

            String name = JOptionPane.showInputDialog(null, "Enter the student's name:");
            if(name == null){
                return;
            }
            String ageInput = JOptionPane.showInputDialog(null, "Enter the student's age:");
            if (ageInput == null) {
                return;
            }

            if (ageInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must enter an age.");
                return;
            }

            byte age = Byte.parseByte(ageInput);

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