CREATE DATABASE gestion_academica_universidad 

USE gestion_academica_universidad;

CREATE TABLE estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    genero VARCHAR(10) NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    carrera VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_ingreso DATE NOT NULL
);

ALTER TABLE estudiantes MODIFY COLUMN fecha_nacimiento DATE NOT NULL CHECK (fecha_nacimiento <= '2008-12-31');

CREATE TABLE docentes (
    id_docente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    correo_institucional VARCHAR(100) NOT NULL UNIQUE,
    departamento_academico VARCHAR(100) NOT NULL,
    anios_experiencia INT NOT NULL CHECK (anios_experiencia >= 0)
);

CREATE TABLE cursos (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    creditos INT NOT NULL CHECK (creditos BETWEEN 1 AND 6),
    semestre INT NOT NULL CHECK (semestre BETWEEN 1 AND 10),
    id_docente INT NOT NULL,
    FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)
);

CREATE TABLE inscripciones (
    id_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_curso INT NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    fecha_inscripcion DATE NOT NULL,
    calificacion_final DECIMAL(3,2) CHECK (calificacion_final BETWEEN 0.00 AND 5.00)
);


-- INSERCIÓN DE DATOS

INSERT INTO estudiantes (nombre_completo, correo_electronico, genero, identificacion, carrera, fecha_nacimiento, fecha_ingreso) VALUES 
	('Camila Acosta', 'mila@gmail.com', 'Femenino', '1005840211', 'Ingeniería de Software', '2001-12-24', '2019-07-01'),
	('Angela Urrego', 'angela@gmail.com', 'Femenino', '1081736400', 'Medicina', '2002-05-15', '2020-01-20'),
	('Cristian Penagos', 'cris@gmail.com', 'Masculino', '1024228547', 'Ingeniería de Software', '2000-09-27', '2019-07-01'),
	('Daniela Martínez', 'daniela@gmail.com', 'Femenino', '1037409186', 'Medicina', '2003-01-10', '2021-08-01'),
	('Samuel Gomez', 'samuel@gmail.com', 'Masculino', '1047658910', 'Arquitectura', '2000-11-25', '2018-02-10'),
	('Alejandra Jaramillo', 'aleja@gmail.com', 'Femenino', '1220439822', 'Arquitectura', '2002-05-15', '2020-01-20'),
	('Valentina Ríos', 'valen@gmail.com', 'Femenino', '1065328701', 'Psicología', '2001-07-09', '2019-07-15'),
	('Santiago Pérez', 'santiago@gmail.com', 'Masculino', '1012483670', 'Ingeniería Civil', '2000-03-22', '2018-01-25'),
	('Mariana Salazar', 'marianar@gmail.com', 'Femenino', '1098734612', 'Psicología', '2002-09-30', '2020-07-01'),
	('Andres Cano', 'andres@gmail.com', 'Masculino', '1087964352', 'Medicina', '1999-11-05', '2017-08-01'),
	('Laura Castillo', 'laura@gmail.com', 'Femenino', '1070048293', 'Psicología', '2003-06-18', '2021-01-15'),
	('Juan Caicedo', 'juan@gmail.com', 'Masculino', '1023467812', 'Ingeniería de Software', '2002-03-12', '2024-07-01'),
	('Natalia Ramírez', 'natalia@gmail.com', 'Femenino', '1056189012', 'Derecho', '2002-08-14', '2023-01-10'),
    ('Anderson Quiroz', 'anderson@gmail.com', 'Masculino', '1002431005', 'Derecho', '2001-10-14', '2023-01-10')
;

INSERT INTO docentes (nombre_completo, correo_institucional, departamento_academico, anios_experiencia) VALUES
    ('David Henao', 'david@university.edu.co', 'Ingeniería', 8),
    ('Alfredo Dominguez', 'alfredo@university.edu.co', 'Ciencias de la Salud', 20),
    ('Monica Nieto', 'monica@university.edu.co', 'Artes y Diseño', 10),
    ('Sebastian Cortes', 'sebastiana@university.edu.co', 'Ciencias de la Salud', 6),
    ('Fernanda Caro', 'fernanda@university.edu.co', 'Ingeniería', 3),
	('Andres Higuita', 'andres@university.edu.co', 'Ciencias Jurídicas', 4)
;

