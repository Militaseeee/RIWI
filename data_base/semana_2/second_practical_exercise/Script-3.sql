CREATE DATABASE practice2_s2

USE practice2_s2

CREATE TABLE users (
	id_user INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	year INT,
	grade FLOAT,
	city VARCHAR(50),
	isActive BOOLEAN 
);

ALTER TABLE users DROP COLUMN ciudad;

ALTER TABLE users MODIFY COLUMN name VARCHAR(100);

ALTER TABLE users MODIFY COLUMN grade FLOAT CHECK(grade >= 0 AND grade <=5);

LOAD DATA INFILE '/var/lib/mysql-files/usuarios_taller.csv'
INTO TABLE users
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(name, year, grade, city, @activo)
SET isActive = IF(LOWER(@activo) = 'true', 1, 0);

DESCRIBE users;

SELECT * FROM users;

SELECT name, year FROM users;

SELECT name, year FROM users WHERE year BETWEEN 18 AND 25;

SELECT * FROM users WHERE isActive = 1;

SELECT * FROM users ORDER BY year DESC;

SELECT AVG(grade) AS average_grade FROM users;

SELECT COUNT(*) AS total_users FROM users;

SELECT name, year FROM users ORDER BY year DESC LIMIT 1

SELECT name, year FROM users WHERE year = (SELECT MAX(year) FROM users);

SELECT name, year FROM users ORDER BY year ASC LIMIT 1

SELECT name, year FROM users WHERE year = (SELECT MIN(year) FROM users) ORDER BY name ASC;

SELECT * FROM users WHERE city = 'Medellín';

SELECT COUNT(*) AS active_users FROM users WHERE isActive = 1

SELECT name, year, grade FROM users WHERE grade >= 4.0 AND isActive = 1


