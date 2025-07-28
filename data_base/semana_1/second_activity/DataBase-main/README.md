### **¿Qué es un dato?**
Un dato es una pieza de información simbólica (número, letra, etc.). En informática, representa una característica de una entidad.
* **Tipos comunes:** Numéricos (`10`), Texto (`"Hola"`), Booleanos (`true`/`false`), Fechas.
* **Ejemplo web:** El **nombre de usuario** o el **número de 'me gusta'** en una publicación.

---

### **¿Qué es una base de datos?**
Es una **colección organizada de datos** para almacenar, gestionar y acceder a la información de forma eficiente.
* **Uso:** Almacenar datos de aplicaciones de forma segura, consistente y rápida.
* **Diferencia con archivos:** Las bases de datos controlan el acceso concurrente, evitan redundancia y garantizan la integridad, a diferencia de archivos simples (como .txt o Excel).
* **Ejemplo real:** Un **banco** gestionando las cuentas y transacciones de sus clientes.

---

### **¿Qué es un motor de base de datos?**
Es el **software (el "cerebro")** que gestiona la base de datos.
* **Función:** Permite crear, leer, actualizar y borrar datos (CRUD), además de gestionar la seguridad y optimizar consultas.
* **Diferencia con la BD:** La base de datos son los datos; el motor es el programa que los administra.
* **Motores conocidos:** MySQL, PostgreSQL, Microsoft SQL Server, Oracle.

---

### **¿Qué es una base de datos relacional (SQL)?**
Organiza los datos en **tablas estructuradas** (filas y columnas) que se pueden conectar entre sí.
* **"Relacional":** Significa que las tablas se vinculan mediante identificadores únicos para evitar duplicar información.
* **Ventajas de SQL:** Es un lenguaje estándar que ofrece **consistencia** (transacciones seguras ACID) y una estructura de datos predecible.
* **Motores populares:** MySQL, PostgreSQL, SQL Server.

---

### **¿Qué es una base de datos NoSQL?**
Bases de datos que **no usan el modelo de tablas relacionales**, ofreciendo más flexibilidad.
* **Significado:** "Not Only SQL" (No solo SQL). Se diferencia por usar modelos como documentos (MongoDB), clave-valor (Redis) o grafos (Neo4j).
* **Tipos:** Documentales, Clave-Valor, Columnares y de Grafos.
* **Motores de ejemplo:** MongoDB, Redis, Cassandra, Neo4j.

---

### **¿Cuándo usar SQL vs. NoSQL?**

* **Usa SQL si:**
    * Necesitas **integridad y consistencia** de datos (ej: un sistema bancario o un e-commerce).
    * Tu estructura de datos es **estable y bien definida**.
* **Usa NoSQL si:**
    * Necesitas **alta flexibilidad** para datos no estructurados (ej: redes sociales, IoT).
    * Requieres **escalabilidad horizontal** masiva y alta velocidad de escritura/lectura.

| Enfoque | Ventajas | Desventajas |
| :--- | :--- | :--- |
| **SQL** | ✅ Consistencia, estructura clara. | ❌ Rigidez, escalabilidad vertical (costosa). |
| **NoSQL** | ✅ Flexibilidad, escalabilidad horizontal. | ❌ Menor consistencia, sin un estándar de consulta. |

---

### **Similitudes y diferencias clave**

* **Manejo de relaciones:**
    * **SQL:** Usa **relaciones explícitas** entre tablas (`JOINs`).
    * **NoSQL:** Tiende a **incrustar datos** relacionados en un mismo documento o usa referencias simples manejadas por la aplicación.
* **Escalabilidad:**
    * **SQL:** **Escala verticalmente** (mejorando el hardware de un único servidor).
    * **NoSQL:** **Escala horizontalmente** (distribuyendo la carga entre múltiples servidores más económicos).

