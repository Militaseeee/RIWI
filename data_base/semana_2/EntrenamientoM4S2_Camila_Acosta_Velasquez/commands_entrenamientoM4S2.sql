-- CREATE DATABASE AND SELECT IT
CREATE DATABASE university_academic_management;

USE university_academic_management;

-- STUDENTS TABLE
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    gender VARCHAR(10) NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    career VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    enrollment_date DATE NOT NULL
);

ALTER TABLE students MODIFY COLUMN birth_date DATE NOT NULL CHECK (birth_date <= '2008-12-31');

-- TEACHERS TABLE
CREATE TABLE teachers (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    institutional_email VARCHAR(100) NOT NULL UNIQUE,
    academic_department VARCHAR(100) NOT NULL,
    years_experience INT NOT NULL CHECK (years_experience >= 0)
);

-- COURSES TABLE
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL UNIQUE,
    credits INT NOT NULL CHECK (credits BETWEEN 1 AND 6),
    semester INT NOT NULL CHECK (semester BETWEEN 1 AND 10),
    teacher_id INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
);

-- ENROLLMENTS TABLE
CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    enrollment_date DATE NOT NULL,
    final_grade DECIMAL(3,2) CHECK (final_grade BETWEEN 0.00 AND 5.00)
);

-- INSERTING DATA
INSERT INTO students (full_name, email, gender, identification, career, birth_date, enrollment_date) VALUES 
    ('Camila Acosta', 'mila@gmail.com', 'Female', '1005840211', 'Software Engineering', '2001-12-24', '2019-07-01'),
    ('Angela Urrego', 'angela@gmail.com', 'Female', '1081736400', 'Medicine', '2002-05-15', '2020-01-20'),
    ('Cristian Penagos', 'cris@gmail.com', 'Male', '1024228547', 'Software Engineering', '2000-09-27', '2019-07-01'),
    ('Daniela Martínez', 'daniela@gmail.com', 'Female', '1037409186', 'Medicine', '2003-01-10', '2021-08-01'),
    ('Samuel Gomez', 'samuel@gmail.com', 'Male', '1047658910', 'Architecture', '2000-11-25', '2018-02-10'),
    ('Alejandra Jaramillo', 'aleja@gmail.com', 'Female', '1220439822', 'Architecture', '2002-05-15', '2020-01-20'),
    ('Valentina Ríos', 'valen@gmail.com', 'Female', '1065328701', 'Psychology', '2001-07-09', '2019-07-15'),
    ('Santiago Pérez', 'santiago@gmail.com', 'Male', '1012483670', 'Civil Engineering', '2000-03-22', '2018-01-25'),
    ('Mariana Salazar', 'marianar@gmail.com', 'Female', '1098734612', 'Psychology', '2002-09-30', '2020-07-01'),
    ('Andres Cano', 'andres@gmail.com', 'Male', '1087964352', 'Medicine', '1999-11-05', '2017-08-01'),
    ('Laura Castillo', 'laura@gmail.com', 'Female', '1070048293', 'Psychology', '2003-06-18', '2021-01-15'),
    ('Juan Caicedo', 'juan@gmail.com', 'Male', '1023467812', 'Software Engineering', '2002-03-12', '2024-07-01'),
    ('Natalia Ramírez', 'natalia@gmail.com', 'Female', '1056189012', 'Law', '2002-08-14', '2023-01-10'),
    ('Anderson Quiroz', 'anderson@gmail.com', 'Male', '1002431005', 'Law', '2001-10-14', '2023-01-10')
;

INSERT INTO teachers (full_name, institutional_email, academic_department, years_experience) VALUES
    ('David Henao', 'david@university.edu.co', 'Engineering', 8),
    ('Alfredo Dominguez', 'alfredo@university.edu.co', 'Health Sciences', 20),
    ('Monica Nieto', 'monica@university.edu.co', 'Arts and Design', 10),
    ('Sebastian Cortes', 'sebastiana@university.edu.co', 'Health Sciences', 6),
    ('Fernanda Caro', 'fernanda@university.edu.co', 'Engineering', 3),
    ('Andres Higuita', 'andres@university.edu.co', 'Legal Sciences', 4)