INSERT INTO cursos (nombre, codigo, creditos, semestre, id_docente) VALUES
    ('Programación POO', 'IS106', 4, 1, 1),     
    ('Bases de Datos', 'IS204', 3, 3, 1),    
    ('Anatomía Humana', 'MD101', 4, 1, 2),    
    ('Psicología General', 'PS101', 3, 1, 4), 
    ('Diseño Arquitectónico', 'AR101', 5, 2, 3), 
    ('Estructuras I', 'IC101', 3, 2, 5),      
    ('Bioética Médica', 'MD202', 2, 4, 2),    
    ('Modelado 3D Arquitectónico', 'AR202', 5, 5, 3),
    ('Derecho Constitucional', 'DJ101', 3, 1, 6)
;  

INSERT INTO inscripciones (id_estudiante, id_curso, fecha_inscripcion, calificacion_final) VALUES
    (1, 1, '2025-02-15', 4.2),  
    (3, 2, '2025-03-01', 4.7),  
    (2, 3, '2024-01-16', 4.8),  
    (4, 7, '2024-01-16', 2.0), 
    (6, 5, '2024-01-17', 4.3), 
    (5, 8, '2024-01-17', 4.7), 
    (7, 4, '2024-01-18', 5.0),  
    (8, 6, '2024-01-18', 3.4),  
    (9, 4, '2024-01-18', 4.5),  
    (10, 3, '2024-01-19', 3.1), 
    (11, 4, '2024-01-19', 4.6), 
    (12, 2, '2024-01-19', 2.9), 
    (1, 1, '2025-03-01', 4.9), 
    (3, 2, '2025-02-15', 4.4), 
    (10, 7, '2024-01-19', 3.1),
    (13, 9, '2025-07-15', 4.6), 
    (14, 9, '2025-07-15', 4.0)
;


-- CONSULTAS BÁSICAS Y MANIPULACIÓN

SELECT e.nombre_completo AS estudiante, e.correo_electronico, e.identificacion, e.carrera, c.nombre AS curso, c.codigo, i.fecha_inscripcion, i.calificacion_final FROM inscripciones i
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante
	JOIN cursos c ON i.id_curso = c.id_curso ORDER BY e.nombre_completo
;


SELECT c.nombre AS curso, c.codigo, d.nombre_completo AS docente, d.anios_experiencia AS tiempo_experiencia FROM cursos c
	JOIN docentes d ON c.id_docente = d.id_docente WHERE d.anios_experiencia >= 5 ORDER BY c.nombre
;


SELECT c.nombre, AVG(i.calificacion_final) AS promedio
	FROM inscripciones i JOIN cursos c ON i.id_curso = c.id_curso
	GROUP BY c.nombre ORDER BY promedio
;


SELECT e.id_estudiante, e.identificacion, e.nombre_completo AS estudiante, COUNT(i.id_curso) AS cantidad_cursos FROM inscripciones i
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante
	GROUP BY e.id_estudiante, e.nombre_completo HAVING COUNT(i.id_curso) > 1
;


ALTER TABLE estudiantes ADD estado_academico VARCHAR(20);

UPDATE estudiantes SET estado_academico = 'Activo' WHERE id_estudiante = 14;


-- No funciona aun
DELETE FROM docentes WHERE id_docente = 6;

-- Elimina la clave foránea actual
ALTER TABLE cursos DROP FOREIGN KEY cursos_ibfk_1;

-- Vuelve a agregar la clave foránea con ON DELETE CASCADE
ALTER TABLE cursos ADD CONSTRAINT fk_docente
	FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)
	ON DELETE CASCADE
;

DELETE FROM docentes WHERE id_docente = 6;


-- Elimina la restricción que impide borrar cursos con inscripciones
ALTER TABLE inscripciones DROP FOREIGN KEY inscripciones_ibfk_2;

-- Se agrega el DELETE si se borra un curso, se borran sus inscripciones
ALTER TABLE inscripciones ADD CONSTRAINT fk_inscripcion_curso
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
    ON DELETE CASCADE
;

DELETE FROM docentes WHERE id_docente = 6;

-- Comparar si funciono:
SELECT * FROM docentes WHERE id_docente = 6;

SELECT * FROM cursos WHERE id_docente = 6;          

SELECT * FROM inscripciones WHERE id_curso IN (SELECT id_curso FROM cursos WHERE id_docente = 6);


SELECT c.id_curso, c.nombre AS nombre_curso, COUNT(i.id_estudiante) AS cantidad_estudiantes FROM inscripciones i
	JOIN cursos c ON i.id_curso = c.id_curso 
	GROUP BY c.id_curso, c.nombre HAVING COUNT(i.id_estudiante) > 2
;


-- SUBCONSULTAS Y FUNCIONES

SELECT e.id_estudiante, e.nombre_completo, AVG(i.calificacion_final) AS promedio_estudiante FROM estudiantes e 
	JOIN inscripciones i ON e.id_estudiante = i.id_estudiante
	GROUP BY e.id_estudiante, e.nombre_completo
	HAVING AVG(i.calificacion_final) > (SELECT AVG(calificacion_final) FROM inscripciones)
	ORDER BY promedio_estudiante DESC
