import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int n1;
        int n2;
        float result;
        byte opc;

        Scanner sc = new Scanner(System.in);

        System.out.println("Type first number c:");
        n1 = sc.nextInt();

        System.out.println("Type second number c:");
        n2 = sc.nextInt();

        System.out.println("Type a number to select one option");
        System.out.println("1 - Add");
        System.out.println("2 - Subtract");
        System.out.println("3 - Multiply");
        System.out.println("4 - Divide");
        System.out.println("5 - Modulo");
        opc = sc.nextByte();

        if ( opc == 1 ) {
            result = n1 + n2;
            System.out.println("Add is: " + result);
        } else if ( opc == 2 ) {
            result = n1 - n2;
            System.out.println("Subtract is: " + result);
        } else if ( opc == 3 ) {
            result = n1 * n2;
            System.out.println("Multiply is: " + result);
        } else if ( opc == 4 ) {
            result = (float) n1 / n2;
            System.out.println("Divide is: " + result);
        } else if ( opc == 5 ) {
            result = (float) n1 % n2;
            System.out.println("Modulo is: " + result);
        } else {
            System.out.println("Bad!!! >:V");
        }
    }
}