package examples_use;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Arrays - Example
        int[] numeros = {10, 20, 30, 40};

        // Recorriendo con for tradicional
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Elemento en posición " + i + ": " + numeros[i]);
        }
        System.out.println("\n");

        int position = 0;
        for (int i : numeros) {
            System.out.println("Elemento en posición " + position + ": " + i);
            position++;
        }
        System.out.println("\n");

        // Recorriendo con for-each
        for (int num : numeros) {
            System.out.println("Número: " + num);
        }
        System.out.println("\n");

        // 2. ArrayList - Example
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Manzana");
        frutas.add("Banana");
        frutas.add("Mango");

        // Recorriendo con for tradicional
        // -> Size: devuelve el número de elementos que hay en la lista
        for (int i = 0; i < frutas.size(); i++) {
            System.out.println("Fruta en posición " + i + ": " + frutas.get(i));
        }
        System.out.println("\n");

        // Recorriendo con for-each
        for (String fruta : frutas) {
            System.out.println("Fruta: " + fruta);
        }
        System.out.println("\n");

        // 3. HashMap - Example
        HashMap<Integer, String> estudiantes = new HashMap<>();
        estudiantes.put(1, "Ana");
        estudiantes.put(2, "Luis");
        estudiantes.put(3, "Maria");

        // Recorriendo solo las claves
        for (Integer clave : estudiantes.keySet()) {
            System.out.println("Clave: " + clave);
        }
        System.out.println("\n");

        // Recorriendo solo los valores
        for (String nombre : estudiantes.values()) {
            System.out.println("Nombre: " + nombre);
        }
        System.out.println("\n");

        // Recorriendo clave → valor

        // 1
        for (var entry : estudiantes.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + " → Valor: " + entry.getValue());
        }
        System.out.println("\n");

        // 2
        for (Map.Entry<Integer, String> result : estudiantes.entrySet()) {
            System.out.println("Clave: " + result.getKey() + " → Valor: " + result.getValue());
        }
        System.out.println("\n");
    }
}