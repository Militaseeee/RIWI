USE gestion_academica_universidad;

-- Obtener los estudiantes con promedio superior al general
SELECT e.id_estudiante, e.nombre_completo, AVG(i.calificacion_final) AS promedio_estudiante FROM estudiantes e 
	JOIN inscripciones i ON e.id_estudiante = i.id_estudiante
	GROUP BY e.id_estudiante, e.nombre_completo
	HAVING AVG(i.calificacion_final) > (SELECT AVG(calificacion_final) FROM inscripciones
);


-- Calcular promedios y sumatorias utilizando funciones de agregación como AVG(), SUM(), COUNT(), MAX() y MIN()
SELECT AVG(calificacion_final) AS promedio_general FROM inscripciones;

SELECT SUM(calificacion_final) AS suma_total FROM inscripciones;

SELECT COUNT(calificacion_final) AS total_registros FROM inscripciones;

SELECT MAX(calificacion_final) AS calificacion_maxima FROM inscripciones;

SELECT MIN(calificacion_final) AS calificacion_minima FROM inscripciones;


-- Usar cláusulas como WHERE, ORDER BY, BETWEEN, IN, LIKE e IS NULL para refinar las búsquedas
SELECT * FROM estudiantes WHERE carrera = 'Ingeniería de Software';

SELECT nombre_completo AS docente, departamento_academico AS facultad, anios_experiencia AS experiencia FROM docentes ORDER BY anios_experiencia DESC;

SELECT * FROM estudiantes WHERE fecha_ingreso BETWEEN '2017-01-01' AND '2020-01-01';

SELECT * FROM estudiantes WHERE carrera IN ('Ingeniería de Software', 'Arquitectura', 'Ingeniería Civil');

SELECT * FROM cursos WHERE codigo LIKE 'MD%';

SELECT * FROM estudiantes WHERE correo_electronico IS NULL;


-- Agrupar datos por carrera o curso utilizando GROUP BY y filtrar los resultados con HAVING
SELECT carrera, COUNT(*) AS total_estudiantes FROM estudiantes GROUP BY carrera HAVING COUNT(*) > 1;


-- Crear subconsultas para filtrar información con base en otras consultas
SELECT d.nombre_completo AS docente, c.codigo AS codigo_curso FROM docentes d
	JOIN cursos c ON d.id_docente = c.id_docente WHERE c.creditos > (SELECT AVG(creditos) FROM cursos
); 


-- Utilizar JOINs para combinar la información de estudiantes, docentes, cursos e inscripciones
SELECT e.identificacion AS cedula, e.nombre_completo AS estudiante, e.carrera, c.nombre AS curso, c.codigo AS codigo_curso, d.nombre_completo AS docente, i.fecha_inscripcion, i.calificacion_final FROM inscripciones i
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante
	JOIN cursos c ON i.id_curso = c.id_curso
	JOIN docentes d ON c.id_docente = d.id_docente
;


-- Crear una VISTA que muestre el historial académico de los estudiantes con nombre del curso, docente, calificación y semestre
CREATE VIEW historial_academico AS
	SELECT e.identificacion AS cedula, e.nombre_completo AS estudiante, e.carrera, c.nombre AS curso, c.codigo AS codigo_curso, d.nombre_completo AS docente, i.fecha_inscripcion, c.semestre, i.calificacion_final FROM inscripciones i
	JOIN estudiantes e ON i.id_estudiante = e.id_estudiante
	JOIN cursos c ON i.id_curso = c.id_curso
	JOIN docentes d ON c.id_docente = d.id_docente
;

SELECT * FROM historial_academico;


-- Manejar permisos y roles mediante comandos GRANT y REVOKE
CREATE USER 'coordinador'@'localhost' IDENTIFIED BY 'c123';

GRANT SELECT ON gestion_academica_universidad.estudiantes TO 'coordinador'@'localhost';
GRANT SELECT ON gestion_academica_universidad.docentes TO 'coordinador'@'localhost';
GRANT SELECT ON gestion_academica_universidad.cursos TO 'coordinador'@'localhost';

GRANT UPDATE (correo_electronico) ON gestion_academica_universidad.estudiantes TO 'coordinador'@'localhost';
GRANT UPDATE (correo_institucional) ON gestion_academica_universidad.docentes TO 'coordinador'@'localhost';
GRANT UPDATE (nombre, codigo, creditos) ON gestion_academica_universidad.cursos TO 'coordinador'@'localhost';

SHOW GRANTS FOR 'coordinador'@'localhost';

REVOKE UPDATE (creditos) ON gestion_academica_universidad.cursos FROM 'coordinador'@'localhost';


-- Asegurar la integridad de las transacciones usando COMMIT, ROLLBACK y SAVEPOINT
START TRANSACTION;

-- Actualiza un dato del estudiante
UPDATE estudiantes SET correo_electronico = 'pablito@gmail.com' WHERE identificacion = '1003250422';

-- Crea un punto de guardado
SAVEPOINT despues_estudiante;

-- Actualiza el correo de un docente
UPDATE docentes SET correo_institucional = 'alfredo_d@university.edu.co' WHERE nombre_completo = 'Alfredo Dominguez';

-- Crea otro punto de guardado
SAVEPOINT despues_docente;

-- Intenta actualizar un curso con un código duplicado (esto causará error si 'MD101' ya existe)
UPDATE cursos SET codigo = 'MD101' WHERE nombre = 'Anatomía Humana I';

-- Si hubo error, revierte solo desde el último SAVEPOINT
ROLLBACK TO despues_docente;

-- Finaliza la transacción guardando lo anterior
COMMIT;
