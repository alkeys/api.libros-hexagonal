-- ============================================================
-- SISTEMA ESCOLAR - PostgreSQL - Flyway Migration V2
-- ------------------------------------------------------------
-- Relleno de roles para cuentas creadas antes de la gestión de
-- roles por usuario.
--
-- La cuenta 'admin' creada antes de que existieran los roles en
-- usuario_rol (o antes del rol ADMIN) quedaba sin ningún rol y,
-- al iniciar sesión, el sistema la trataba como ESTUDIANTE por
-- defecto (sin acceso administrativo).
--
-- Esta migración es el complemento de DataInitializer.seedAdmin():
-- ese componente solo asigna el rol ADMIN cuando crea la cuenta,
-- pero si el usuario 'admin' ya existía (era de antes) lo omitía
-- y quedaba sin rol. Aquí se le asigna el rol ADMIN si aún no lo
-- tiene. Es segura si no existe el usuario o el rol (no inserta
-- nada).
-- ============================================================

INSERT INTO usuario_rol (id_usuario, id_rol)
SELECT u.id_usuario, r.id_rol
FROM usuario u
JOIN rol r ON LOWER(r.nombre) = 'admin'
WHERE LOWER(u.username) = 'admin'
  AND NOT EXISTS (
      SELECT 1
      FROM usuario_rol ur
      WHERE ur.id_usuario = u.id_usuario
        AND ur.id_rol = r.id_rol
  );
