-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
);

-- =============================================
-- Base de datos para Congresista
-- =============================================
CREATE DATABASE IF NOT EXISTS eventos_congresos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE eventos_congresos;

CREATE TABLE IF NOT EXISTS congresista (
    id                INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre            VARCHAR(100)  NOT NULL,
    apellido          VARCHAR(100)  NOT NULL,
    institucion       VARCHAR(200)  NOT NULL,
    email             VARCHAR(150)  NOT NULL UNIQUE,
    telefono          VARCHAR(20)   DEFAULT NULL,
    es_miembro_comite BOOLEAN       NOT NULL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Tablas para modulo Trabajo
-- =============================================

CREATE TABLE IF NOT EXISTS trabajo (
    id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo      VARCHAR(255)  NOT NULL,
    resumen     TEXT          NOT NULL,
    estado      VARCHAR(50)   NOT NULL DEFAULT 'ENVIADO',
    sesion_id   INT           DEFAULT NULL,
    ponente_id  INT           DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS trabajo_autor (
    congresista_id INT NOT NULL,
    trabajo_id     INT NOT NULL,
    orden_autor    INT NOT NULL DEFAULT 1,
    PRIMARY KEY (congresista_id, trabajo_id),
    CONSTRAINT fk_ta_congresista FOREIGN KEY (congresista_id)
        REFERENCES congresista(id) ON DELETE CASCADE,
    CONSTRAINT fk_ta_trabajo FOREIGN KEY (trabajo_id)
        REFERENCES trabajo(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Datos de prueba para modulo Trabajo
-- =============================================

INSERT INTO trabajo (titulo, resumen, estado, sesion_id, ponente_id) VALUES
('Arquitectura Hexagonal en Java', 'Implementacion del patron de arquitectura hexagonal en aplicaciones empresariales con Java y Spring Boot.', 'ACEPTADO', 1, 1),
('Domain-Driven Design Practico', 'Aplicacion de DDD en proyectos reales, desde la exploracion del dominio hasta la implementacion de agregados.', 'ACEPTADO', 1, 2),
('Microservicios con eventos', 'Arquitectura orientada a eventos para sistemas distribuidos usando RabbitMQ y Kafka.', 'REVISADO', 2, 3),
('Patrones de diseno en sistemas modernos', 'Evolucion de los patrones GoF hacia implementaciones funcionales con Java moderno.', 'ENVIADO', 2, 4),
('Clean Code y deuda tecnica', 'Estrategias para identificar y reducir deuda tecnica en proyectos legacy.', 'ACEPTADO', 3, 5),
('Pruebas automatizadas en Java', 'De unit tests a integration tests: piramide de testing practica con JUnit 5 y Mockito.', 'ENVIADO', 3, 1),
('CQRS y Event Sourcing', 'Separacion de responsabilidades de lectura y escritura con almacenamiento de eventos.', 'REVISADO', 1, 2);

INSERT INTO trabajo_autor (congresista_id, trabajo_id, orden_autor) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(1, 6, 1),
(2, 7, 1);

