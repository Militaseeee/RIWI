import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        boolean first_time;
        double basic = 80.0;
        double plus = 120.0;
        double premium = 180.0;
        byte discount = 0; // now we accumulate discounts
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

        sc.nextLine(); // clear buffer

        System.out.println("\nChose your plan");
        System.out.println("BASIC");
        System.out.println("PLUS");
        System.out.println("PREMIUM");
        String plan = sc.nextLine();

        System.out.println("first time");
        System.out.println("1 - true");
        System.out.println("2 - false");
        byte chose = sc.nextByte();

        double basePrice = 0; // Determine base price
        if (plan.equalsIgnoreCase("BASIC")) {
            basePrice = basic;
        } else if (plan.equalsIgnoreCase("PLUS")) {
            basePrice = plus;
        } else if (plan.equalsIgnoreCase("PREMIUM")) {
            basePrice = premium;
        }

        // Discounts
        if (chose == 1) {
            discount += 10; // first time
        }
        if (age >= 16 && age <= 25) {
            discount += 10; // young
        }

        if (discount > 20) {
            discount = 20; // 20% cap
        }

        total = basePrice - (basePrice * discount / 100.0);

        if (age < 14) {
            System.out.println("Not eligible");
        } else if (age < 18) {
            System.out.println("Requires guardian authorization");
        }

        double bmi = weight / (height * height);

        if (bmi < 18.5) {
            System.out.println("Low weight");
        } else if (bmi <= 24.9) {
            System.out.println("Normal");
        } else if (bmi <= 29.9) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }

        System.out.println(" ------------------------- ");
        System.out.println("Name: " + name);
        System.out.println("Plan: " + plan);
        System.out.println("Base price: $" + basePrice);
        System.out.println("Discount applied: " + discount + "%");
        System.out.println("Final price: $" + total);
        System.out.println("BMI: " + bmi);
        System.out.println(" ------------------------- ");
    }
}