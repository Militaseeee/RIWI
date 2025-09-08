import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        boolean first_time;
        double basic = 80.0;
        double plus = 120.0;
        double premium = 180.0;
        byte discount = 10;
        double total = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Type your name: ");
        String name = sc.nextLine();
        System.out.println("Type your age: ");
        byte age = sc.nextByte();
        System.out.println("Type your height in meters");
        double height = sc.nextDouble();
        System.out.println("Type your weight in kg");
        double weight = sc.nextDouble();

        sc.nextLine();

        System.out.println("Chose your plan");
        System.out.println("BASIC");
        System.out.println("PLUS");
        System.out.println("PREMIUM");
        String plan = sc.nextLine();

        System.out.println("first time");
        System.out.println("1 - true");
        System.out.println("2 - false");
        byte chose = sc.nextByte();

        if ( plan == "BASIC" ) {
            System.out.println("You should pay " + basic);
        } else if ( plan == "PLUS" ) {
            System.out.println("You should pay " + plus);
        } else if ( plan == "PREMIUM" ) {
            System.out.println("You should pay " + premium);
        }

        if ( chose == 1 ) {
            first_time = true;
            System.out.println("You have a discount of " + discount);

            if ( plan == "BASIC" ) {
                System.out.println("You should pay " + basic);
                total = (basic * discount) / 100.0;
            } else if ( plan == "PLUS" ) {
                System.out.println("You should pay " + plus);
                total = (plus * discount) / 100.0;
            } else if ( plan == "PREMIUM" ) {
                System.out.println("You should pay " + premium);
                total = (premium * discount) / 100.0;
            }

            System.out.println("You have pay: " + total);

        } else if ( age > 16 && age < 25 ) {
            System.out.println("You have a discount of " + discount);

            if ( plan == "BASIC" ) {
                System.out.println("You should pay " + basic);
                total = (basic * discount) / 100.0;
            } else if ( plan == "PLUS" ) {
                System.out.println("You should pay " + plus);
                total = (plus * discount) / 100.0;
            } else if ( plan == "PREMIUM" ) {
                System.out.println("You should pay " + premium);
                total = (premium * discount) / 100.0;
            }

            System.out.println("You have pay: " + total);

        } else if ( discount > 20 ) {
            System.out.println("You can't have more 20%");
        }

        if ( age < 14 ) {
            System.out.println("Not eligible");
        } else if ( age > 14 && age < 18 ) {
            System.out.println("Requires guardian authorization");
        }

        Double bmi = weight / (height * height);

        if ( bmi < 18.5 ) {
            System.out.println("Low weight");
        } else if ( bmi > 18.5 && bmi < 24.9 ) {
            System.out.println("Normal");
        } else if ( bmi > 25 && bmi < 29.9 ) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }

        System.out.println("Your data is: " + name + plan + discount + total + bmi);
    }
}