package loops_exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Loop exercises
        // 1. Crea una aplicación que pida un número y calcule su factorial (El factorial de un número es el producto de todos los enteros entre 1 y el propio número y se representa por el número seguido de un signo de exclamación. Por ejemplo 5! = 1x2x3x4x5=120),

//        System.out.println("Type the number for searching factorial number");
//        int number = sc.nextInt();
//
//        int fact = 1;
//        for (int i = 1; i <= number; i++) {
//            fact *= i;
//
//            System.out.println(number + "! = " + fact);
//        }

        // 2. Crea una aplicación que permita adivinar un número. La aplicación genera un número aleatorio del 1 al 100. A continuación va pidiendo números y va respondiendo si el número a adivinar es mayor o menor que el introducido,a demás de los intentos que te quedan (tienes 10 intentos para acertarlo). El programa termina cuando se acierta el número (además te dice en cuantos intentos lo has acertado), si se llega al limite de intentos te muestra el número que había generado.

//        int randomNumber = (int) (Math.random() * 100) +1;
//        int tryNumber = 10;
//        boolean successes = false;
//
//        System.out.println("Type a number to find the hidden number, and you have: " + tryNumber + " attempts\n");
//        for (int n = 1; n <= tryNumber; n++) {
//            System.out.println("Attempts " + n + ". Type a number: ");
//            int typeNumber = sc.nextInt();
//
//            if (typeNumber < randomNumber) {
//                System.out.println("You are cold, the number is major, you have: " + (tryNumber - n) + " attempts");
//            } else if (typeNumber > randomNumber){
//                System.out.println("You are cold, the number is minor, you have: " + (tryNumber - n) + " attempts");
//            } else if (typeNumber == randomNumber){
//                System.out.println("Fine!!!, the number is: " + randomNumber + " you finish this program with " + tryNumber + " attempts");
//                successes = true;
//                break;
//            }
//        }
//        if (!successes) {
//            System.out.println("You lost all attempts, the random number is: " + randomNumber);
//        }

        // 3. Algoritmo que pida números hasta que se introduzca un cero. Debe imprimir la suma y la media de todos los números introducidos.

//        ArrayList<Integer> showNumber = new ArrayList<>();
//        int add = 0;
//
//        System.out.println("Type a number, when you type 0, the program finish and add all\n");
//
//        System.out.println("Type a number: ");
//        int askNumber = sc.nextInt();
//
//        if (askNumber != 0) {
//
//            showNumber.add(askNumber);
//            for (int i = 0; i < showNumber.size(); i++) {
//
//                System.out.println("Type a number: ");
//                askNumber = sc.nextInt();
//
//                if (askNumber == 0) {
//                    break;
//                }
//                showNumber.add(askNumber);
//            }
//        } else {
//            System.out.println("Error!!!");
//        }
//
//        for (int a : showNumber) {
//            add += a;
//        }
//
//        System.out.println("\nThe add is: " + add);
//        double arithmetic_mean = (double) add / showNumber.size();
//        System.out.println("The arithmetic mean is: " + arithmetic_mean);

        // 4. Realizar un algoritmo que pida números (se pedirá por teclado la cantidad de números a introducir). El programa debe informar de cuantos números introducidos son mayores que 0, menores que 0 e iguales a 0.

//        int major = 0;
//        int minor = 0;
//        int equal = 0;
//
//        System.out.println("How many numbers do you want to enter?");
//        int amount = sc.nextInt();
//
//        for (int n = 0; n < amount; n++) {
//
//            System.out.println("Type a integer number");
//            int inputNumber = sc.nextInt();
//
//            if (inputNumber > 0) {
//                major ++;
//            } else if (inputNumber < 0) {
//                minor++;
//            } else {
//                equal++;
//            }
//        }
//        System.out.println("The amount numbers major than cero is: " + major);
//        System.out.println("The amount numbers minor than cero is: " + minor);
//        System.out.println("The amount numbers equal than cero is: " + equal);

        // 5. Algoritmo que pida caracteres e imprima ‘VOCAL’ si son vocales y ‘NO VOCAL’ en caso contrario, el programa termina cuando se introduce un espacio.

//        System.out.println("Type a leter, when you type a space, program close");
//
//        char space = ' ';
//
//        for (;;) { // THis way is a infinite for
//            char letter = sc.nextLine().charAt(0);
//            if (letter == space) {
//                System.out.println("Bye!!!");
//                break;
//            }
//
//            letter = Character.toLowerCase(letter);
//
//            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
//                System.out.println("VOCAL");
//            } else {
//                System.out.println("NO VOCAL");
//            }
//        }

        // 6. Escribir un programa que imprima todos los números pares entre dos números que se le pidan al usuario.

//        int n1, n2;
//
//        System.out.println("Type first number: ");
//        n1 = sc.nextInt();
//
//        System.out.println("Type second number: ");
//        n2 = sc.nextInt();
//
//        for ( int i = n1; i <= n2; i++ ) {
//
//            if ( i % 2 == 0) {
//                System.out.println(i);
//            }
//        }

        // 7. Realizar una algoritmo que muestre la tabla de multiplicar de un número introducido por teclado.

