import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Array – El Cofre Ordenado
        int number [] = {2, 5, 8, 15, 24};

        System.out.println("First number: " + number[0]);
        System.out.println("Second numero: " + number[number.length - 1]);

        number [1] = 13;
        System.out.println("The new value of position 2 is: " + number[1]);
        System.out.println("\n");


        // 2. ArrayList – La Mochila Mágica

        // -> Usa el metodo .add() para guardar, .get() para sacar y .remove() para eliminar
        ArrayList<String> bag = new ArrayList<>();
        bag.add("sword");
        bag.add("map");
        bag.add("position");

        System.out.println("Second object: " + bag.get(1));
        bag.remove(0);
        System.out.println("Now the new array is: " + bag);
        System.out.println("\n");


        // 3. HashMap – El Mapa del Tesoro
        HashMap<String, Integer> treasures = new HashMap<>();
        treasures.put("Gold", 100); // clave, valor
        treasures.put("Silver", 50);
        treasures.put("Diamonds", 5);

        System.out.println("Diamonds " + treasures.get("Diamonds"));
        treasures.put("Gold", 200);
        System.out.println("The gold has a value of: " + treasures.get("Gold"));
        System.out.println("\n");


        // 4. Desafío Final
        int [] secretsCodes = { 123, 456, 789 };

        ArrayList<String> nameExplorers = new ArrayList<>();
        nameExplorers.add("Cristian");
        nameExplorers.add("Camila");
        nameExplorers.add("Pablo");

        HashMap<String, Integer> explorersGold = new HashMap<>();
        explorersGold.put(nameExplorers.get(0), 20);
        explorersGold.put(nameExplorers.get(1), 50);
        explorersGold.put(nameExplorers.get(2), 10);

        if ( explorersGold.get(nameExplorers.get(0)) > explorersGold.get(nameExplorers.get(1))
                && explorersGold.get(nameExplorers.get(0)) > explorersGold.get(nameExplorers.get(2)) ) {
            System.out.println("The explorer: " + nameExplorers.get(0) + " has more money");
        } else if ( explorersGold.get(nameExplorers.get(1)) > explorersGold.get(nameExplorers.get(0))
                && explorersGold.get(nameExplorers.get(1)) > explorersGold.get(nameExplorers.get(2)) ) {
            System.out.println("The explorer: " + nameExplorers.get(1) + " has more money");
        } else if ( explorersGold.get(nameExplorers.get(2)) > explorersGold.get(nameExplorers.get(0))
                && explorersGold.get(nameExplorers.get(2)) > explorersGold.get(nameExplorers.get(1)) ) {
            System.out.println("The explorer: " + nameExplorers.get(2) + " has more money");
        } else if ( explorersGold.get(nameExplorers.get(0)) == explorersGold.get(nameExplorers.get(1))
                && explorersGold.get(nameExplorers.get(0)) > explorersGold.get(nameExplorers.get(2)) ) {
            System.out.println("Explorers " + nameExplorers.get(0) + " and " + nameExplorers.get(1) + " are tied with " + explorersGold.get(nameExplorers.get(0)) + " gold");
        } else if ( explorersGold.get(nameExplorers.get(0)) == explorersGold.get(nameExplorers.get(2))
                && explorersGold.get(nameExplorers.get(0)) > explorersGold.get(nameExplorers.get(1)) ) {
            System.out.println("Explorers " + nameExplorers.get(0) + " and " + nameExplorers.get(2) + " are tied with " + explorersGold.get(nameExplorers.get(0)) + " gold");
        } else if ( explorersGold.get(nameExplorers.get(1)) == explorersGold.get(nameExplorers.get(2))
                && explorersGold.get(nameExplorers.get(1)) > explorersGold.get(nameExplorers.get(0)) ) {
            System.out.println("Explorers " + nameExplorers.get(1) + " and " + nameExplorers.get(2) + " are tied with " + explorersGold.get(nameExplorers.get(1)) + " gold");
        } else {
            System.out.println("All explorers are tied with " + explorersGold.get(nameExplorers.get(0) + " gold"));
        }
    }
}