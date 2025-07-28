
# Clase: Introducción a las Bases de Datos

## ¿Qué es un dato?
Un **dato** es una unidad mínima de información. Puede representar hechos, cifras, símbolos, palabras o medidas que, por sí solos, no tienen mucho significado, pero al ser organizados y procesados permiten generar conocimiento. Por ejemplo: “25”, “Colombia”, “Activo”.

## ¿Qué es una base de datos?
Una **base de datos (BD)** es una colección estructurada de datos que permite su fácil acceso, manipulación y actualización. Se utiliza para almacenar información que luego puede ser consultada, modificada o eliminada de manera eficiente. Las bases de datos se emplean en sistemas informáticos para manejar grandes volúmenes de información en áreas como comercio, salud, educación, redes sociales, etc.

## ¿Cuál es la importancia de las BD?
Las bases de datos son fundamentales en la industria del software porque:

- **Eficiencia en el Manejo de Datos**: Automatizan procesos de almacenamiento, recuperación y gestión de información, permitiendo rapidez y precisión.
- **Seguridad y Consistencia**: Aplican reglas de acceso, validaciones y restricciones para proteger los datos y evitar inconsistencias.
- **Independencia de los Datos**: Los datos pueden gestionarse de forma separada al software que los utiliza, facilitando su reutilización y mantenimiento.
- **Menor Espacio de Almacenamiento**: Emplean técnicas como la normalización, compresión o almacenamiento en la nube para optimizar recursos.
- **Escalabilidad**: Las bases de datos pueden crecer en tamaño y capacidad según la demanda del sistema.
- **Toma de Decisiones**: La información almacenada puede ser analizada para generar reportes, estadísticas y estrategias en las organizaciones.

## ¿Qué es un Motor de Bases de Datos?
Un **motor de base de datos** es el componente que se encarga de procesar las solicitudes realizadas a una base de datos. Ejecuta instrucciones como insertar, actualizar, borrar o consultar datos. Trabaja directamente con el sistema operativo y el hardware para gestionar los archivos físicos donde se almacenan los datos.

## ¿Qué es un Sistema de Gestión de BD (SGBD)?
Un **Sistema de Gestión de Base de Datos (SGBD)** es un software que proporciona una interfaz entre los usuarios y las bases de datos. Permite definir estructuras, manipular datos, controlar el acceso, garantizar la integridad y gestionar la concurrencia. Ejemplos de SGBD: MySQL, Oracle, PostgreSQL, MongoDB, SQL Server.

---

## Importancia de las Bases de Datos en la Industria del Software

Las bases de datos facilitan la gestión, protección y análisis de la información, lo cual es esencial para el funcionamiento de aplicaciones modernas:

- **Eficiencia en el Manejo de Datos**: Permiten operaciones complejas con rapidez, como búsquedas, filtros, agrupaciones y cálculos.
- **Seguridad y Consistencia**: Se implementan permisos, backups automáticos, y reglas de integridad que reducen riesgos de pérdida o alteración indebida de la información.
- **Independencia de los Datos**: Se puede modificar la base sin afectar directamente al sistema que la usa.
- **Menor Espacio de Almacenamiento**: Se optimizan recursos al guardar solo lo necesario, evitar duplicados y almacenar datos de forma compacta.
- **Escalabilidad**: Las bases pueden aumentar su capacidad sin perder rendimiento.
- **Toma de Decisiones**: Se generan reportes, dashboards e insights que ayudan a las organizaciones a planificar estrategias y mejorar resultados.

---

## Bases de Datos Relacionales (SQL)

### Introducción
Las bases de datos relacionales almacenan información en tablas organizadas por filas (registros) y columnas (campos). Las relaciones entre estas tablas se establecen mediante claves primarias y claves foráneas. Se utiliza el lenguaje **SQL (Structured Query Language)** para consultar y manipular los datos.

### Ventajas de las BD SQL

- Estructura clara que facilita la organización de los datos.
- Alta integridad de la información gracias al uso de claves y restricciones.
- Amplio soporte por parte de la industria.
- Posibilidad de realizar consultas complejas con facilidad.

