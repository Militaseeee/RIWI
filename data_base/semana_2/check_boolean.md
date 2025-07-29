# Importar usuarios desde archivo CSV en MySQL

Este procedimiento importa un archivo CSV llamado `usuarios_taller.csv` a la tabla `users` en una base de datos MySQL, realizando una conversión del campo booleano `isActive`.

## 📁 Paso 1: Copiar el archivo CSV a la carpeta permitida por MySQL

MySQL con la opción `--secure-file-priv` solo permite importar archivos desde rutas específicas. Para mover el archivo:

```bash
sudo cp /home/coders/Descargas/second_practical_exercise/usuarios_taller.csv /var/lib/mysql-files/
```

## 🗃️ Paso 2: Importar el archivo en la tabla users
Asegúrate de que la tabla users esté creada previamente con esta estructura:

sql
```
CREATE TABLE users (
  id_user INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  year INT,
  grade FLOAT,
  city VARCHAR(50),
  isActive BOOLEAN
);
```

Luego ejecuta el comando de importación:

sql
```
LOAD DATA INFILE '/var/lib/mysql-files/usuarios_taller.csv'
INTO TABLE users
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(name, year, grade, city, @activo)
SET isActive = IF(LOWER(@activo) = 'true', 1, 0);
```
