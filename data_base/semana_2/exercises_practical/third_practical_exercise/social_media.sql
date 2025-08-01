CREATE DATABASE social_media

USE social_media;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    role VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts (
    id_post INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    body TEXT,
    user_id INT NOT NULL,
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id_user) ON DELETE CASCADE
);

CREATE TABLE follows (
    following_user_id INTEGER,
    followed_user_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (following_user_id, followed_user_id),
    FOREIGN KEY (following_user_id) REFERENCES users(id_user) ON DELETE CASCADE,
    FOREIGN KEY (followed_user_id) REFERENCES users(id_user) ON DELETE CASCADE
);

-- 1. Listar todos los usuarios registrados
SELECT id_user AS id, username, role, created_at FROM users;

-- 2. Ver todos los posts publicados por el usuario con username = 'sofia_g'
SELECT p.title, p.status, p.created_at FROM posts p
	JOIN users u ON p.user_id = u.id_user WHERE u.username = 'sofia_g'
;

-- 3. Mostrar todos los usuarios con rol 'admin'
SELECT username, created_at FROM users WHERE role = 'admin';

-- 4. Ver todos los posts con estado 'publicado'
SELECT p.id_post AS id, p.title, u.username FROM posts p
	JOIN users u ON p.user_id = u.id_user WHERE p.status = 'publicado'
;

-- 5. Mostrar los nombres de los usuarios que están siendo seguidos
SELECT u.username AS followed_username FROM follows f
	JOIN users u ON f.followed_user_id = u.id_user
;

-- 6. Consultar el número total de usuarios
SELECT COUNT(*) AS total_usuarios FROM users;

-- 7. Mostrar los títulos de los posts creados hoy
SELECT title FROM posts WHERE DATE(created_at) = CURDATE();
-- funciona solo si created_at tiene exactamente la fecha de hoy. En tu caso, los registros tienen fecha actual (correcta), 
-- pero puede que el formato o la zona horaria esté desincronizada.

INSERT INTO posts (title, body, user_id, status, created_at) VALUES 
	('Post del día', 'Contenido especial de hoy', 1, 'publicado', NOW())
;

SELECT title FROM posts WHERE DATE(created_at) = CURDATE();

-- 8. Obtener el nombre del autor y título de cada post
SELECT u.username, p.title FROM posts p
	JOIN users u ON p.user_id = u.id_user
;

-- 9. Listar los usuarios junto con la cantidad de seguidores que tienen
SELECT u.username, COUNT(f.following_user_id) AS seguidores FROM users u
	LEFT JOIN follows f ON u.id_user = f.followed_user_id
	GROUP BY u.id_user, u.username
;

-- 10. Mostrar los usuarios que no han publicado ningún post
SELECT u.username FROM users u
	LEFT JOIN posts p ON u.id_user = p.user_id
	WHERE p.id_post IS NULL
;

-- 11. Mostrar los posts junto con el número de palabras en el contenido (body)
SELECT title, LENGTH(body) - LENGTH(REPLACE(body, ' ', '')) + 1 AS palabras FROM posts WHERE body IS NOT NULL;

-- 12. Consultar cuántos usuarios hay por tipo de rol
SELECT role, COUNT(*) AS cantidad FROM users GROUP BY role;

-- 13. Obtener la fecha y el autor del post más reciente
SELECT u.username, p.title, p.created_at FROM posts p
	JOIN users u ON p.user_id = u.id_user
	ORDER BY p.created_at DESC
	-- LIMIT 1
;

-- 14. Mostrar los usuarios que siguen a más de 3 personas
SELECT u.username, COUNT(f.followed_user_id) AS siguiendo_a FROM users u
	JOIN follows f ON u.id_user = f.following_user_id
	GROUP BY u.id_user, u.username HAVING COUNT(f.followed_user_id) > 3
;

-- 15. Consultar los 5 posts más antiguos con estado 'borrador'
SELECT title, created_at FROM posts
	WHERE status = 'borrador' ORDER BY created_at ASC LIMIT 5
;

-- 16. Usuarios con más de 2 posts publicados
SELECT u.username, COUNT(p.id_post) AS publicados FROM users u
	JOIN posts p ON u.id_user = p.user_id WHERE p.status = 'publicado'
	GROUP BY u.username HAVING COUNT(p.id_post) > 2
;

-- 17. Usuarios que no siguen a nadie pero sí tienen seguidores
SELECT u.username FROM users u WHERE u.id_user 
	NOT IN ( SELECT f.following_user_id FROM follows f )
	AND u.id_user IN ( SELECT f.followed_user_id FROM follows f
);

-- 18. Usuarios que tienen posts pero nunca han seguido a nadie
SELECT DISTINCT u.username FROM users u
	JOIN posts p ON u.id_user = p.user_id WHERE u.id_user NOT IN (
    SELECT following_user_id FROM follows
);

-- 19. Usuarios que se siguen mutuamente
SELECT u1.username AS usuario_1, u2.username AS usuario_2 FROM follows f1
	JOIN follows f2 ON f1.following_user_id = f2.followed_user_id AND f1.followed_user_id = f2.following_user_id
	JOIN users u1 ON u1.id_user = f1.following_user_id
	JOIN users u2 ON u2.id_user = f1.followed_user_id
	WHERE u1.id_user < u2.id_user
;

-- 20. Eliminar todos los posts en estado 'borrador' de usuarios con rol 'guest'
DELETE FROM posts WHERE status = 'borrador'
	AND user_id IN (
    SELECT id_user FROM users WHERE role = 'guest'
);

