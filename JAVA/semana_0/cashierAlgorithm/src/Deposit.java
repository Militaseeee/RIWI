import java.util.Scanner;

public class Deposit {
    public double saveMoney(double money) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el dinero a depositar: ");
        double saveDeposit = sc.nextDouble();

        if (saveDeposit > 0) {
            money = saveDeposit + money;

            System.out.println("LISTO!!!\n");
            System.out.println("----------------------------");
            System.out.println("Su nuevo saldo es: $ " + money);
            System.out.println("----------------------------\n");

        } else {
            System.out.println("El deposito debe ser mayor que 0\n");
        }
        return money;
    }
}