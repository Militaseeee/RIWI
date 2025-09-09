import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Verificar si un texto son solo números ()
        String input = "12345";
        if (input.matches("\\d+")) {
            System.out.println("Es un número válido\n");
        } else {
            System.out.println("No es un número\n");
        }

        // Validar si una palabra contiene solo letras
        String nombre = "Camila";
        if (nombre.matches("[a-zA-Z]+")) {
            System.out.println("El nombre es válido\n");
        } else {
            System.out.println("El nombre contiene caracteres no válidos\n");
        }

        // Comprobar si una cadena tiene exactamente 4 dígitos (ej: un PIN):
        String pin = "1234";
        if (pin.matches("\\d{4}")) {
            System.out.println("PIN válido\n");
        } else {
            System.out.println("PIN inválido\n");
        }

        // Validar un correo electrónico (regex sencillo):
        String email = "prueba@gmail.com";
        if (email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Correo válido\n");
        } else {
            System.out.println("Correo inválido\n");
        }

        String repeat = "Hi";
        System.out.println(repeat.repeat(5));
        System.out.println("\n");

        String withStrip = " Practice\n       ";
        System.out.println(withStrip.strip());

        // Char
        char [] viewArray = "Hello\n".toCharArray();
        for (char i : viewArray) {
            System.out.print(i);
        }
        System.out.println("\n");

        // String
        String [] viewArrayString = "Camila".split(""); // SPLIT -> Divide en un arreglo según separador
        for (String a : viewArrayString) {
            System.out.print(a);
        }
        System.out.println("\n");

        // Convierte un arreglo en bytes
        String word = "word";
        byte [] bytes = word.getBytes();
        System.out.println(Arrays.toString(bytes));
        System.out.println("\n");

        // Convierte cualquier valor a String
        int number = 123;
        String text = String.valueOf(number);
        System.out.println(text);
        System.out.println("\n");

        String colores = "rojo,verde,azul";
        String[] partes = colores.split(",");
        for (String color : partes) {
            System.out.println(color);
        }
        System.out.println("\n");

        // DATOS COMPUESTOS!!!

        // 1. String
        String greet = "Hello all!!!";
        System.out.println(greet.toUpperCase());
        System.out.println("\n");

        // 2. Array
        int [] numberArray = {1, 2, 3, 4, 5};
        System.out.println("Elemt in position 0: " + numberArray[0]);
        for (int n : numberArray){
            System.out.println(n);
        }
        // OJO: el tamaño es fijo, una vez creado, no puedes agrandarlo ni achicarlo
        System.out.println("\n");

        // 3. ArrayList
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Luis");
        System.out.println("Primer nombre: " + nombres.get(0));
        for (String names : nombres) {
            System.out.println(names);
        }
        // OJO: Tamaño dinámico: puedes agregar (add) o quitar (remove) elementos libremente.
        // Se accede con .get(índice)
        // Tiene muchos métodos útiles: add, remove, contains, size, etc.
        System.out.println("\n");

        // Diferencia principal:
        // Array = estructura fija y simple.
        // ArrayList = lista flexible con más funcionalidades.
        System.out.println("\n");

        // 4. HashMap == Colección

        // Es una estructura de datos que guarda pares clave → valor (key → value)
        HashMap<String, Integer> edades = new HashMap<>();
        edades.put("Carlos", 25); // clave, valor
        edades.put("María", 30);
        edades.put("Camila", 23);

        System.out.println("Edad de María: " + edades.get("María"));
        System.out.println("\n");

        // Recorrer solo la clave
        for ( String nameHashMap:  edades.keySet()) {
            System.out.println("Clave: " + nameHashMap);
        }
        System.out.println("\n");

        // Recorrer solo el valor
        for (Integer ageHashMap : edades.values()) {
            System.out.println("Valor: " + ageHashMap);
        }
        System.out.println("\n");

        // Recorrer clave y valor
        for (Map.Entry <String, Integer> result : edades.entrySet()) {
            System.out.println("Name: " + result.getKey() + " Age: " + result.getValue());
        }
        System.out.println("\n");

        // 5. Enum

        // Es una forma especial de crear constantes con nombre
        enum Dia { LUNES, MARTES, MIERCOLES, JUEVES, VIERNES }

        Dia hoy = Dia.MARTES;
        System.out.println("Hoy es: " + hoy);
        System.out.println("\n");

        switch (hoy) {
            case LUNES:
                System.out.println("Ánimo, empieza la semana 💪");
                break;
            case MARTES:
                System.out.println("Segundo día, seguimos con fuerza 🚀");
                break;
            case MIERCOLES:
                System.out.println("Mitad de semana 😅");
                break;
            case JUEVES:
                System.out.println("Casi es viernes 🎉");
                break;
            case VIERNES:
                System.out.println("¡Por fin viernes! 🎊");
                break;
            default:
                System.out.println("Día no válido");
        }
        System.out.println("\n");

        for ( Dia d : Dia.values()) {
            System.out.println(d);
        }
        System.out.println("\n");

        // 6. Class
        Persona p = new Persona();
        p.nombre = "Lucía";
        p.edad = 22;
        p.saludar();
    }
}

class Persona {
    String nombre;
    int edad;

    void saludar() {
        System.out.println("Hola, me llamo " + nombre);
    }
}