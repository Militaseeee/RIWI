## Paso 1: Crear la base de datos

```sql
CREATE DATABASE tienda;
USE tienda;

```

 Creamos una base de datos llamada `tienda` y la activamos para trabajar dentro de ella.

---

##  Paso 2: Crear la tabla `clientes`

```sql
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100)
);

```

 La tabla `clientes` tiene un campo `id` que será la **clave primaria**. Esto identifica a cada cliente de forma única.

---

## 📦 Paso 3: Crear la tabla `pedidos`

```sql
CREATE TABLE pedidos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fecha DATE,
  cliente_id INT,
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

```

📌 La tabla `pedidos` también tiene su propia clave primaria (`id`).

El campo `cliente_id` es una **clave foránea** que se conecta con `clientes.id`. Así sabemos qué cliente hizo cada pedido.

---

##  Paso 4: Insertar datos de ejemplo

```sql
-- Insertar clientes
INSERT INTO clientes (nombre, email) VALUES
('Ana Pérez', 'ana@gmail.com'),
('Luis Torres', 'luis@gmail.com');

-- Insertar pedidos
INSERT INTO pedidos (fecha, cliente_id) VALUES
('2025-07-01', 1),
('2025-07-05', 2),
('2025-07-10', 1);

```

 Agregamos dos clientes y tres pedidos. Observa que los pedidos hacen referencia a los `id` de los clientes.

---

##  Paso 5: Consulta con JOIN (versión simple)

```sql
SELECT *
FROM pedidos
JOIN clientes ON pedidos.cliente_id = clientes.id;

```

 Con este `JOIN` unimos las dos tablas.

Mostramos todos los campos de ambas tablas (`SELECT *`) y conectamos cada pedido con su cliente gracias al campo `cliente_id`.