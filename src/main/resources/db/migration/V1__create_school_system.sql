-- ============================================================
-- SISTEMA ESCOLAR COMPLETO
-- PostgreSQL - Flyway Migration V1
-- ============================================================

-- ============================================================
-- 1. TIPOS ENUM
-- ============================================================

CREATE TYPE estado_anio AS ENUM (
    'PLANIFICADO', 'ACTIVO', 'FINALIZADO', 'CERRADO'
);

CREATE TYPE estado_periodo AS ENUM (
    'PLANIFICADO', 'ACTIVO', 'FINALIZADO', 'CERRADO'
);

CREATE TYPE genero AS ENUM (
    'MASCULINO', 'FEMENINO', 'OTRO'
);

CREATE TYPE estado_estudiante AS ENUM (
    'ACTIVO', 'INACTIVO', 'RETIRADO', 'GRADUADO', 'SUSPENDIDO'
);

CREATE TYPE tipo_matricula AS ENUM (
    'NUEVO', 'CONTINUIDAD', 'TRASLADO', 'REINGRESO'
);

CREATE TYPE estado_matricula AS ENUM (
    'PENDIENTE', 'ACTIVA', 'FINALIZADA', 'CANCELADA', 'RETIRADA'
);

CREATE TYPE estado_profesor AS ENUM (
    'ACTIVO', 'INACTIVO', 'SUSPENDIDO', 'RETIRADO'
);

CREATE TYPE turno AS ENUM (
    'MATUTINO', 'VESPERTINO', 'NOCTURNO', 'MIXTO'
);

CREATE TYPE tipo_materia AS ENUM (
    'OBLIGATORIA', 'OPTATIVA', 'TALLER', 'EXTRACURRICULAR'
);

CREATE TYPE estado_materia AS ENUM (
    'ACTIVA', 'INACTIVA'
);

CREATE TYPE tipo_aula AS ENUM (
    'AULA', 'LABORATORIO', 'AUDITORIO', 'TALLER', 'BIBLIOTECA', 'COMPUTO', 'OTRO'
);

CREATE TYPE estado_aula AS ENUM (
    'DISPONIBLE', 'MANTENIMIENTO', 'INACTIVA'
);

CREATE TYPE dia_semana AS ENUM (
    'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO'
);

CREATE TYPE modalidad_clase AS ENUM (
    'PRESENCIAL', 'VIRTUAL', 'HIBRIDA'
);

CREATE TYPE estado_asignacion AS ENUM (
    'ACTIVA', 'INACTIVA'
);

CREATE TYPE estado_evaluacion AS ENUM (
    'PROGRAMADA', 'REALIZADA', 'CANCELADA'
);

CREATE TYPE estado_nota AS ENUM (
    'APROBADO', 'REPROBADO', 'PENDIENTE'
);

CREATE TYPE estado_asistencia AS ENUM (
    'PRESENTE', 'AUSENTE', 'TARDE', 'JUSTIFICADA'
);

CREATE TYPE estado_persona AS ENUM (
    'ACTIVO', 'INACTIVO'
);

CREATE TYPE nivel_incidente AS ENUM (
    'LEVE', 'MODERADO', 'GRAVE'
);

CREATE TYPE estado_incidente AS ENUM (
    'ABIERTO', 'RESUELTO', 'CERRADO'
);

CREATE TYPE tipo_observacion AS ENUM (
    'ACADEMICA', 'CONDUCTA', 'PARTICIPACION', 'RENDIMIENTO', 'GENERAL'
);

CREATE TYPE estado_actividad AS ENUM (
    'BORRADOR', 'PUBLICADA', 'CERRADA', 'CANCELADA'
);

CREATE TYPE estado_entrega AS ENUM (
    'PENDIENTE', 'ENTREGADA', 'TARDE', 'CALIFICADA'
);

CREATE TYPE prioridad_aviso AS ENUM (
    'BAJA', 'NORMAL', 'ALTA', 'URGENTE'
);

CREATE TYPE estado_aviso AS ENUM (
    'BORRADOR', 'PUBLICADO', 'EXPIRADO'
);

CREATE TYPE tipo_evento AS ENUM (
    'ACADEMICO', 'DEPORTIVO', 'CULTURAL', 'REUNION', 'FERIADO', 'OTRO'
);

CREATE TYPE estado_evento AS ENUM (
    'PROGRAMADO', 'REALIZADO', 'CANCELADO'
);

CREATE TYPE estado_concepto AS ENUM (
    'ACTIVO', 'INACTIVO'
);

