import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Type your age");
        byte age = sc.nextByte();

        if ( age < 12 ) {
            System.out.println("You are a kid");
        } else if ( age > 12  && age < 17 ) {
            System.out.println("You are a teenager");
        } else if ( age > 18 ) {
            System.out.println("You are an adult");
        }
    }
}