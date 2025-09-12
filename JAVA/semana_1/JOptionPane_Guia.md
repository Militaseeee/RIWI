# JOptionPane en Java

`JOptionPane` es una clase de **Swing** en Java que permite crear
**cuadros de diálogo** para interactuar con el usuario mediante ventanas
emergentes.

Se encuentra en el paquete:

``` java
import javax.swing.JOptionPane;
```

------------------------------------------------------------------------

## Métodos principales

### 1. `showMessageDialog`

Muestra un cuadro de diálogo con un mensaje.

``` java
JOptionPane.showMessageDialog(null, "Hola, este es un mensaje.");
```

Parámetros: - `null`: indica la ventana padre (puede ser un `JFrame` o
`null`). - `"Mensaje"`: texto que se mostrará.

También se puede agregar un título y un tipo de mensaje:

``` java
JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
```

Tipos de mensaje disponibles: - `JOptionPane.INFORMATION_MESSAGE` -
`JOptionPane.WARNING_MESSAGE` - `JOptionPane.ERROR_MESSAGE` -
`JOptionPane.QUESTION_MESSAGE` - `JOptionPane.PLAIN_MESSAGE`

------------------------------------------------------------------------

### 2. `showInputDialog`

Permite **pedir datos** al usuario.

``` java
String nombre = JOptionPane.showInputDialog("¿Cuál es tu nombre?");
```

Con título y mensaje:

``` java
String edad = JOptionPane.showInputDialog(null, "Ingresa tu edad:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
```

------------------------------------------------------------------------

### 3. `showConfirmDialog`

Muestra un cuadro de confirmación con botones (`Sí`, `No`, `Cancelar`).

``` java
int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?");
```

Valores de retorno: - `JOptionPane.YES_OPTION` → 0 -
`JOptionPane.NO_OPTION` → 1 - `JOptionPane.CANCEL_OPTION` → 2

Ejemplo:

``` java
if (respuesta == JOptionPane.YES_OPTION) {
    System.out.println("El usuario aceptó.");
}
```

------------------------------------------------------------------------

### 4. `showOptionDialog`

Permite personalizar los botones.

``` java
String[] opciones = {"Rojo", "Verde", "Azul"};
int seleccion = JOptionPane.showOptionDialog(
        null,
        "Elige un color",
        "Selector de color",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        opciones,
        opciones[0]
);
```

Retorna el índice de la opción seleccionada.

------------------------------------------------------------------------

## Ejemplo completo

``` java
import javax.swing.JOptionPane;

public class EjemploJOptionPane {
    public static void main(String[] args) {
        // Mensaje
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        // Entrada
        String nombre = JOptionPane.showInputDialog("¿Cuál es tu nombre?");

        // Confirmación
        int confirm = JOptionPane.showConfirmDialog(null, "¿Quieres continuar, " + nombre + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Has aceptado continuar.");
        } else {
            JOptionPane.showMessageDialog(null, "Has cancelado la operación.", "Cancelado", JOptionPane.WARNING_MESSAGE);
        }
    }
}
```

------------------------------------------------------------------------

## Resumen

-   `showMessageDialog` → Mostrar un mensaje.
-   `showInputDialog` → Pedir un dato al usuario.
-   `showConfirmDialog` → Confirmación con botones.
-   `showOptionDialog` → Opciones personalizadas.

👉 Con `JOptionPane` puedes reemplazar la entrada/salida por consola
(`System.out.println` y `Scanner`) con ventanas gráficas más intuitivas.