CREATE TYPE estado_cobro AS ENUM (
    'PENDIENTE', 'PAGADO', 'VENCIDO', 'CANCELADO'
);

CREATE TYPE metodo_pago AS ENUM (
    'EFECTIVO', 'TRANSFERENCIA', 'TARJETA', 'CHEQUE', 'OTRO'
);

CREATE TYPE tipo_documento AS ENUM (
    'DUI', 'NIE', 'PARTIDA_NACIMIENTO', 'CERTIFICADO', 'CONSTANCIA', 'FOTOGRAFIA', 'OTRO'
);

CREATE TYPE estado_documento AS ENUM (
    'PENDIENTE', 'VALIDADO', 'RECHAZADO'
);

CREATE TYPE tipo_notificacion AS ENUM (
    'INFO', 'AVISO', 'ALERTA', 'ACADEMICA', 'PAGO', 'ASISTENCIA'
);

CREATE TYPE accion_auditoria AS ENUM (
    'INSERT', 'UPDATE', 'DELETE', 'LOGIN', 'LOGOUT'
);

-- ============================================================
-- 2. CONFIGURACIÓN
-- ============================================================

CREATE TABLE configuracion (
    id_configuracion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_institucion VARCHAR(150) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(30),
    correo VARCHAR(150),
    sitio_web VARCHAR(255),
    logo_url VARCHAR(500),
    escala_minima NUMERIC(4,2) DEFAULT 0.00,
    escala_maxima NUMERIC(4,2) DEFAULT 10.00,
    nota_aprobacion NUMERIC(4,2) DEFAULT 6.00,
    moneda VARCHAR(10) DEFAULT 'USD',
    zona_horaria VARCHAR(100) DEFAULT 'America/El_Salvador',
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_config_escala CHECK (escala_minima < escala_maxima),
    CONSTRAINT chk_config_aprobacion CHECK (nota_aprobacion >= escala_minima AND nota_aprobacion <= escala_maxima)
);

-- ============================================================
-- 3. AÑO ESCOLAR
-- ============================================================

CREATE TABLE anio_escolar (
    id_anio_escolar BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    anio INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado estado_anio DEFAULT 'PLANIFICADO',
    CONSTRAINT uq_anio_escolar UNIQUE (anio),
    CONSTRAINT chk_anio_valido CHECK (anio >= 2000 AND anio <= 2100),
    CONSTRAINT chk_anio_fechas CHECK (fecha_inicio < fecha_fin)
);

-- ============================================================
-- 4. PERIODOS
-- ============================================================

CREATE TABLE periodo_academico (
    id_periodo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_anio_escolar BIGINT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    numero_periodo INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado estado_periodo DEFAULT 'PLANIFICADO',
    CONSTRAINT fk_periodo_anio FOREIGN KEY (id_anio_escolar) REFERENCES anio_escolar(id_anio_escolar),
    CONSTRAINT uq_periodo_anio_numero UNIQUE (id_anio_escolar, numero_periodo),
    CONSTRAINT chk_periodo_numero CHECK (numero_periodo > 0),
    CONSTRAINT chk_periodo_fechas CHECK (fecha_inicio < fecha_fin)
);

-- ============================================================
-- 5. NIVEL EDUCATIVO
-- ============================================================

CREATE TABLE nivel_educativo (
    id_nivel BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    CONSTRAINT uq_nivel_nombre UNIQUE (nombre)
);

-- ============================================================
-- 6. GRADOS
-- ============================================================

CREATE TABLE grado (
    id_grado BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_nivel BIGINT NOT NULL,
    nombre_grado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    CONSTRAINT fk_grado_nivel FOREIGN KEY (id_nivel) REFERENCES nivel_educativo(id_nivel),
    CONSTRAINT uq_grado_nombre UNIQUE (nombre_grado)
);

-- ============================================================
-- 7. SECCIONES
-- ============================================================

CREATE TABLE seccion (
    id_seccion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(100),
    CONSTRAINT uq_seccion_nombre UNIQUE (nombre)
);

-- ============================================================
-- 8. GRUPOS
-- ============================================================

