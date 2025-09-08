import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String name = "Camila";
        byte age = 22;
        double height = 1.59;
        boolean student = true;
        char firstLetter = 'C';

        System.out.println("Hi, mi name is " + name + ". I am " + age + " years old" + ", my height is " + height + " and my first letter is " + firstLetter);

        String name1;
        byte age1;
        double height1;
        int student1;
        char firstLetter1;

        Scanner sc = new Scanner(System.in);

        System.out.println("Type your name");
        name1 = sc.nextLine();

        System.out.println("Type your age");
        age1 = sc.nextByte();

        System.out.println("Type your height");
        height1 = sc.nextDouble();

        System.out.println("Type if you are a student");
        System.out.println("1 - student");
        System.out.println("2 - no student");
        student1 = sc.nextInt();

        boolean isStudent;

        if (student1 == 1) {
            isStudent = true;
        } else if (student1 == 2) {
            isStudent = false;
        } else {
            System.out.println("Incorrect data");
            isStudent = false;
        }

        System.out.println("Type the first letter of your name");
        firstLetter1 = sc.next().charAt(0);

        if (Character.isLetter(firstLetter)) {
            System.out.println("Correct, it's a letter");
        } else {
            System.out.println("Incorrect, not a letter");
        }

        System.out.println("Hi, mi name is " + name1 + ". I am " + age1 + " years old" + ", my height is " + height1 + " and my first letter is " + firstLetter1);
    }
}