;

INSERT INTO courses (name, code, credits, semester, teacher_id) VALUES
    ('Object-Oriented Programming', 'IS106', 4, 1, 1),     
    ('Databases', 'IS204', 3, 3, 1),    
    ('Human Anatomy', 'MD101', 4, 1, 2),    
    ('General Psychology', 'PS101', 3, 1, 4), 
    ('Architectural Design', 'AR101', 5, 2, 3), 
    ('Structures I', 'IC101', 3, 2, 5),      
    ('Medical Bioethics', 'MD202', 2, 4, 2),    
    ('3D Architectural Modeling', 'AR202', 5, 5, 3),
    ('Constitutional Law', 'DJ101', 3, 1, 6)
;  

INSERT INTO enrollments (student_id, course_id, enrollment_date, final_grade) VALUES
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


-- BASIC QUERIES AND DATA MANIPULATION
SELECT s.full_name AS student, s.email, s.identification, s.career, c.name AS course, c.code, e.enrollment_date, e.final_grade FROM enrollments e
	JOIN students s ON e.student_id = s.student_id
	JOIN courses c ON e.course_id = c.course_id ORDER BY s.full_name
;


SELECT c.name AS course, c.code, t.full_name AS teacher, t.years_experience AS experience_years FROM courses c
	JOIN teachers t ON c.teacher_id = t.teacher_id WHERE t.years_experience >= 5 ORDER BY c.name
;


SELECT c.name, AVG(e.final_grade) AS average FROM enrollments e 
	JOIN courses c ON e.course_id = c.course_id GROUP BY c.name ORDER BY average
;


SELECT s.student_id, s.identification, s.full_name AS student, COUNT(e.course_id) AS total_courses FROM enrollments e
	JOIN students s ON e.student_id = s.student_id GROUP BY s.student_id, s.full_name HAVING COUNT(e.course_id) > 1
;


ALTER TABLE students ADD academic_status VARCHAR(20);

UPDATE students SET academic_status = 'Active' WHERE student_id = 14;


-- CASCADE DELETE: REMOVE A TEACHER AND EVERYTHING LINKED

-- Doesn't work yet
DELETE FROM teachers WHERE teacher_id = 6;

-- Deletes the current foreign key
ALTER TABLE courses DROP FOREIGN KEY courses_ibfk_1;

-- Add the foreign key again with ON DELETE CASCADE
ALTER TABLE courses ADD CONSTRAINT fk_teacher
	FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
	ON DELETE CASCADE
;

DELETE FROM teachers WHERE teacher_id = 6;

-- CASCADE DELETE FOR ENROLLMENTS

-- Removes the restriction that prevents deleting courses with enrollments
ALTER TABLE enrollments DROP FOREIGN KEY enrollments_ibfk_2;

-- DELETE is added if a course is deleted, its registrations are deleted
ALTER TABLE enrollments ADD CONSTRAINT fk_enrollment_course
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
    ON DELETE CASCADE
;

DELETE FROM teachers WHERE teacher_id = 6;

-- CHECK IF DELETE WORKED
SELECT * FROM teachers WHERE teacher_id = 6;

SELECT * FROM courses WHERE teacher_id = 6;

SELECT * FROM enrollments WHERE course_id IN (SELECT course_id FROM courses WHERE teacher_id = 6);


-- COUNT STUDENTS PER COURSE (ONLY COURSES WITH MORE THAN 2)

SELECT c.course_id, c.name AS course_name, COUNT(e.student_id) AS student_count FROM enrollments e
	JOIN courses c ON e.course_id = c.course_id 
	GROUP BY c.course_id, c.name HAVING COUNT(e.student_id) > 2
;


-- SUBQUERIES & FUNCTIONS

SELECT s.student_id, s.full_name, AVG(e.final_grade) AS average_grade FROM students s 
	JOIN enrollments e ON s.student_id = e.student_id
	GROUP BY s.student_id, s.full_name
	HAVING AVG(e.final_grade) > (SELECT AVG(final_grade) FROM enrollments)
	ORDER BY average_grade DESC