CREATE TABLE grupo (
    id_grupo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_grado BIGINT NOT NULL,
    id_seccion BIGINT NOT NULL,
    id_anio_escolar BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    capacidad INTEGER DEFAULT 40,
    turno turno DEFAULT 'MATUTINO',
    estado estado_asignacion DEFAULT 'ACTIVA',
    CONSTRAINT fk_grupo_grado FOREIGN KEY (id_grado) REFERENCES grado(id_grado),
    CONSTRAINT fk_grupo_seccion FOREIGN KEY (id_seccion) REFERENCES seccion(id_seccion),
    CONSTRAINT fk_grupo_anio FOREIGN KEY (id_anio_escolar) REFERENCES anio_escolar(id_anio_escolar),
    CONSTRAINT uq_grupo UNIQUE (id_grado, id_seccion, id_anio_escolar),
    CONSTRAINT chk_grupo_capacidad CHECK (capacidad > 0)
);

-- ============================================================
-- 9. ESTUDIANTES
-- ============================================================

CREATE TABLE estudiante (
    id_estudiante BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_estudiante VARCHAR(30) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    genero genero,
    nacionalidad VARCHAR(80),
    dui VARCHAR(20),
    nie VARCHAR(30),
    correo_electronico VARCHAR(150),
    telefono VARCHAR(30),
    direccion VARCHAR(255),
    fecha_ingreso DATE,
    estado estado_estudiante DEFAULT 'ACTIVO',
    foto_url VARCHAR(500),
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_estudiante_codigo UNIQUE (codigo_estudiante),
    CONSTRAINT uq_estudiante_dui UNIQUE (dui),
    CONSTRAINT uq_estudiante_nie UNIQUE (nie)
);

-- ============================================================
-- 10. MATRÍCULAS
-- ============================================================

CREATE TABLE matricula (
    id_matricula BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    id_grupo BIGINT NOT NULL,
    fecha_matricula DATE NOT NULL,
    tipo_matricula tipo_matricula DEFAULT 'NUEVO',
    estado estado_matricula DEFAULT 'PENDIENTE',
    observaciones TEXT,
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_matricula_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_matricula_grupo FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo),
    CONSTRAINT uq_matricula UNIQUE (id_estudiante, id_grupo)
);

-- ============================================================
-- 11. PROFESORES
-- ============================================================

CREATE TABLE profesor (
    id_profesor BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_profesor VARCHAR(30) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dui VARCHAR(20),
    especialidad VARCHAR(150),
    correo_electronico VARCHAR(150),
    telefono VARCHAR(30),
    direccion VARCHAR(255),
    fecha_contratacion DATE,
    estado estado_profesor DEFAULT 'ACTIVO',
    foto_url VARCHAR(500),
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_profesor_codigo UNIQUE (codigo_profesor),
    CONSTRAINT uq_profesor_dui UNIQUE (dui)
);

-- ============================================================
-- 12. DEPARTAMENTOS
-- ============================================================

CREATE TABLE departamento (
    id_departamento BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    CONSTRAINT uq_departamento_nombre UNIQUE (nombre)
);

-- ============================================================
-- 13. PROFESOR - DEPARTAMENTO
-- ============================================================

CREATE TABLE profesor_departamento (
    id_profesor BIGINT NOT NULL,
    id_departamento BIGINT NOT NULL,
    PRIMARY KEY (id_profesor, id_departamento),
    CONSTRAINT fk_pd_profesor FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor),
    CONSTRAINT fk_pd_departamento FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento)
);

-- ============================================================
-- 14. MATERIAS
-- ============================================================

CREATE TABLE materia (
    id_materia BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_materia VARCHAR(30) NOT NULL,
    nombre_materia VARCHAR(100) NOT NULL,
    descripcion TEXT,
    horas_semanales INTEGER DEFAULT 1,
    tipo tipo_materia DEFAULT 'OBLIGATORIA',
    estado estado_materia DEFAULT 'ACTIVA',
    CONSTRAINT uq_materia_codigo UNIQUE (codigo_materia),
    CONSTRAINT uq_materia_nombre UNIQUE (nombre_materia),
    CONSTRAINT chk_horas_semanales CHECK (horas_semanales > 0)
);

-- ============================================================
-- 15. MATERIAS POR GRADO
-- ============================================================

CREATE TABLE grado_materia (
    id_grado BIGINT NOT NULL,
    id_materia BIGINT NOT NULL,
    horas_semanales INTEGER DEFAULT 1,
    obligatoria BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id_grado, id_materia),
    CONSTRAINT fk_gm_grado FOREIGN KEY (id_grado) REFERENCES grado(id_grado),
    CONSTRAINT fk_gm_materia FOREIGN KEY (id_materia) REFERENCES materia(id_materia),
    CONSTRAINT chk_gm_horas CHECK (horas_semanales > 0)
);

-- ============================================================
-- 16. AULAS
-- ============================================================

