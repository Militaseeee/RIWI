-- 1. LIMPIEZA DE DATOS
DELETE FROM repair_order;
DELETE FROM device;
DELETE FROM users;

-- 2. INSERTAR USUARIOS DE PRUEBA (TABLA USERS)

-- Admins (2)
INSERT INTO users (id, username, password, email, full_name, role, enabled) VALUES
(1, 'admin', 'pass', 'admin@mobilefix.com', 'Admin User', 'ADMIN', TRUE),
(2, 'cami_admin', '1234', 'cami@mobilefix.com', 'Camila Admin', 'ADMIN', TRUE);

-- Técnicos (3)
INSERT INTO users (id, username, password, email, full_name, role, enabled) VALUES
(10, 'tech1', 'pass', 'tech1@mobilefix.com', 'Tech User 1', 'TECH', TRUE),
(11, 'tech2', 'pass', 'tech2@mobilefix.com', 'Tech User 2', 'TECH', TRUE),
(12, 'tech3', 'pass', 'tech3@mobilefix.com', 'Tech User 3', 'TECH', TRUE);

-- Clientes (4)
INSERT INTO users (id, username, password, email, full_name, role, enabled) VALUES
(100, 'user1', 'pass', 'client1@mobilefix.com', 'Client User 1', 'USER', TRUE),
(101, 'user2', 'pass', 'client2@mobilefix.com', 'Client User 2', 'USER', TRUE),
(102, 'user3', 'pass', 'client3@mobilefix.com', 'Client User 3', 'USER', TRUE),
(103, 'user4', 'pass', 'client4@mobilefix.com', 'Client User 4', 'USER', TRUE);


-- 3. INSERTAR DISPOSITIVOS DE PRUEBA
INSERT INTO device (id, brand, model, serial_number) VALUES
(101, 'Samsung', 'Galaxy S21', 'SN00101'),
(102, 'Apple', 'iPhone 13', 'SN00102');


-- 4. INSERTAR ÓRDENES DE REPARACIÓN

-- Orden 1001: PENDIENTE (Cliente 100, Dispositivo 101)
INSERT INTO repair_order (id, customer_id, device_id, issue_description, status) VALUES
(1001, 100, 101, 'La pantalla no enciende.', 'PENDING');

-- Orden 1002: EN PROGRESO (Cliente 101, Dispositivo 102, Asignada a Técnico 10)
INSERT INTO repair_order (id, customer_id, device_id, issue_description, status, tech_id, tech_notes) VALUES
(1002, 101, 102, 'Batería se descarga rápido.', 'IN_PROGRESS', 10, 'Revisión inicial completada.');