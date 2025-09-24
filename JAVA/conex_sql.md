# Guía: Eliminar e instalar MySQL desde cero en Debian/Ubuntu

En Linux (Debian/Ubuntu)

## 1. Detener el servicio
```sql
sudo systemctl stop mysql
```

## 2. Desinstalar MySQL y paquetes relacionados
```bash
sudo apt-get remove --purge mysql-server mysql-client mysql-common mysql-server-core-* mysql-client-core-*
```

## 3. Eliminar configuraciones y bases de datos
```sql
sudo rm -rf /etc/mysql /var/lib/mysql
```

## 4. Limpiar paquetes que ya no sirven
```sql
sudo apt-get autoremove
sudo apt-get autoclean
```

## 5. (Opcional) Verifica que no quede nada
```sql
dpkg -l | grep mysql
```

---


# 🔹 Reinstalar MySQL
```sql
sudo apt-get update
sudo apt-get install mysql-server
```

Durante la instalación (en algunas versiones) te pedirá nueva contraseña para root.
En otras (ej. Ubuntu 20.04+) puede instalarse con `auth_socket` y no pedir contraseña.

Si quieres forzar que root use contraseña:

# 🔹 Forzar que root use contraseña

## 1. Entra como superusuario:
```sql
sudo mysql
```

## 2. Ejecuta:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'TuNuevaContraseñaFuerte';
FLUSH PRIVILEGES;
```

## 3. Sal y prueba:
```sql
mysql -u root -p
```