CREATE TABLE aula (
    id_aula BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    edificio VARCHAR(100),
    piso VARCHAR(50),
    capacidad INTEGER NOT NULL,
    tipo tipo_aula DEFAULT 'AULA',
    estado estado_aula DEFAULT 'DISPONIBLE',
    CONSTRAINT uq_aula_codigo UNIQUE (codigo),
    CONSTRAINT chk_aula_capacidad CHECK (capacidad > 0)
);

-- ============================================================
-- 17. BLOQUES HORARIOS
-- ============================================================

CREATE TABLE bloque_horario (
    id_horario BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    dia_semana dia_semana NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    CONSTRAINT chk_horario_horas CHECK (hora_inicio < hora_fin)
);

-- ============================================================
-- 18. ASIGNACIÓN DE CLASE
-- ============================================================

CREATE TABLE asignacion_clase (
    id_asignacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_grupo BIGINT NOT NULL,
    id_materia BIGINT NOT NULL,
    id_profesor BIGINT NOT NULL,
    id_horario BIGINT NOT NULL,
    id_aula BIGINT NOT NULL,
    modalidad modalidad_clase DEFAULT 'PRESENCIAL',
    estado estado_asignacion DEFAULT 'ACTIVA',
    observaciones TEXT,
    CONSTRAINT fk_asignacion_grupo FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo),
    CONSTRAINT fk_asignacion_materia FOREIGN KEY (id_materia) REFERENCES materia(id_materia),
    CONSTRAINT fk_asignacion_profesor FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor),
    CONSTRAINT fk_asignacion_horario FOREIGN KEY (id_horario) REFERENCES bloque_horario(id_horario),
    CONSTRAINT fk_asignacion_aula FOREIGN KEY (id_aula) REFERENCES aula(id_aula)
);

-- ============================================================
-- 19. TIPOS DE EVALUACIÓN
-- ============================================================

CREATE TABLE tipo_evaluacion (
    id_tipo_evaluacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    porcentaje NUMERIC(5,2) DEFAULT 0,
    CONSTRAINT uq_tipo_evaluacion UNIQUE (nombre),
    CONSTRAINT chk_tipo_porcentaje CHECK (porcentaje >= 0 AND porcentaje <= 100)
);

-- ============================================================
-- 20. EVALUACIONES
-- ============================================================

CREATE TABLE evaluacion (
    id_evaluacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_asignacion BIGINT NOT NULL,
    id_periodo BIGINT NOT NULL,
    id_tipo_evaluacion BIGINT NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    fecha_evaluacion DATE NOT NULL,
    porcentaje NUMERIC(5,2) DEFAULT 0,
    nota_maxima NUMERIC(5,2) DEFAULT 10.00,
    estado estado_evaluacion DEFAULT 'PROGRAMADA',
    CONSTRAINT fk_evaluacion_asignacion FOREIGN KEY (id_asignacion) REFERENCES asignacion_clase(id_asignacion),
    CONSTRAINT fk_evaluacion_periodo FOREIGN KEY (id_periodo) REFERENCES periodo_academico(id_periodo),
    CONSTRAINT fk_evaluacion_tipo FOREIGN KEY (id_tipo_evaluacion) REFERENCES tipo_evaluacion(id_tipo_evaluacion),
    CONSTRAINT chk_evaluacion_porcentaje CHECK (porcentaje >= 0 AND porcentaje <= 100),
    CONSTRAINT chk_evaluacion_nota CHECK (nota_maxima > 0)
);

-- ============================================================
-- 21. CALIFICACIONES
-- ============================================================

CREATE TABLE calificacion (
    id_calificacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_evaluacion BIGINT NOT NULL,
    id_estudiante BIGINT NOT NULL,
    nota_obtenida NUMERIC(5,2),
    observacion VARCHAR(255),
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_calificacion_evaluacion FOREIGN KEY (id_evaluacion) REFERENCES evaluacion(id_evaluacion),
    CONSTRAINT fk_calificacion_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT uq_calificacion UNIQUE (id_evaluacion, id_estudiante),
    CONSTRAINT chk_calificacion_nota CHECK (nota_obtenida >= 0)
);

-- ============================================================
-- 22. NOTA FINAL
-- ============================================================

