import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Type first number: ");
        int n1 = sc.nextInt();

        System.out.println("Type second number: ");
        int n2 = sc.nextInt();

        if ( n1 > 0 && n2 > 0 ) {
            System.out.println("Both number are positive");
        } else if ( n1 > 100 || n2 > 100 ) {
            System.out.println("At least one number is greater than 100");
        } else if (n1 != n2) {
            System.out.println("The number " + n1 + " is different " + n2);
        } else {
            System.out.println("IDK");
        }

    }
}