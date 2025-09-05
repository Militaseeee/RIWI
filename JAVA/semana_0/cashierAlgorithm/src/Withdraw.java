import java.util.Scanner;

public class Withdraw {
    public double retirar(double money, boolean hizoAvance) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el dinero a retirar: ");
        double retiro = sc.nextDouble();

        if (retiro > 0 && (retiro <= money || hizoAvance)) {
            money -= retiro;
            System.out.println("LISTO!!!\n");
            System.out.println("----------------------------");
            System.out.println("Su nuevo saldo es: " + money);
            System.out.println("----------------------------\n");
        } else {
            System.out.println("Saldo insuficiente\n");
        }
        return money;
    }
}