CREATE TABLE nota_final (
    id_nota_final BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    id_asignacion BIGINT NOT NULL,
    id_periodo BIGINT NOT NULL,
    nota NUMERIC(5,2) NOT NULL,
    estado estado_nota DEFAULT 'PENDIENTE',
    observacion VARCHAR(255),
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_nota_final_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_nota_final_asignacion FOREIGN KEY (id_asignacion) REFERENCES asignacion_clase(id_asignacion),
    CONSTRAINT fk_nota_final_periodo FOREIGN KEY (id_periodo) REFERENCES periodo_academico(id_periodo),
    CONSTRAINT uq_nota_final UNIQUE (id_estudiante, id_asignacion, id_periodo),
    CONSTRAINT chk_nota_final CHECK (nota >= 0 AND nota <= 10)
);

-- ============================================================
-- 23. ASISTENCIA
-- ============================================================

CREATE TABLE asistencia (
    id_asistencia BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    id_asignacion BIGINT NOT NULL,
    fecha DATE NOT NULL,
    estado estado_asistencia NOT NULL,
    observacion VARCHAR(255),
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_asistencia_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_asistencia_asignacion FOREIGN KEY (id_asignacion) REFERENCES asignacion_clase(id_asignacion),
    CONSTRAINT uq_asistencia UNIQUE (id_estudiante, id_asignacion, fecha)
);

-- ============================================================
-- 24. REPRESENTANTES
-- ============================================================

CREATE TABLE representante (
    id_representante BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dui VARCHAR(20),
    correo_electronico VARCHAR(150),
    telefono VARCHAR(30),
    telefono_alternativo VARCHAR(30),
    direccion VARCHAR(255),
    ocupacion VARCHAR(100),
    estado estado_persona DEFAULT 'ACTIVO',
    CONSTRAINT uq_representante_dui UNIQUE (dui)
);

-- ============================================================
-- 25. ESTUDIANTE - REPRESENTANTE
-- ============================================================

CREATE TABLE estudiante_representante (
    id_estudiante BIGINT NOT NULL,
    id_representante BIGINT NOT NULL,
    parentesco VARCHAR(50) NOT NULL,
    es_responsable BOOLEAN DEFAULT FALSE,
    es_contacto_emergencia BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id_estudiante, id_representante),
    CONSTRAINT fk_er_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_er_representante FOREIGN KEY (id_representante) REFERENCES representante(id_representante)
);

-- ============================================================
-- 26. CONTACTOS DE EMERGENCIA
-- ============================================================

CREATE TABLE contacto_emergencia (
    id_contacto BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    parentesco VARCHAR(50),
    telefono VARCHAR(30) NOT NULL,
    telefono_alternativo VARCHAR(30),
    prioridad INTEGER DEFAULT 1,
    CONSTRAINT fk_contacto_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT chk_contacto_prioridad CHECK (prioridad > 0)
);

-- ============================================================
-- 27. USUARIOS
-- ============================================================

CREATE TABLE usuario (
    id_usuario BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    correo VARCHAR(150),
    estado VARCHAR(20) DEFAULT 'ACTIVO',
    ultimo_acceso TIMESTAMP WITH TIME ZONE,
    intentos_fallidos INTEGER DEFAULT 0,
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_usuario_username UNIQUE (username),
    CONSTRAINT uq_usuario_correo UNIQUE (correo),
    CONSTRAINT chk_intentos_fallidos CHECK (intentos_fallidos >= 0)
);

-- ============================================================
-- 28. ROLES
-- ============================================================

CREATE TABLE rol (
    id_rol BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    CONSTRAINT uq_rol_nombre UNIQUE (nombre)
);

-- ============================================================
-- 29. USUARIO - ROL
-- ============================================================

CREATE TABLE usuario_rol (
    id_usuario BIGINT NOT NULL,
    id_rol BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_ur_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_ur_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

-- ============================================================
-- 30. USUARIO - ESTUDIANTE
-- ============================================================

CREATE TABLE usuario_estudiante (
    id_usuario BIGINT PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    CONSTRAINT fk_ue_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_ue_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT uq_usuario_estudiante UNIQUE (id_estudiante)
);

-- ============================================================
-- 31. USUARIO - PROFESOR
-- ============================================================

CREATE TABLE usuario_profesor (
    id_usuario BIGINT PRIMARY KEY,
    id_profesor BIGINT NOT NULL,
    CONSTRAINT fk_up_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_up_profesor FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor),
    CONSTRAINT uq_usuario_profesor UNIQUE (id_profesor)
);

-- ============================================================
-- 32. PERMISOS
-- ============================================================

CREATE TABLE permiso (
    id_permiso BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    CONSTRAINT uq_permiso_nombre UNIQUE (nombre)
);

