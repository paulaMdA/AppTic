DROP DATABASE IF EXISTS AppTIC;
CREATE DATABASE AppTIC;
USE AppTIC;

CREATE TABLE Usuarios (
    idUsuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    rol ENUM('alumno', 'profesor', 'mantenimiento', 'administrador') NOT NULL
);

CREATE TABLE Equipos (
    idEquipo VARCHAR(50) PRIMARY KEY,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    descripcion TEXT,
    aula ENUM('A1', 'A2', 'A3', 'B1', 'B2', 'B3', 'C1', 'C2', 'C3') NOT NULL
);

CREATE TABLE Incidencias (
    idIncidencia INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT,
    idEquipo VARCHAR(50),
    estado ENUM('pendiente', 'reparado') NOT NULL,
    prioridad ENUM('baja', 'media', 'alta') NOT NULL,
    descripcion TEXT NOT NULL,
    fechaInicio DATETIME NOT NULL,
    fechaFin DATETIME,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario),
    FOREIGN KEY (idEquipo) REFERENCES Equipos(idEquipo)
);

-- Inserciones de prueba

INSERT INTO Usuarios (nombre, apellido, password, email, rol) VALUES
('Juan', 'Perez', 'kk', 'juan@juan.com', 'alumno'),
('Maria', 'Gomez', 'kk', 'maria@maria.com', 'profesor'),
('Luis', 'Lopez', 'kk', 'luis@luis.com', 'mantenimiento'),
('Ana', 'Martinez', 'kk', 'ana@ana.com', 'administrador');

INSERT INTO Equipos (idEquipo, marca, modelo, descripcion, aula) VALUES
('B2001', 'Dell', 'OptiPlex 3070', 'PC de escritorio', 'B2'),
('B2002', 'HP', 'EliteBook 850', 'Laptop', 'B2'),
('B3001', 'Lenovo', 'ThinkPad X1', 'Laptop', 'B3');

INSERT INTO Incidencias (idUsuario, idEquipo, estado, prioridad, descripcion, fechaInicio) VALUES
(1, 'B2001', 'pendiente', 'alta', 'El equipo no enciende', NOW()),
(2, 'B2002', 'pendiente', 'media', 'Problemas con el teclado', NOW()),
(3, 'B3001', 'reparado', 'baja', 'Actualización de software', NOW());
