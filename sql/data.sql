-- =========================================================
-- EXTENSIONES
-- =========================================================

CREATE EXTENSION IF NOT EXISTS pgcrypto;


-- =========================================================
-- USUARIOS
-- =========================================================

CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    nombre_usuario VARCHAR(50) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    contrasena_hash VARCHAR(255) NOT NULL,

    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_usuarios_nombre_usuario
        UNIQUE (nombre_usuario),

    CONSTRAINT uq_usuarios_correo
        UNIQUE (correo),

    CONSTRAINT ck_usuarios_nombre_usuario
        CHECK (char_length(nombre_usuario) >= 3),

    CONSTRAINT ck_usuarios_correo
        CHECK (btrim(correo) <> '')
);


-- =========================================================
-- LIBROS
-- =========================================================

CREATE TABLE libros (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    url_imagen TEXT,

    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT ck_libros_titulo
        CHECK (btrim(titulo) <> ''),

    CONSTRAINT ck_libros_autor
        CHECK (btrim(autor) <> ''),

    CONSTRAINT ck_libros_descripcion
        CHECK (btrim(descripcion) <> '')
);


-- =========================================================
-- CALIFICACIONES
-- =========================================================

CREATE TABLE calificaciones (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    usuario_id UUID NOT NULL,
    libro_id UUID NOT NULL,

    puntuacion SMALLINT NOT NULL,

    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_calificaciones_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_calificaciones_libro
        FOREIGN KEY (libro_id)
        REFERENCES libros(id)
        ON DELETE CASCADE,

    CONSTRAINT ck_calificaciones_puntuacion
        CHECK (puntuacion BETWEEN 1 AND 5),

    CONSTRAINT uq_calificaciones_usuario_libro
        UNIQUE (usuario_id, libro_id)
);


-- =========================================================
-- COMENTARIOS
-- =========================================================

CREATE TABLE comentarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    usuario_id UUID NOT NULL,
    libro_id UUID NOT NULL,

    contenido TEXT NOT NULL,

    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_comentarios_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_comentarios_libro
        FOREIGN KEY (libro_id)
        REFERENCES libros(id)
        ON DELETE CASCADE,

    CONSTRAINT ck_comentarios_contenido
        CHECK (btrim(contenido) <> '')
);


-- =========================================================
-- ÍNDICES
-- =========================================================

-- Libros por título
CREATE INDEX idx_libros_titulo
    ON libros (titulo);

-- Libros por autor
CREATE INDEX idx_libros_autor
    ON libros (autor);

-- Libros más recientes
CREATE INDEX idx_libros_fecha_creacion
    ON libros (fecha_creacion DESC);


-- Calificaciones de un libro
CREATE INDEX idx_calificaciones_libro_id
    ON calificaciones (libro_id);

-- Calificaciones realizadas por un usuario
CREATE INDEX idx_calificaciones_usuario_id
    ON calificaciones (usuario_id);


-- Comentarios de un libro
CREATE INDEX idx_comentarios_libro_id
    ON comentarios (libro_id);

-- Comentarios realizados por un usuario
CREATE INDEX idx_comentarios_usuario_id
    ON comentarios (usuario_id);

-- Comentarios de un libro ordenados por fecha
CREATE INDEX idx_comentarios_libro_fecha
    ON comentarios (libro_id, fecha_creacion DESC);