-- ============================================================
-- 33. ROL - PERMISO
-- ============================================================

CREATE TABLE rol_permiso (
    id_rol BIGINT NOT NULL,
    id_permiso BIGINT NOT NULL,
    PRIMARY KEY (id_rol, id_permiso),
    CONSTRAINT fk_rp_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
    CONSTRAINT fk_rp_permiso FOREIGN KEY (id_permiso) REFERENCES permiso(id_permiso)
);

-- ============================================================
-- 34. DISCIPLINA
-- ============================================================

CREATE TABLE incidente_disciplinario (
    id_incidente BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    fecha DATE NOT NULL,
    tipo nivel_incidente NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    medida_tomada TEXT,
    estado estado_incidente DEFAULT 'ABIERTO',
    id_profesor BIGINT,
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_incidente_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_incidente_profesor FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor)
);

-- ============================================================
-- 35. OBSERVACIONES ACADÉMICAS
-- ============================================================

CREATE TABLE observacion_academica (
    id_observacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    id_profesor BIGINT,
    id_periodo BIGINT,
    fecha DATE NOT NULL,
    tipo tipo_observacion NOT NULL,
    descripcion TEXT NOT NULL,
    CONSTRAINT fk_observacion_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_observacion_profesor FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor),
    CONSTRAINT fk_observacion_periodo FOREIGN KEY (id_periodo) REFERENCES periodo_academico(id_periodo)
);

-- ============================================================
-- 36. ACTIVIDADES
-- ============================================================

CREATE TABLE actividad (
    id_actividad BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_asignacion BIGINT NOT NULL,
    id_periodo BIGINT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    fecha_publicacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_entrega TIMESTAMP WITH TIME ZONE,
    porcentaje NUMERIC(5,2) DEFAULT 0,
    estado estado_actividad DEFAULT 'BORRADOR',
    CONSTRAINT fk_actividad_asignacion FOREIGN KEY (id_asignacion) REFERENCES asignacion_clase(id_asignacion),
    CONSTRAINT fk_actividad_periodo FOREIGN KEY (id_periodo) REFERENCES periodo_academico(id_periodo),
    CONSTRAINT chk_actividad_porcentaje CHECK (porcentaje >= 0 AND porcentaje <= 100),
    CONSTRAINT chk_actividad_fechas CHECK (fecha_entrega IS NULL OR fecha_entrega >= fecha_publicacion)
);

-- ============================================================
-- 37. ENTREGAS
-- ============================================================

CREATE TABLE entrega_actividad (
    id_entrega BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_actividad BIGINT NOT NULL,
    id_estudiante BIGINT NOT NULL,
    fecha_entrega TIMESTAMP WITH TIME ZONE,
    archivo_url VARCHAR(500),
    comentario TEXT,
    nota NUMERIC(5,2),
    estado estado_entrega DEFAULT 'PENDIENTE',
    CONSTRAINT fk_entrega_actividad FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad),
    CONSTRAINT fk_entrega_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT uq_entrega UNIQUE (id_actividad, id_estudiante),
    CONSTRAINT chk_entrega_nota CHECK (nota IS NULL OR nota >= 0)
);

-- ============================================================
-- 38. AVISOS
-- ============================================================

CREATE TABLE aviso (
    id_aviso BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    contenido TEXT NOT NULL,
    fecha_publicacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_expiracion TIMESTAMP WITH TIME ZONE,
    prioridad prioridad_aviso DEFAULT 'NORMAL',
    estado estado_aviso DEFAULT 'BORRADOR',
    id_usuario_autor BIGINT,
    CONSTRAINT fk_aviso_usuario FOREIGN KEY (id_usuario_autor) REFERENCES usuario(id_usuario),
    CONSTRAINT chk_aviso_fechas CHECK (fecha_expiracion IS NULL OR fecha_expiracion >= fecha_publicacion)
);

-- ============================================================
-- 39. AVISOS - GRUPOS
-- ============================================================

CREATE TABLE aviso_grupo (
    id_aviso BIGINT NOT NULL,
    id_grupo BIGINT NOT NULL,
    PRIMARY KEY (id_aviso, id_grupo),
    CONSTRAINT fk_ag_aviso FOREIGN KEY (id_aviso) REFERENCES aviso(id_aviso),
    CONSTRAINT fk_ag_grupo FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo)
);

-- ============================================================
-- 40. EVENTOS
-- ============================================================

