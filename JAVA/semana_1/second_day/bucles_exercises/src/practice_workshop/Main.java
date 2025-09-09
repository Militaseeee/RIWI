package practice_workshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Practice workshop

        // Arrays:
        // 1. Create an array of 5 integers
        int [] integerArray = { 25, 32, 62, 9, 20 };

        for (int i = 0; i < integerArray.length; i++) {
            System.out.println(integerArray[i]);
        }
        System.out.println("\n");
        for (int i: integerArray) {
            System.out.println(i);
        }
        System.out.println("\n");

        // 2. Recorre el array con un for y muestra cada número multiplicado por 2
        int [] runsArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < runsArray.length; i++) {

            int multiplier = runsArray[i] * 2;

            System.out.println("Number: " + runsArray[i] + " multiplied is: " + multiplier);
        }
        System.out.println("\n");

        for ( int i : runsArray) {
            int multiplier = i * 2;
            System.out.println("Number: " + i + " multiplied is: " + multiplier);
        }
        System.out.println("\n");

        // 3. Luego crea un array sobre numeros con 6 posiciones de espacio y luego llenalo pidiendole al usuario por consola los numeros uno a uno
        int[] sixPositions = new int[6];
        

    }
}
