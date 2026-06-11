-- =============================================
-- Tablas para modulo Trabajo (y dependencias)
-- =============================================

USE eventos_congresos;

-- =============================================
-- Sala
-- =============================================
CREATE TABLE IF NOT EXISTS sala (
    id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100)  NOT NULL,
    capacidad   INT           NOT NULL,
    ubicacion   VARCHAR(200)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Sesion
-- =============================================
CREATE TABLE IF NOT EXISTS sesion (
    id           INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha        DATE          NOT NULL,
    hora_inicio  TIME          NOT NULL,
    hora_fin     TIME          NOT NULL,
    chairman_id  INT           DEFAULT NULL,
    sala_id      INT           NOT NULL,
    CONSTRAINT fk_sesion_chairman FOREIGN KEY (chairman_id)
        REFERENCES congresista(id) ON DELETE SET NULL,
    CONSTRAINT fk_sesion_sala FOREIGN KEY (sala_id)
        REFERENCES sala(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Trabajo
-- =============================================
CREATE TABLE IF NOT EXISTS trabajo (
    id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo      VARCHAR(255)  NOT NULL,
    resumen     TEXT          NOT NULL,
    estado      VARCHAR(50)   NOT NULL DEFAULT 'ENVIADO',
    sesion_id   INT           DEFAULT NULL,
    ponente_id  INT           DEFAULT NULL,
    CONSTRAINT fk_trabajo_sesion FOREIGN KEY (sesion_id)
        REFERENCES sesion(id) ON DELETE SET NULL,
    CONSTRAINT fk_trabajo_ponente FOREIGN KEY (ponente_id)
        REFERENCES congresista(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Trabajo-Autor
-- =============================================
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
-- Datos de prueba
-- =============================================

-- 1. Congresistas (IDs 1-5)
INSERT INTO congresista (id, nombre, apellido, institucion, email, telefono, es_miembro_comite) VALUES
(1, 'Juan', 'Perez', 'UniCartagena', 'juan@correo.com', '3001111111', FALSE),
(2, 'Maria', 'Gomez', 'UniCartagena', 'maria@correo.com', '3002222222', FALSE),
(3, 'Carlos', 'Lopez', 'UniCartagena', 'carlos@correo.com', NULL, TRUE),
(4, 'Ana', 'Martinez', 'UniCartagena', 'ana@correo.com', '3004444444', FALSE),
(5, 'Luis', 'Rodriguez', 'UniCartagena', 'luis@correo.com', NULL, TRUE);

-- 2. Salas (IDs 1-3)
INSERT INTO sala (id, nombre, capacidad, ubicacion) VALUES
(1, 'Sala A', 100, 'Bloque A'),
(2, 'Sala B', 80, 'Bloque B'),
(3, 'Sala C', 120, 'Bloque C');

-- 3. Sesiones (IDs 1-3)
INSERT INTO sesion (id, fecha, hora_inicio, hora_fin, chairman_id, sala_id) VALUES
(1, '2026-06-15', '08:00:00', '10:00:00', 1, 1),
(2, '2026-06-15', '10:30:00', '12:30:00', 2, 2),
(3, '2026-06-16', '08:00:00', '10:00:00', 3, 3);

-- 4. Trabajos (IDs 1-7)
INSERT INTO trabajo (id, titulo, resumen, estado, sesion_id, ponente_id) VALUES
(1, 'Analisis estructural de puentes peatonales en hormigon armado', 'Estudio comparativo de metodos de calculo estructural para puentes peatonales utilizando hormigon armado en zonas urbanas.', 'ACEPTADO', 1, 1),
(2, 'Diseno sismorresistente de edificios de mediana altura', 'Aplicacion de la norma NSR-10 en el diseno de edificios de hasta 10 pisos en zona de amenaza sismica alta.', 'ACEPTADO', 1, 2),
(3, 'Eficiencia energetica en viviendas de interes social', 'Evaluacion de estrategias pasivas de climatizacion para reducir el consumo energetico en viviendas de interes social en clima tropical.', 'REVISADO', 2, 3),
(4, 'Urbanismo tactico y espacio publico', 'Intervenciones urbanas de bajo costo para la recuperacion del espacio publico en centros historicos.', 'ENVIADO', 2, 4),
(5, 'Materiales sostenibles en construccion', 'Analisis del ciclo de vida de bloques de tierra comprimida como alternativa sostenible frente al ladrillo ceramico.', 'ACEPTADO', 3, 5),
(6, 'Sistemas de drenaje urbano sostenible', 'Diseno de jardines de lluvia y pavimentos permeables para mitigar inundaciones en ciudades costeras.', 'ENVIADO', 3, 1),
(7, 'Patrimonio arquitectonico y rehabilitacion', 'Criterios de intervencion para la rehabilitacion de fachadas en edificios patrimoniales del periodo republicano.', 'REVISADO', 1, 2);

-- 5. Autores
INSERT INTO trabajo_autor (congresista_id, trabajo_id, orden_autor) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(1, 6, 1),
(2, 7, 1);