CREATE TABLE evento (
    id_evento BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descripcion TEXT,
    fecha_inicio TIMESTAMP WITH TIME ZONE NOT NULL,
    fecha_fin TIMESTAMP WITH TIME ZONE,
    ubicacion VARCHAR(255),
    tipo tipo_evento DEFAULT 'ACADEMICO',
    estado estado_evento DEFAULT 'PROGRAMADO',
    CONSTRAINT chk_evento_fechas CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio)
);

-- ============================================================
-- 41. CONCEPTOS DE PAGO
-- ============================================================

CREATE TABLE concepto_pago (
    id_concepto BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    monto NUMERIC(10,2) NOT NULL,
    obligatorio BOOLEAN DEFAULT FALSE,
    estado estado_concepto DEFAULT 'ACTIVO',
    CONSTRAINT uq_concepto_pago UNIQUE (nombre),
    CONSTRAINT chk_concepto_monto CHECK (monto >= 0)
);

-- ============================================================
-- 42. COBROS
-- ============================================================

CREATE TABLE cobro (
    id_cobro BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    id_concepto BIGINT NOT NULL,
    id_anio_escolar BIGINT NOT NULL,
    fecha_vencimiento DATE,
    monto NUMERIC(10,2) NOT NULL,
    estado estado_cobro DEFAULT 'PENDIENTE',
    observacion VARCHAR(255),
    CONSTRAINT fk_cobro_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    CONSTRAINT fk_cobro_concepto FOREIGN KEY (id_concepto) REFERENCES concepto_pago(id_concepto),
    CONSTRAINT fk_cobro_anio FOREIGN KEY (id_anio_escolar) REFERENCES anio_escolar(id_anio_escolar),
    CONSTRAINT chk_cobro_monto CHECK (monto >= 0)
);

-- ============================================================
-- 43. PAGOS
-- ============================================================

CREATE TABLE pago (
    id_pago BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cobro BIGINT NOT NULL,
    fecha_pago TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    monto NUMERIC(10,2) NOT NULL,
    metodo_pago metodo_pago NOT NULL,
    referencia VARCHAR(100),
    observacion VARCHAR(255),
    id_usuario BIGINT,
    CONSTRAINT fk_pago_cobro FOREIGN KEY (id_cobro) REFERENCES cobro(id_cobro),
    CONSTRAINT fk_pago_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT chk_pago_monto CHECK (monto > 0)
);

-- ============================================================
-- 44. DOCUMENTOS
-- ============================================================

CREATE TABLE documento_estudiante (
    id_documento BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_estudiante BIGINT NOT NULL,
    tipo_documento tipo_documento NOT NULL,
    nombre_archivo VARCHAR(255),
    url_archivo VARCHAR(500),
    fecha_subida TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    estado estado_documento DEFAULT 'PENDIENTE',
    observacion VARCHAR(255),
    CONSTRAINT fk_documento_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante)
);

-- ============================================================
-- 45. NOTIFICACIONES
-- ============================================================

