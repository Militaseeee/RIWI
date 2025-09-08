import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        byte age;

        System.out.println("Type your age");
        age = sc.nextByte();

        if (age >= 18) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}