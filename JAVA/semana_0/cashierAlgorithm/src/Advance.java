import java.util.Scanner;

public class Advance {
    public double avance(double money) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el monto de avance: ");
        double montoAvance = sc.nextDouble();

        if (montoAvance > 0) {
            money = montoAvance + money;

            System.out.println("LISTO!!!\n");
            System.out.println("----------------------------");
            System.out.println("Su nuevo saldo es: $" + money);
            System.out.println("----------------------------\n");
        }
        else{
            System.out.println("El monto debe ser mayor que 0");
        }
        return money;
    }
}
