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
## ❌ REVOKE: Retirar permisos previamente otorgados
Para quitar un permiso previamente dado, por ejemplo INSERT, se usa el comando REVOKE. Esto es útil si quieres limitar las acciones que puede realizar un usuario.

```sql
REVOKE INSERT ON empleadosdb.empleados FROM 'cris'@'localhost';
```
Esto hará que el usuario cris ya no pueda insertar nuevos registros en la tabla empleados, pero podrá seguir realizando las acciones para las que aún tenga permisos (como SELECT o UPDATE).

📝 **Nota:** Los permisos solo pueden ser revocados por el **usuario que los otorgó** o por un **administrador** (como `root`). Si intentas hacer `REVOKE` desde un usuario que no tiene autoridad sobre esos permisos, recibirás un error de acceso denegado.
