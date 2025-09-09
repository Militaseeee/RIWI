# Contenido en Markdown con ejemplos de los métodos String en Java
markdown_content = """
# 📘 Métodos de la clase String en Java

En Java, los `String` son objetos y tienen muchos métodos útiles.  
Aquí tienes una lista completa con ejemplos listos para probar.

---

## 🫵 Definiciones`

| Método | Descripción | Ejemplo |
|--------|-------------|---------|
| `length()` | Devuelve la longitud de la cadena | `"Hola".length()` → `4` |
| `charAt(int index)` | Devuelve el carácter en la posición indicada | `"Hola".charAt(1)` → `'o'` |
| `substring(int inicio, int fin)` | Extrae una parte de la cadena | `"Hola".substring(0,2)` → `"Ho"` |
| `toUpperCase()` | Convierte a mayúsculas | `"hola".toUpperCase()` → `"HOLA"` |
| `toLowerCase()` | Convierte a minúsculas | `"HOLA".toLowerCase()` → `"hola"` |
| `trim()` | Elimina espacios en los extremos | `"  hola  ".trim()` → `"hola"` |
| `equals(String str)` | Compara dos cadenas (sensible a mayúsculas) | `"hola".equals("Hola")` → `false` |
| `equalsIgnoreCase(String str)` | Compara ignorando mayúsculas | `"hola".equalsIgnoreCase("Hola")` → `true` |
| `compareTo(String str)` | Compara lexicográficamente | `"a".compareTo("b")` → `-1` |
| `compareToIgnoreCase(String str)` | Igual que arriba pero ignora mayúsculas | `"a".compareToIgnoreCase("A")` → `0` |
| `contains(CharSequence s)` | Verifica si contiene un texto | `"Hola".contains("Ho")` → `true` |
| `startsWith(String prefix)` | Verifica si empieza con el texto | `"Hola".startsWith("Ho")` → `true` |
| `endsWith(String suffix)` | Verifica si termina con el texto | `"Hola".endsWith("la")` → `true` |
| `indexOf(String s)` | Devuelve la primera posición de una subcadena | `"banana".indexOf("na")` → `2` |
| `lastIndexOf(String s)` | Devuelve la última posición de una subcadena | `"banana".lastIndexOf("na")` → `4` |
| `replace(CharSequence viejo, CharSequence nuevo)` | Reemplaza texto | `"Hola".replace("o","a")` → `"Hala"` |
| `replaceAll(String regex, String nuevo)` | Reemplaza usando expresión regular | `"123abc".replaceAll("\\\\d","X")` → `"XXXabc"` |
| `replaceFirst(String regex, String nuevo)` | Reemplaza la primera coincidencia | `"123123".replaceFirst("123","X")` → `"X123"` |
| `split(String regex)` | Divide en un arreglo según separador | `"rojo,verde".split(",")` → `["rojo","verde"]` |
| `matches(String regex)` | Verifica con expresión regular | `"123".matches("\\\\d+")` → `true` |
| `isEmpty()` | Verifica si la cadena está vacía | `"".isEmpty()` → `true` |
| `isBlank()` (Java 11+) | Verifica si está vacía o solo tiene espacios | `"   ".isBlank()` → `true` |
| `repeat(int n)` (Java 11+) | Repite la cadena n veces | `"Hi".repeat(3)` → `"HiHiHi"` |
| `strip()` (Java 11+) | Elimina espacios Unicode en extremos | `"   hola   ".strip()` → `"hola"` |
| `toCharArray()` | Convierte en arreglo de caracteres | `"Hola".toCharArray()` → `['H','o','l','a']` |
| `getBytes()` | Convierte en arreglo de bytes | `"Hola".getBytes()` → `[72,111,108,97]` |
| `intern()` | Devuelve la referencia desde el pool de strings | `"Hola".intern()` → `"Hola"` |
| `format(String fmt, Object... args)` | Formatea texto | `String.format("Edad: %d", 20)` → `"Edad: 20"` |
| `join(CharSequence sep, CharSequence... elements)` | Une cadenas con un separador | `String.join("-", "a","b","c")` → `"a-b-c"` |
| `valueOf(...)` (static) | Convierte cualquier valor a String | `String.valueOf(123)` → `"123"` |

---

## 🔤 Métodos principales de `String`

```java
public class StringMethods {
    public static void main(String[] args) {
        String saludo = "Hola Mundo";

        // Longitud
        System.out.println("length(): " + saludo.length());

        // Caracter en una posición
        System.out.println("charAt(0): " + saludo.charAt(0));

        // Subcadena
        System.out.println("substring(0,4): " + saludo.substring(0,4));

        // Mayúsculas y minúsculas
        System.out.println("toUpperCase(): " + saludo.toUpperCase());
        System.out.println("toLowerCase(): " + saludo.toLowerCase());

        // Eliminar espacios
        String conEspacios = "   Hola   ";
        System.out.println("trim(): '" + conEspacios.trim() + "'");

        // Comparaciones
        String saludo2 = "hola mundo";
        System.out.println("equals: " + saludo.equals(saludo2));
        System.out.println("equalsIgnoreCase: " + saludo.equalsIgnoreCase(saludo2));

        // Comparación lexicográfica
        System.out.println("compareTo: " + "a".compareTo("b"));
        System.out.println("compareToIgnoreCase: " + "a".compareToIgnoreCase("A"));

        // Búsqueda de texto
        System.out.println("contains: " + saludo.contains("Mundo"));
        System.out.println("startsWith: " + saludo.startsWith("Hola"));
        System.out.println("endsWith: " + saludo.endsWith("Mundo"));
        System.out.println("indexOf: " + "banana".indexOf("na"));
        System.out.println("lastIndexOf: " + "banana".lastIndexOf("na"));

        // Reemplazos
        System.out.println("replace: " + saludo.replace("Mundo", "Java"));
        System.out.println("replaceFirst: " + "123123".replaceFirst("123", "X"));
        System.out.println("replaceAll: " + "123abc".replaceAll("\\\\d", "X"));

        // División
        String frase = "rojo,verde,azul";
        String[] colores = frase.split(",");
        for(String color : colores){
            System.out.println("split: " + color);
        }

        // Expresiones regulares
        System.out.println("matches: " + "123".matches("\\\\d+"));

        // Verificar vacío o espacios (Java 11+)
        System.out.println("isEmpty: " + "".isEmpty());
        System.out.println("isBlank (Java 11+): " + "   ".isBlank());

        // Repetir (Java 11+)
        System.out.println("repeat (Java 11+): " + "Hi".repeat(3));

        // strip (Java 11+)
        System.out.println("strip (Java 11+): '" + "   hola   ".strip() + "'");

        // Convertir
        System.out.println("toCharArray: " + java.util.Arrays.toString(saludo.toCharArray()));
        System.out.println("getBytes: " + java.util.Arrays.toString(saludo.getBytes()));

        // Intern
        System.out.println("intern: " + saludo.intern());

        // Formateo
        System.out.println("format: " + String.format("Edad: %d", 20));

        // Unir cadenas
        System.out.println("join: " + String.join("-", "a","b","c"));

        // valueOf
        System.out.println("valueOf: " + String.valueOf(123));
    }
}
