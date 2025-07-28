# 🛡️ Guardar - Creación de Usuario y Asignación de Permisos en MySQL

Este documento contiene los comandos SQL necesarios para crear un usuario en MySQL, asignarle permisos sobre una tabla específica y configurar la autenticación para evitar errores de conexión en herramientas como DBeaver.

---

## 🔧 Crear el usuario `cris` con contraseña

```sql
CREATE USER 'cris'@'localhost' IDENTIFIED BY 'c123';
```
## ✅ Asignar permisos sobre la tabla empleados de la base de datos empleadosdb

```sql
GRANT SELECT, INSERT, UPDATE ON empleadosdb.empleados TO 'cris'@'localhost';
```
## 🔄 Ajustar el método de autenticación para evitar errores como "Public Key Retrieval is not allowed"

```sql
ALTER USER 'cris'@'localhost' IDENTIFIED WITH mysql_native_password BY 'c123';
FLUSH PRIVILEGES;
```
## 📂 Usar la base de datos y verificar acceso

```sql

USE empleadosdb;

SELECT * FROM empleados;
```
