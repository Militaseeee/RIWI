import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 🔹 for -> Patrón en pirámide Usa dos bucles for para imprimir
        //     *
        //    ***
        //   *****
        //  *******

        // First way
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4 + i; j++) {
//                if (j < 4 - i - 1) {
//                    System.out.print(" ");
//                } else {
//                    System.out.print("*");
//                }
//            }
//            System.out.println();
//        }

        // Second way
//        String space = "";
//        for (int i = 0; i < 4; i++) {
//            // we calculate spaces
//            String spaces = " ".repeat(4 - i - 1);
//            // we calculate starts
//            String stars = "*".repeat(2 * i + 1);
//            // armamos la fila
//            space = spaces + stars;
//            System.out.println(space);
//        }

        // 🔹 while -> Inverso de un número Pide un número entero y usando while obtén el número invertido (ejemplo: 12345 → 54321). Luego, indica si es capicúa.

        // First way

//        // Inverso de un número
//        System.out.println("- Inverso de un número -");
//        System.out.println("Ingresa un número entero: ");
//        int number = sc.nextInt();
//
//        String numberString = Integer.toString(number); // Convierte a String el int.
//        char [] numberArray = numberString.toCharArray(); // Convierte el String en un array de caracteres.
//        int lastChar = (numberString.length()) - 1; // Se busca el indice del ultimo caracter en el array.
//        int counter = 0;
//        String reverseNumber = "";
//
//        while (counter < numberString.length()){
//            reverseNumber += numberArray[lastChar];
//            lastChar--;
//            counter++;
//        }
//
//        System.out.println("El número al reves es: "+reverseNumber);
//
//        if (numberString.equals(reverseNumber)){
//            System.out.println("El número "+numberString+" es Capicúa.");
//        }

        // Second way

//        System.out.println("Enter an integer: ");
//        int number = sc.nextInt();
//        int reversed = 0;
//
//        while (number != 0) {
//            int digit = number % 10;
//            reversed = reversed * 10 + digit;
//            number /= 10;
//        }
//        System.out.println("Reversed number: " + reversed);

        // Third way

//        System.out.println("Enter an integer: ");
//        int number = sc.nextInt();
//
//        String text = String.valueOf(number);
//        char[] digits = text.toCharArray();
//        int i = digits.length - 1;
//        String reversedText = "";
//
//        while (i >= 0) {
//            reversedText += digits[i];
//            i--;
//        }
//        System.out.println("Reversed: " + reversedText);

//        // Fourth way

//        System.out.println("Enter an integer: ");
//        int number = sc.nextInt();
//
//        String text = String.valueOf(number);
//        int i = text.length() - 1;
//        String reversedText = "";
//
//        while (i >= 0) {
//            reversedText += text.charAt(i);
//            i--;
//        }
//
//        System.out.println("Reversed: " + reversedText);

        // 🔹 do-while
        // Usa do-while para repetir hasta que elija 0.
        // Número adivinanza con pistas Genera un número aleatorio entre 1 y 100. Usa do-while para que el usuario intente adivinar. Si el número está a ±5 del secreto, muestra "¡Muy cerca!".

//        int randomNumber = (int) (Math.random() * 10) +1;
//        int tryNumber = 10;
//        boolean successes = false;
//
//        System.out.println("Type a number to find the hidden number, and you have: " + tryNumber + " attempts");
//        int typeNumber = sc.nextInt();
//
//        do {
//            System.out.print("Enter a number: ");
//            typeNumber = sc.nextInt();
//            tryNumber--;
//
//            if (typeNumber == randomNumber) {
//                System.out.println("Correct! The number was " + randomNumber);
//                successes = true;
//                break;
//            } else if (Math.abs(typeNumber - randomNumber) <= 5) {
//                System.out.println("Very close! Try again. Attempts left: " + tryNumber);
//            } else if (typeNumber < randomNumber) {
//                System.out.println("The hidden number is bigger. Attempts left: " + tryNumber);
//            } else {
//                System.out.println("The hidden number is smaller. Attempts left: " + tryNumber);
//            }
//        } while (typeNumber > 0 && !successes);
//
//        if (!successes) {
//            System.out.println("You lost! The hidden number was: " + randomNumber);
//        }

        // 🔹 for-each -> Frecuencia de palabras Dado un arreglo de String, muestra cuántas veces aparece cada palabra. Ejemplo: ["hola", "mundo", "hola"] → hola=2, mundo=1. Reto extra: ignora mayúsculas/minúsculas.

        String [] words = {"Hello", "world", "Camila", "camila", "World", "Hello", "hello", "hello"};
        ArrayList<String> counted = new ArrayList<>();

        for (String word : words) {
            String lowerWord = word.toLowerCase();
            // Contains se usa en colecciones como ArrayList para verificar si ya existe un elemento dentro de la lista.
            if (!counted.contains(lowerWord)) {
                int count = 0;
                for (String amount : words) {
                    if (amount.toLowerCase().equals(lowerWord)) {
                        count++;
                    }
                }
                System.out.println(lowerWord + " = " + count);
                counted.add(lowerWord);
            }
        }

    }
}