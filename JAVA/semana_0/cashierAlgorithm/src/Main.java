import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Se usa para leer datos que el usuario escriba en la consola

        int opc = 0;
        double money = 1000000;
        boolean hizoAvance = false;

        while (opc != 5) {

            System.out.println("RIWI BANK\n");
            System.out.println("Selecciona una opción:\n");

            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Avance de dinero");
            System.out.println("5. Salir");

            opc = sc.nextInt();
            System.out.println("");

            switch (opc) {
                case 1:
                    Consult consulta = new Consult();
                    consulta.mostrarSaldo(money);
                    break;
                case 2:
                    Deposit deposit = new Deposit();
                    money = deposit.saveMoney(money);
                    break;
                case 3:
                    Withdraw withdraw = new Withdraw();
                    money = withdraw.retirar(money, hizoAvance);

                    if (money < 0 && hizoAvance) {
                        System.out.println("Saldo negativo, recuerda pagar el avance anteriormente realizado\n");
                    }
                    break;
                case 4:
                    Advance advance = new Advance();
                    money = advance.avance(money);
                    hizoAvance = true;

                    break;
                case 5:
                    System.out.println("Adiossss vuelva pronto c: \n");
                    break;
                default:
                    System.out.println("Opción inválida!!!\n");
            }
        }

    }
}