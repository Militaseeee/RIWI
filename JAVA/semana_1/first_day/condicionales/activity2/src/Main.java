import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Type a number");
        byte n = sc.nextByte();

        if ( n > 0 ) {
            System.out.println("Number " + n + " is positive");
        } else if ( n < 0) {
            System.out.println("Number " + n + " is negative");
        } else if (n == 0) {
            System.out.println("Number " + n + " is exactly 0");
        }

    }
}