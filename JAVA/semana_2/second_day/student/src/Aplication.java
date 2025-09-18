import app.Model.Student;

import app.Model.Student;
import app.Model.Subject;

public class Aplication {
    public void execute() {
        System.out.println("Student 1");
        Student student1 = new Student("Pablito", (byte) 27);
        student1.addSubject(new Subject("Math", 4.0));
        student1.addSubject(new Subject("Chemistry", 3.6));
        student1.addSubject(new Subject("Religion", 4.7));
        System.out.println();

        System.out.println("Student 2");
        Student student2 = new Student("Daniela", (byte) 26);
        student2.addSubject(new Subject("Geography", 4.0));
        student2.addSubject(new Subject("Literature", 3.2));
        student2.addSubject(new Subject("Science", 2.9));
        System.out.println();

        System.out.println("Student 3");
        Student student3 = new Student("El Cri", (byte) 25);
        student3.addSubject(new Subject("English", 5.0));
        student3.addSubject(new Subject("Biology", 3.8));
        student3.addSubject(new Subject("Music", 4.5));
        System.out.println();

        student1.showInformation();
        student2.showInformation();
        student3.showInformation();
    }
}