### Secciones de una Base de Datos Relacional

- **Tablas**: Son la estructura principal. Cada tabla representa una entidad (por ejemplo, usuarios, productos, ventas) y cada fila un registro. Las columnas definen los atributos del registro.

- **Vistas**: Son consultas almacenadas que actúan como tablas virtuales. Permiten mostrar ciertos datos sin revelar la tabla completa, facilitando la seguridad y la abstracción.

- **Índices**: Estructuras que aceleran la búsqueda de datos en las tablas. Funcionan como índices en un libro: permiten llegar más rápido al dato que se necesita, pero ocupan más espacio.

- **Consultas (queries)**: Instrucciones en SQL que permiten recuperar, filtrar, ordenar y combinar datos. Son la herramienta principal de interacción con los datos.

- **Procedimientos almacenados**: Bloques de código SQL que se almacenan en el servidor y pueden ejecutarse repetidamente. Son útiles para tareas repetitivas y para centralizar lógica de negocio en la base de datos.

### Motores de Bases Relacionales

Algunos motores populares:

- **MySQL**: Muy usado en desarrollo web. Gratuito y de código abierto.
- **PostgreSQL**: Muy robusto y con soporte a funciones avanzadas.
- **Oracle Database**: Amplio soporte empresarial, altamente seguro.
- **SQL Server**: Producto de Microsoft, con excelente integración en entornos Windows.

### ¿Debo dominar todos?

No es necesario, pero se recomienda aprender al menos uno como MySQL o PostgreSQL. Esto te dará una base sólida para entender otros motores, ya que todos utilizan SQL como lenguaje principal.

---

## Bases de Datos No Relacionales (NoSQL)

### Introducción
Las bases de datos **NoSQL** se diseñaron para superar las limitaciones de las BD relacionales en términos de escalabilidad, flexibilidad y manejo de grandes volúmenes de datos. No siguen el modelo de tablas y, en su lugar, adoptan estructuras como documentos, grafos, pares clave-valor o columnas.

### Ventajas de las BD NoSQL

- **Flexibilidad**: No requieren un esquema fijo. Los documentos pueden tener estructuras distintas.
- **Escalabilidad Horizontal**: Es fácil distribuir los datos entre varios servidores o nodos.
- **Alta disponibilidad**: Muchos motores NoSQL están diseñados para seguir funcionando aunque un nodo falle.
- **Desempeño**: Excelente rendimiento para consultas específicas y cargas masivas de datos.

### Más sobre BD NoSQL

Tipos comunes:

- **Documentales (ej. MongoDB)**: Almacenan datos en documentos tipo JSON, ideales para aplicaciones web.
- **Clave-Valor (ej. Redis)**: Parejas simples de clave y valor, muy rápidas para accesos directos.
- **Columnar (ej. Cassandra)**: Diseñadas para lecturas rápidas en grandes volúmenes de datos.
- **Grafos (ej. Neo4j)**: Usadas para representar relaciones complejas, como redes sociales o rutas.

### ¿Qué es la Escalabilidad Horizontal?

La **escalabilidad horizontal** es la capacidad de un sistema para incrementar su rendimiento agregando más máquinas (nodos) a la red. En lugar de hacer que una sola máquina sea más potente (escalabilidad vertical), se divide la carga entre varios equipos. Las BD NoSQL están optimizadas para este tipo de escalado, lo que las hace ideales en sistemas distribuidos y aplicaciones de gran tamaño como redes sociales, sistemas de análisis en tiempo real, etc.

### Conclusión NoSQL

Las bases de datos NoSQL **no sustituyen** a las relacionales, sino que **las complementan**. La elección entre una u otra depende del contexto del proyecto:

- Si se requiere estructura fija, integridad referencial y consultas complejas → **SQL**.
- Si se busca flexibilidad de esquema, alta disponibilidad y escalabilidad horizontal → **NoSQL**.

Hoy en día, muchas arquitecturas modernas combinan ambos tipos en un enfoque llamado **poliglot persistence**, donde se utilizan distintos tipos de base según la necesidad específica de cada parte del sistema.