;

-- IN
SELECT DISTINCT carrera FROM estudiantes WHERE id_estudiante IN (
    SELECT i.id_estudiante FROM inscripciones i
    JOIN cursos c ON i.id_curso = c.id_curso WHERE c.semestre >= 2
);

-- EXISTS
SELECT DISTINCT e.carrera FROM estudiantes e
	WHERE EXISTS (
    	SELECT 1
    	FROM inscripciones i
    	JOIN cursos c ON i.id_curso = c.id_curso
    	WHERE i.id_estudiante = e.id_estudiante
      	AND c.semestre >= 2
);


-- Consultas que trae solo un dato 
SELECT AVG(calificacion_final) AS promedio_general FROM inscripciones;

SELECT SUM(calificacion_final) AS suma_total FROM inscripciones;

SELECT COUNT(calificacion_final) AS total_registros FROM inscripciones;

SELECT MAX(calificacion_final) AS calificacion_maxima FROM inscripciones;

SELECT MIN(calificacion_final) AS calificacion_minima FROM inscripciones;

--  Promedio de calificaciones por carrera
SELECT e.carrera, AVG(i.calificacion_final) AS promedio_carrera FROM inscripciones i
	JOIN estudiantes e ON e.id_estudiante = i.id_estudiante
	GROUP BY e.carrera
;

-- Sumar calificaciones por curso
SELECT c.nombre AS curso, SUM(i.calificacion_final) AS suma_notas_curso FROM inscripciones i
	JOIN cursos c ON c.id_curso = i.id_curso
	GROUP BY c.id_curso
;

-- Máxima calificación por curso
SELECT c.nombre AS curso, MAX(i.calificacion_final) AS nota_mas_alta FROM inscripciones i
	JOIN cursos c ON c.id_curso = i.id_curso
	GROUP BY c.id_curso ORDER BY nota_mas_alta DESC 
;

-- Nota más baja por estudiante
SELECT e.nombre_completo, MIN(i.calificacion_final) AS nota_mas_baja FROM inscripciones i
	JOIN estudiantes e ON e.id_estudiante = i.id_estudiante
	GROUP BY e.id_estudiante ORDER BY nota_mas_baja ASC;

-- Cuántos estudiantes tiene cada carrera
SELECT carrera, COUNT(*) AS cantidad_estudiantes FROM estudiantes
	GROUP BY carrera;


-- CREAR UNA VISTA 

CREATE VIEW vista_historial_academico AS
	SELECT e.nombre_completo AS estudiante, c.nombre AS curso, d.nombre_completo AS docente, c.semestre, i.calificacion_final FROM inscripciones i 
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante
	JOIN cursos c ON i.id_curso = c.id_curso
	JOIN docentes d ON c.id_docente = d.id_docente
;
	
SELECT * FROM vista_historial_academico ORDER BY estudiante;


-- CONTROL DE ACCESO Y TRANSACCIONES

CREATE USER 'revisor_academico'@'%' IDENTIFIED BY 'admin';

GRANT SELECT ON gestion_academica_universidad.vista_historial_academico TO 'revisor_academico'@'%';

-- Aplica los cambios
FLUSH PRIVILEGES;

SELECT * FROM vista_historial_academico;

-- Compruebo si el usuario puede agregar
INSERT INTO vista_historial_academico (estudiante, curso, docente, semestre, calificacion_final) VALUES 
	('prueba', 'matematicas', 'prueba', 1, 4)
;


REVOKE UPDATE ON gestion_academica_universidad.vista_historial_academico FROM 'revisor_academico'@'%';


START TRANSACTION;

BEGIN;

-- Actualizamos la calificación del estudiante con id_inscripcion = 1
UPDATE inscripciones SET calificacion_final = 4.6 WHERE id_inscripcion = 1;

-- Guardamos un punto al que podemos volver si hay problemas
SAVEPOINT backup;

-- Intentamos actualizar otra calificación (ejemplo, con un valor inválido)
UPDATE inscripciones SET calificacion_final = 6.2 WHERE id_inscripcion = 2;

-- Deshacemos esa actualización:
ROLLBACK TO SAVEPOINT backup;

UPDATE inscripciones SET calificacion_final = 4.3 WHERE id_inscripcion = 2;

-- Confirmamos todos los cambios
COMMIT;

SELECT i.id_inscripcion, e.nombre_completo AS estudiante, i.calificacion_final FROM inscripciones i
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante 
;