//        System.out.println("Hello! Enter a number to see its multiplication table: ");
//        int number = sc.nextInt();
//
//        for (int i = number; i <= number; i++) {
//            for (int j = 1; j <= 10; j++) {
//                System.out.println(i + " * " + j + " = " + (i * j));
//            }
//        }

        // 8. Escribe un programa que pida el limite inferior y superior de un intervalo. Si el límite inferior es mayor que el superior lo tiene que volver a pedir. A continuación se van introduciendo números hasta que introduzcamos el 0. Cuando termine el programa dará las siguientes informaciones:

        // La suma de los números que están dentro del intervalo (intervalo abierto). Cuantos números están fuera del intervalo. He informa si hemos introducido algún número igual a los límites del intervalo.

//        // // Ask the user for the interval limits
//        System.out.println("Enter lower limit: ");
//        int lower = sc.nextInt();
//        System.out.println("Enter upper limit: ");
//        int upper = sc.nextInt();
//
//        // Validate that the lower bound is less than the upper bound
//        // We use a for loop without initialization or increment, it works like a while loop
//        for (; lower >= upper ;) {
//            System.out.println("The lower limit must be less than the upper limit. Try again");
//            System.out.println("Enter lower limit: ");
//            lower = sc.nextInt();
//            System.out.println("Enter upper limit: ");
//            upper = sc.nextInt();
//        }
//
//        // Variables for statistics
//        int sumInside = 0;              // sum of numbers within the interval
//        int countOutside = 0;           // how many numbers were outside the interval
//        boolean equalToLimit = false;   // if there was any number equal to the limits
//
//        // We traverse that amount with a for
//        System.out.println("\nHow many numbers do you want to enter?");
//        int amount = sc.nextInt();
//
//        // Read numbers with a for
//        for (int i = 1; i <= amount; i++) {
//            System.out.println("Enter number " + i + ": ");
//            int number = sc.nextInt();
//
//            // Check where the number falls
//            if (number > lower && number < upper) {
//                sumInside += number;
//            } else if (number == lower || number == upper) {
//                equalToLimit = true;
//            } else {
//                countOutside++; // It is out of range
//            }
//        }
//
//        // Show results
//        System.out.println("\n--- Results ---");
//        System.out.println("Sum of numbers inside interval: " + sumInside);
//        System.out.println("Count of numbers outside interval: " + countOutside);
//
//        if (equalToLimit) {
//            System.out.println("At least one number was equal to the limits");
//        } else {
//            System.out.println("No number was equal to the limits");
//        }

        // Second form (easy)

//        int lower, upper;
//
//        do {
//            System.out.println("Enter lower limit: ");
//            lower = sc.nextInt();
//            System.out.println("Enter upper limit: ");
//            upper = sc.nextInt();
//
//            if (lower >= upper) {
//                System.out.println("The lower limit must be less than the upper limit. Try again.\n");
//            }
//        } while (lower >= upper);
//
//        int sumInside = 0;
//        int countOutside = 0;
//        boolean equalToLimit = false;
//
//        System.out.println("\nNow enter numbers (0 to finish):");
//        int number;
//
//        do {
//            number = sc.nextInt();
//            if (number == 0) {
//                break;
//            }
//            if (number > lower && number < upper) {
//                sumInside += number;
//            } else if (number == lower || number == upper) {
//                equalToLimit = true;
//            } else {
//                countOutside++;
//            }
//
//        } while (number != 0);
//
//        System.out.println("\n--- Results ---");
//        System.out.println("Sum of numbers inside interval: " + sumInside);
//        System.out.println("Count of numbers outside interval: " + countOutside);
//
//        if (equalToLimit) {
//            System.out.println("At least one number was equal to the limits");
//        } else {
//            System.out.println("No number was equal to the limits");
//        }

        // 9. Escribe un programa que dados dos números, uno real (base) y un entero positivo (exponente), saque por pantalla el resultado de la potencia. No se puede utilizar el operador de potencia.

        // THIS FORM IS EASY, ONLY I PUT THIS CODE FOR I HAVE IDEA IN A FUTURE
//        System.out.println("Enter the base: ");
//        int base = sc.nextInt();
//
//        System.out.println("Enter the base: ");
//        int exponent = sc.nextInt();
//
//        // Use method Math.pow
//        double power = Math.pow(base, exponent);
//        System.out.println(base + " raised to the power of " + exponent + " is: " + power);

//        System.out.println("Enter the base: ");
//        double base = sc.nextDouble();
//
//        System.out.println("Enter the exponent (positive integer): ");
//        int exponent = sc.nextInt();
//
//        if (exponent < 0) {
//            System.out.println("Error!!! the exponent must be a positive integer");
//        } else {
//            double power = 1; // We start at 1 because it is the neutral of multiplication
//            for (int i = 1; i <= exponent; i++) {
//                power *= base;
//            }
//
//            System.out.println(base + " raised to the power of " + exponent + " is: " + power);
//        }

        // 10. Algoritmo que muestre la tabla de multiplicar de los números 1,2,3,4 y 5.

        for (int i = 1; i <= 5; i++) {
            System.out.println("\nMultiplication Table of " + i);
            for (int j = 1; j <= 10 ; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
        }
    }
}
