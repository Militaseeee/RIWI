### Objetivo

Construir una jerarquía de clases para representar a una **Persona** dentro de una institución educativa, aplicando los principios de **herencia, encapsulamiento y polimorfismo**.

---

### Requisitos de Clases

#### Clase `Persona` (base)

* Atributos:

  * `nombre`
  * `edad`
* Métodos:

  * Consultar y modificar atributos.
  * `mostrarInformacion()` → método polimórfico.

---

#### Clase `Alumno` (hereda de Persona)

* Restricciones:

  * Puede tener **máximo cuatro asignaturas** asociadas.
* Métodos:

  * `agregarAsignatura()` → valida que la asignatura no exista previamente.
  * `calcularPromedio()` → devuelve el promedio de las notas.
  * Sobrescribir `mostrarInformacion()` para mostrar:

    * Nombre y edad.
    * Asignaturas con: nombre, nota y estado (aprobado/reprobado).
    * Promedio final.

---

#### Clase `Asignatura`

* Atributos:

  * `nombre`
  * `nota`
* Métodos:

  * `estado()` → devuelve **Aprobada** si la nota ≥ 3.5, o **Reprobada** si < 3.5.

---

### Clase Principal `Aplicacion`

* Funcionalidades:

  * Crear al menos **tres instancias de Alumno**.
  * Asignar a cada alumno sus asignaturas y notas.
  * Mostrar en pantalla la información de cada alumno usando `mostrarInformacion()`.
