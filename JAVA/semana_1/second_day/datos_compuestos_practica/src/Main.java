import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Luis");
        System.out.println("Primer nombre: " + nombres.get(0));

        HashMap<String, Integer> edades = new HashMap<>();
        edades.put("Carlos", 25);
        edades.put("María", 30);

        System.out.println("Edad de María: " + edades.get("María"));
    }
}