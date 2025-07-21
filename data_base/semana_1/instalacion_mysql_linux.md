
# 🐧 Instalación de MySQL en Linux (Ubuntu/Debian)

Este documento explica cómo instalar y configurar MySQL desde cero en una máquina Linux basada en Ubuntu o Debian.

---

## ✅ Paso 1: Actualizar el sistema

```bash
sudo apt update
sudo apt upgrade
```

---

## ✅ Paso 2: Instalar MySQL Server

```bash
sudo apt install mysql-server
```

Esto instalará:
- El motor de base de datos MySQL
- El cliente de línea de comandos `mysql`

---

## ✅ Paso 3: Verificar que el servicio esté activo

```bash
sudo systemctl status mysql
```

Si no está activo, puedes iniciarlo con:

```bash
sudo systemctl start mysql
```

---

## ✅ Paso 4: Asegurar la instalación

Ejecuta el asistente de seguridad de MySQL:

```bash
sudo mysql_secure_installation
```

Este comando te permitirá:
- Establecer una contraseña para el usuario root
- Eliminar usuarios anónimos
- Deshabilitar el login remoto para root
- Borrar la base de datos de prueba
- Recargar los privilegios

**Recomendado:** Responde "Y" (yes) a todas las opciones.

---

## ✅ Paso 5: Acceder al cliente de MySQL

```bash
sudo mysql -u root -p
```

Si no configuraste contraseña, puedes omitir `-p`:

```bash
sudo mysql -u root
```

---

## ✅ Paso 6: Primeros comandos en MySQL

```sql
-- Ver bases de datos
SHOW DATABASES;

-- Crear una nueva base de datos
CREATE DATABASE mi_base;

-- Usar la base de datos
USE mi_base;

-- Crear una tabla de ejemplo
CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100)
);

-- Insertar un dato
INSERT INTO usuarios (nombre, email) VALUES ('Ana', 'ana@email.com');

-- Consultar datos
SELECT * FROM usuarios;
```

---

## ✅ Paso 7: Salir del cliente MySQL

```sql
EXIT;
```

---

## 🛠 ¿Qué sigue?

- Instalar un visualizador como **DBeaver** o **MySQL Workbench**
- Conectar MySQL con aplicaciones externas (Node.js, Python, etc.)
- Habilitar acceso remoto si es necesario (no recomendado en producción sin seguridad adicional)