CREATE TABLE notificacion (
    id_notificacion BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    tipo tipo_notificacion DEFAULT 'INFO',
    leida BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_lectura TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_notificacion_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- ============================================================
-- 46. AUDITORÍA
-- ============================================================

CREATE TABLE auditoria (
    id_auditoria BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_usuario BIGINT,
    tabla_afectada VARCHAR(100) NOT NULL,
    id_registro VARCHAR(100),
    accion accion_auditoria NOT NULL,
    datos_anteriores JSONB,
    datos_nuevos JSONB,
    ip INET,
    fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_auditoria_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- ============================================================
-- 47. ÍNDICES
-- ============================================================

CREATE INDEX idx_estudiante_apellidos ON estudiante(apellidos);
CREATE INDEX idx_estudiante_nombres ON estudiante(nombres);
CREATE INDEX idx_matricula_estudiante ON matricula(id_estudiante);
CREATE INDEX idx_matricula_grupo ON matricula(id_grupo);
CREATE INDEX idx_asignacion_grupo ON asignacion_clase(id_grupo);
CREATE INDEX idx_asignacion_profesor ON asignacion_clase(id_profesor);
CREATE INDEX idx_asignacion_horario ON asignacion_clase(id_horario);
CREATE INDEX idx_asignacion_aula ON asignacion_clase(id_aula);
CREATE INDEX idx_evaluacion_periodo ON evaluacion(id_periodo);
CREATE INDEX idx_evaluacion_asignacion ON evaluacion(id_asignacion);
CREATE INDEX idx_calificacion_estudiante ON calificacion(id_estudiante);
CREATE INDEX idx_calificacion_evaluacion ON calificacion(id_evaluacion);
CREATE INDEX idx_asistencia_estudiante ON asistencia(id_estudiante);
CREATE INDEX idx_asistencia_fecha ON asistencia(fecha);
CREATE INDEX idx_actividad_asignacion ON actividad(id_asignacion);
CREATE INDEX idx_entrega_estudiante ON entrega_actividad(id_estudiante);
CREATE INDEX idx_notificacion_usuario ON notificacion(id_usuario);
CREATE INDEX idx_notificacion_no_leida ON notificacion(id_usuario, leida);
CREATE INDEX idx_auditoria_usuario ON auditoria(id_usuario);
CREATE INDEX idx_auditoria_fecha ON auditoria(fecha);
CREATE INDEX idx_cobro_estudiante ON cobro(id_estudiante);
CREATE INDEX idx_cobro_estado ON cobro(estado);

-- ============================================================
-- 48. DATOS INICIALES
-- ============================================================

INSERT INTO rol (nombre, descripcion) VALUES
('ADMIN', 'Administrador del sistema'),
('DIRECTOR', 'Director de la institución'),
('PROFESOR', 'Profesor'),
('ESTUDIANTE', 'Estudiante'),
('REPRESENTANTE', 'Padre o representante'),
('SECRETARIA', 'Personal administrativo'),
('CONTABILIDAD', 'Personal de contabilidad');

INSERT INTO permiso (nombre, descripcion) VALUES
('ESTUDIANTE_LEER', 'Consultar estudiantes'),
('ESTUDIANTE_CREAR', 'Crear estudiantes'),
('ESTUDIANTE_EDITAR', 'Editar estudiantes'),
('ESTUDIANTE_ELIMINAR', 'Eliminar estudiantes'),
('PROFESOR_LEER', 'Consultar profesores'),
('PROFESOR_CREAR', 'Crear profesores'),
('PROFESOR_EDITAR', 'Editar profesores'),
('PROFESOR_ELIMINAR', 'Eliminar profesores'),
('MATERIA_LEER', 'Consultar materias'),
('MATERIA_CREAR', 'Crear materias'),
('MATERIA_EDITAR', 'Editar materias'),
('MATERIA_ELIMINAR', 'Eliminar materias'),
('NOTAS_LEER', 'Consultar calificaciones'),
('NOTAS_CREAR', 'Registrar calificaciones'),
('NOTAS_EDITAR', 'Modificar calificaciones'),
('ASISTENCIA_LEER', 'Consultar asistencia'),
('ASISTENCIA_CREAR', 'Registrar asistencia'),
('ASISTENCIA_EDITAR', 'Modificar asistencia'),
('MATRICULA_LEER', 'Consultar matrículas'),
('MATRICULA_CREAR', 'Crear matrículas'),
('MATRICULA_EDITAR', 'Modificar matrículas'),
('HORARIO_LEER', 'Consultar horarios'),
('HORARIO_CREAR', 'Crear horarios'),
('HORARIO_EDITAR', 'Modificar horarios'),
('PAGOS_LEER', 'Consultar pagos'),
('PAGOS_CREAR', 'Registrar pagos'),
('USUARIOS_LEER', 'Consultar usuarios'),
('USUARIOS_CREAR', 'Crear usuarios'),
('USUARIOS_EDITAR', 'Editar usuarios'),
('USUARIOS_ELIMINAR', 'Eliminar usuarios'),
('REPORTES_LEER', 'Consultar reportes');

INSERT INTO nivel_educativo (nombre, descripcion) VALUES
('Educación Inicial', 'Educación inicial'),
('Educación Básica', 'Educación básica'),
('Educación Media', 'Educación media');

INSERT INTO seccion (nombre, descripcion) VALUES
('A', 'Sección A'),
('B', 'Sección B'),
('C', 'Sección C');

INSERT INTO tipo_evaluacion (nombre, descripcion, porcentaje) VALUES
('Examen', 'Evaluación escrita', 40),
('Tarea', 'Tarea académica', 20),
('Proyecto', 'Proyecto académico', 20),
('Participación', 'Participación en clase', 10),
('Actividad', 'Actividad general', 10);

INSERT INTO configuracion (nombre_institucion, direccion, telefono, correo, moneda, zona_horaria) VALUES
('Institución Educativa', 'El Salvador', NULL, NULL, 'USD', 'America/El_Salvador');

-- Asignar todos los permisos al ADMIN
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r CROSS JOIN permiso p
WHERE r.nombre = 'ADMIN';
