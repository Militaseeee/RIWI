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
	('Juan Caicedo', 'juan@gmail.com', 'Masculino', '1023467812', 'Ingeniería de Software', '2002-03-12', '2024-07-01')
;

INSERT INTO docentes (nombre_completo, correo_institucional, departamento_academico, anios_experiencia) VALUES
    ('David Henao', 'david@university.edu.co', 'Ingeniería', 8),
    ('Alfredo Dominguez', 'alfredo@university.edu.co', 'Ciencias de la Salud', 20),
    ('Monica Nieto', 'monica@university.edu.co', 'Artes y Diseño', 10),
    ('Sebastian Cortes', 'sebastiana@university.edu.co', 'Ciencias de la Salud', 6),
    ('Fernanda Caro', 'fernanda@university.edu.co', 'Ingeniería', 3)
;

INSERT INTO cursos (nombre, codigo, creditos, semestre, id_docente) VALUES
    ('Programación POO', 'IS106', 4, 1, 1),     
    ('Bases de Datos', 'IS204', 3, 3, 1),    
    ('Anatomía Humana', 'MD101', 4, 1, 2),    
    ('Psicología General', 'PS101', 3, 1, 4), 
    ('Diseño Arquitectónico', 'AR101', 5, 2, 3), 
    ('Estructuras I', 'IC101', 3, 2, 5),      
    ('Bioética Médica', 'MD202', 2, 4, 2),    
    ('Modelado 3D Arquitectónico', 'AR202', 5, 5, 3)
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
    (10, 7, '2024-01-19', 3.1)  
;