;

-- USING IN
SELECT DISTINCT career FROM students WHERE student_id IN (
    SELECT e.student_id FROM enrollments e
    JOIN courses c ON e.course_id = c.course_id WHERE c.semester >= 2
);

-- USING EXISTS
SELECT DISTINCT s.career 
	FROM students s
	WHERE EXISTS (
    	SELECT 1
    	FROM enrollments e
    	JOIN courses c ON e.course_id = c.course_id
    	WHERE e.student_id = s.student_id
    	AND c.semester >= 2
);


-- SINGLE VALUE AGGREGATES

SELECT AVG(final_grade) AS general_average FROM enrollments;

SELECT SUM(final_grade) AS total_sum FROM enrollments;

SELECT COUNT(final_grade) AS total_records FROM enrollments;

SELECT MAX(final_grade) AS highest_grade FROM enrollments;

SELECT MIN(final_grade) AS lowest_grade FROM enrollments;


-- AVERAGE GRADE PER MAJOR
SELECT s.career, AVG(e.final_grade) AS major_average FROM enrollments e
	JOIN students s ON s.student_id = e.student_id 
	GROUP BY s.career
;

-- TOTAL GRADES PER COURSE
SELECT c.name AS course, SUM(e.final_grade) AS total_grades FROM enrollments e
	JOIN courses c ON c.course_id = e.course_id
	GROUP BY c.course_id
;

-- HIGHEST GRADE PER COURSE
SELECT c.name AS course, MAX(e.final_grade) AS highest_grade FROM enrollments e
	JOIN courses c ON c.course_id = e.course_id
	GROUP BY c.course_id ORDER BY highest_grade DESC
;

-- LOWEST GRADE PER STUDENT
SELECT s.full_name, MIN(e.final_grade) AS lowest_grade FROM enrollments e
	JOIN students s ON s.student_id = e.student_id
	GROUP BY s.student_id ORDER BY lowest_grade ASC
;

-- STUDENT COUNT PER MAJOR
SELECT career, COUNT(*) AS student_count FROM students
	GROUP BY career;


-- CREATE A VIEW
CREATE VIEW academic_history_view AS
	SELECT s.full_name AS student, c.name AS course, t.full_name AS teacher, c.semester, e.final_grade 
	FROM enrollments e 
	JOIN students s ON e.student_id = s.student_id
	JOIN courses c ON e.course_id = c.course_id
	JOIN teachers t ON c.teacher_id = t.teacher_id
;

SELECT * FROM academic_history_view ORDER BY student;


-- ACCESS CONTROL & TRANSACTIONS

CREATE USER 'academic_reviewer'@'%' IDENTIFIED BY 'admin';

GRANT SELECT ON university_academic_management.academic_history_view TO 'academic_reviewer'@'%';

FLUSH PRIVILEGES;

SELECT * FROM academic_history_view;

-- Attempt to insert (should fail due to SELECT-only permissions)
INSERT INTO academic_history_view (student, course, teacher, semester, final_grade) VALUES 
	('test', 'math', 'test', 1, 4)
;

-- REVOKE UPDATE PERMISSIONS (if needed)

REVOKE UPDATE ON university_academic_management.academic_history_view FROM 'academic_reviewer'@'%';


-- TRANSACTIONS

START TRANSACTION;

BEGIN;

-- Update grade for a student

UPDATE enrollments SET final_grade = 4.6 WHERE enrollment_id = 1;

-- Savepoint for rollback

SAVEPOINT backup;

-- Try invalid update (should fail)

UPDATE enrollments SET final_grade = 6.2 WHERE enrollment_id = 2;

-- Rollback to savepoint

ROLLBACK TO SAVEPOINT backup;

-- Valid update

UPDATE enrollments SET final_grade = 4.3 WHERE enrollment_id = 2;

-- Commit all changes

COMMIT;

SELECT e.enrollment_id, s.full_name AS student, e.final_grade 
	FROM enrollments e
	JOIN students s ON e.student_id = s.student_id
;



