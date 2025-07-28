
#  Clase: Normalización en Bases de Datos Relacionales

##  Objetivo
Comprender las tres primeras formas normales de normalización (1FN, 2FN, 3FN) en bases de datos relacionales, sus reglas, beneficios y cómo aplicarlas con ejemplos prácticos.

---

##  ¿Qué es la Normalización?

La **normalización** es un proceso de diseño de bases de datos que organiza la información para reducir la redundancia y mejorar su integridad. Fue introducida por Edgar F. Codd, el creador del modelo relacional.

###  Beneficios de la normalización:
- Evita la duplicación de datos.
- Mejora la integridad de la base de datos.
- Facilita el mantenimiento.
- Aumenta la eficiencia en consultas y actualizaciones.

---

## ✅ Primera Forma Normal (1FN)

### 📌 Reglas:
- Todos los atributos deben contener **valores atómicos** (indivisibles).
- No se permiten columnas que contengan listas, conjuntos o múltiples valores.
- Todas las filas deben ser únicas.

### ❌ Ejemplo antes de aplicar 1FN:

| ID | Nombre | Teléfonos                 |
|----|--------|---------------------------|
| 1  | Ana    | 3001234567, 3109876543    |
| 2  | Luis   | 3112223333                |

`Teléfonos` contiene más de un valor → viola la 1FN.

### ✅ Ejemplo después de aplicar 1FN:

| ID | Nombre | Teléfono     |
|----|--------|--------------|
| 1  | Ana    | 3001234567   |
| 1  | Ana    | 3109876543   |
| 2  | Luis   | 3112223333   |

---

## ✅ Segunda Forma Normal (2FN)

### 📌 Requisitos:
- Cumplir con **1FN**.
- Todos los atributos que no son clave deben depender **completamente** de la clave primaria.
- Se deben eliminar las **dependencias parciales**.

> Una dependencia parcial ocurre cuando un campo depende de solo una parte de una clave primaria compuesta, en lugar de depender de toda la clave.

### ❌ Ejemplo antes de aplicar 2FN:

| ID_Alumno | ID_Curso | Nombre_Alumno | Nombre_Curso |
|-----------|----------|----------------|---------------|
| 1         | A1       | Ana            | Matemáticas   |
| 1         | A2       | Ana            | Física        |

- `ID_Alumno + ID_Curso` es la clave compuesta.
- `Nombre_Alumno` depende solo de `ID_Alumno`.
- `Nombre_Curso` depende solo de `ID_Curso`.

Ambos casos son **dependencias parciales**.

### ✅ Después de aplicar 2FN:

**Alumnos**

| ID_Alumno | Nombre_Alumno |
|-----------|----------------|
| 1         | Ana            |

**Cursos**

| ID_Curso | Nombre_Curso  |
|----------|----------------|
| A1       | Matemáticas    |
| A2       | Física         |

**Matriculas**

| ID_Alumno | ID_Curso |
|-----------|----------|
| 1         | A1       |
| 1         | A2       |

---

## ✅ Tercera Forma Normal (3FN)

### 📌 Requisitos:
- Cumplir con **2FN**.
- Eliminar las **dependencias transitivas**: un campo no clave no debe depender de otro campo no clave.

> Una dependencia transitiva ocurre cuando un campo no clave depende de otro campo no clave, que a su vez depende de la clave primaria.

### ❌ Ejemplo antes de aplicar 3FN:

| ID_Empleado | Nombre | ID_Departamento | Nombre_Departamento |
|-------------|--------|------------------|----------------------|
| 1           | Juan   | 10               | Finanzas             |
| 2           | Laura  | 20               | Recursos Humanos     |

`Nombre_Departamento` no depende directamente de la clave primaria `ID_Empleado`, sino de `ID_Departamento`, un campo no clave.

### ✅ Después de aplicar 3FN:

**Empleados**

| ID_Empleado | Nombre | ID_Departamento |
|-------------|--------|------------------|
| 1           | Juan   | 10               |
| 2           | Laura  | 20               |

**Departamentos**

| ID_Departamento | Nombre_Departamento |
|------------------|---------------------|
| 10               | Finanzas            |
| 20               | Recursos Humanos    |

---

## 🧠 Resumen de Formas Normales

| Forma Normal | Requisito Principal                           | Qué evita                        |
|--------------|-----------------------------------------------|----------------------------------|
| **1FN**      | Datos atómicos.                               | Múltiples valores por campo      |
| **2FN**      | Eliminar dependencias parciales.              | Dependencias de parte de la clave|
| **3FN**      | Eliminar dependencias transitivas.            | Dependencias entre no claves     |

---

##  Ejercicio Propuesto

Realiza el ejercicio propuesto en discord.

---

##  Conclusión

La normalización es una práctica esencial para diseñar bases de datos coherentes, escalables y libres de redundancias. Aunque en ciertos escenarios se permite la desnormalización por razones de rendimiento, siempre se recomienda iniciar con un modelo normalizado para asegurar una base sólida.

