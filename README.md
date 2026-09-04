<div align="center">

# 🏫 Sistema Escolar

**Plataforma integral de gestión académica** — backend REST + frontend web
<br />
*Estudiantes · Profesores · Matrículas · Horarios · Evaluaciones · Notas · Asistencia · Pagos · Usuarios y Roles*

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.7.1-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Astro](https://img.shields.io/badge/Astro-7.3.1-FF5D01?style=for-the-badge&logo=astro&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

[Descripción](#-descripción) •
[Características](#-características) •
[Stack](#-stack-tecnológico) •
[Arquitectura](#-arquitectura) •
[Inicio rápido](#-inicio-rápido) •
[Cuentas demo](#-cuentas-demo) •
[Seguridad](#-seguridad-y-roles) •
[API](#-api-rest) •
[Frontend](#-frontend) •
[Base de datos](#-base-de-datos) •
[Desarrollo](#-desarrollo)

---

</div>

## 📖 Descripción

**Sistema Escolar** es una aplicación completa para la administración de una institución educativa (proyectada para El Salvador). Consta de dos partes:

| Parte | Tecnología | Carpeta |
|-------|-----------|---------|
| 🔌 **API REST** | Spring Boot 4 (Arquitectura Hexagonal) | raíz del repo (`api.escuela`) |
| 🎨 **Frontend web** | Astro + Tailwind CSS | [`front/`](front/) |

El backend implementa **JWT + roles** (ADMIN / PROFESOR / ESTUDIANTE, entre otros) con permisos por endpoint y un **portal del estudiante** donde cada alumno consulta sus calificaciones. Los profesores capturan notas **únicamente de las clases que tienen asignadas**.

## ✨ Características

### Módulos funcionales

| Módulo | Funcionalidades principales |
|--------|-----------------------------|
| 👥 **Estudiantes** | CRUD, matrículas por grupo, contactos de emergencia |
| 🧑‍🏫 **Profesores** | CRUD con especialidad y datos de contacto |
| 📚 **Materias** | Catálogo de asignaturas |
| 🪜 **Niveles, Grados y Secciones** | Estructura académica (Inicial, Básica, Media…) |
| 🏫 **Grupos** | Grupos por grado/sección/año escolar |
| 🕐 **Horarios y Aulas** | Aulas, bloques horarios y asignaciones clase→profesor |
| 🗓️ **Años Escolares y Períodos** | Años lectivos y períodos académicos |
| 🧑‍🤝‍🧑 **Representantes** | Padres/encargados |
| 📝 **Evaluaciones y Calificaciones** | Tipos de evaluación, captura de notas por clase, portal del alumno |
| ✅ **Asistencia** | Registro por clase y fecha |
| 🔐 **Usuarios, Roles y Permisos** | Cuentas, autenticación JWT, asignación de roles desde la UI |
| ⚙️ **Configuración** | Datos de la institución, escala de notas |

### Seguridad

- 🔑 Autenticación con **JWT Bearer** (BCrypt para contraseñas)
- 🛡️ **Autorización por rol por endpoint** vía interceptor
- 📌 Los **profesores** solo leen/escriben calificaciones y asistencia de **sus propias clases** (verificado en backend, no solo en UI)
- 🎓 Los **estudiantes** solo consultan **sus propias** notas
- ✅ Siembra idempotente de cuentas demo (no rompe el arranque si el correo ya existe)

## 🧰 Stack Tecnológico

| Capa | Tecnología |
|------|------------|
| **Lenguaje (back)** | Java 25 |
| **Framework (back)** | Spring Boot 4.1.1 |
| **Persistencia** | Spring Data JPA + Hibernate |
| **Migraciones** | Flyway |
| **Base de datos** | PostgreSQL |
| **Seguridad** | JWT (jjwt 0.12) + Spring Security Crypto (BCrypt) |
| **Documentación API** | SpringDoc OpenAPI (Swagger UI) |
| **Build back** | Gradle 9.7.1 (wrapper incluido) |
| **Frontend** | Astro 7.3.1 + Tailwind CSS 4 |
| **Package manager (front)** | pnpm |
| **Infraestructura local** | Docker Compose |

## 🏗️ Arquitectura

El backend sigue **Arquitectura Hexagonal** (puertos y adaptadores): cada módulo separa su dominio, los casos de uso (`application`), y la infraestructura (`infra` — controladores REST y persistencia JPA).

```
src/main/java/com/aviles/api/escuela/
│
├── Application.java                     # Entry point
│
├── config/                              # WebConfig (CORS + interceptor), DataInitializer
│                                        #   (cuentas demo), PasswordConfig (BCrypt),
│                                        #   GlobalExceptionHandler (errores JSON)
│
├── auth/                                # 🔐 Módulo de autenticación
│   ├── application/                     #   JwtService, AuthContext
│   ├── domain/                          #   AuthUser (id, roles, vínculos)
│   └── infra/                           #   JwtAuthFilter, AuthInterceptor
│
├── <modulo>/                            # Ej.: estudiantes, profesores, materias,
│   ├── domain/                          #   niveles, grupos, horarios, anioescolar,
│   ├── application/                     #   representantes, evaluaciones, asistencia,
│   │   ├── port/in/                     #   usuarios, configuracion, pagos, documentos
│   │   └── service/
│   └── infra/adapter/
│       ├── in/web/                      #   Controller + DTOs (REST)
│       └── out/persistence/             #   JPA (entidades + adaptadores)
│
└── shared/domain/values/Id.java         # Objeto de valor compartido
```

```
┌───────────────────────────────┐        ┌───────────────────────────────┐
│       FRONTEND (Astro)        │        │   Swagger UI / Clientes REST   │
│   front/ · http://localhost:4321      │   /swagger-ui.html              │
└───────────────┬───────────────┘        └───────────────┬───────────────┘
                │  HTTP + Authorization: Bearer <JWT>     │
┌───────────────▼────────────────────────────────────────▼───────────────┐
│                       ADAPTADORES DE ENTRADA (REST)                    │
│   UsuarioController · EstudianteController · EvaluacionController …   │
├────────────────────────────────────────────────────────────────────────┤
│         JwtAuthFilter → AuthContext → AuthInterceptor (roles)          │
├────────────────────────────────────────────────────────────────────────┤
│            CASOS DE USO (application/port/in → service)                │
├────────────────────────────────────────────────────────────────────────┤
│                       DOMINIO (domain/)                                │
├────────────────────────────────────────────────────────────────────────┤
│            PUERTOS DE SALIDA → ADAPTADORES JPA (persistence)           │
└───────────────────────────────┬────────────────────────────────────────┘
                                ▼
              ┌────────────────────────────────────┐
              │  PostgreSQL (Flyway: V1, V2, …)    │
              └────────────────────────────────────┘
```

## 🚀 Inicio Rápido

### Prerrequisitos

- Java **25**
- Node.js **≥ 22.12** y pnpm
- Docker + Docker Compose
- Gradle (opcional — se usa el wrapper `./gradlew`)

### 1. Base de datos (PostgreSQL vía Docker)

```bash
docker compose up -d
```

| Parámetro | Valor |
|-----------|-------|
| Puerto | `5432` |
| Base de datos | `sistema_escolar` |
| Usuario | `admin` |
| Contraseña | `admin` |

> Las tablas y datos base se crean automáticamente con **Flyway** al arrancar.

### 2. Backend (Spring Boot)

```bash
./gradlew bootRun
```

- API disponible en **http://localhost:8080**
- Swagger UI: **http://localhost:8080/swagger-ui.html**
- OpenAPI JSON: **http://localhost:8080/v3/api-docs**

### 3. Frontend (Astro)

```bash
cd front
pnpm install
pnpm dev
```

- Aplicación web en **http://localhost:4321** (el frontend llama a la API de `localhost:8080` directamente vía CORS, configurado en `WebConfig`)

### 📝 Nota sobre arranque

`DataInitializer` siembra las cuentas demo de forma **idempotente**: si un correo ya está en uso por otro usuario, no intenta duplicarlo y lo informa en el log (⚠️ evita el error de llave duplicada al arrancar sobre una BD existente).

## 👤 Cuentas Demo

| Usuario | Contraseña | Rol | Vínculo |
|---------|-----------|-----|---------|
| `admin` | `admin` | ADMIN | — |
| `profesor.demo` | `profesor123` | PROFESOR | Profesor vinculado |
| `estudiante.demo` | `estudiante123` | ESTUDIANTE | Estudiante vinculado |

> ⚠️ **Si una cuenta con el correo `admin@admin.com` ya existe** (p. ej. creada antes bajo otro username), la siembra **no** crea un segundo `admin` y debes entrar con esa cuenta existente. El mensaje exacto aparece en el log del backend al arrancar.

## 🔐 Seguridad y Roles

La autenticación usa **JWT**:

```
POST /api/v1/usuarios/login
{ "username": "admin", "password": "admin" }
→ { "token": "eyJ…", "roles": ["ADMIN"], "idProfesor": null, "idEstudiante": null, … }
```

Las siguientes peticiones envían: `Authorization: Bearer <token>`.

| Rol | Lectura | Escritura |
|-----|---------|-----------|
| **ADMIN** | Todo | Todo |
| **PROFESOR** | Todo (catálogos + matrículas) | Calificaciones y asistencia. Las **calificaciones** solo de **sus clases** |
| **ESTUDIANTE** | Catálogos + **sus propias** notas | — |
| Otros / sin sesión | — | — |

Reglas clave del `AuthInterceptor`:
- Público: `POST /usuarios/login` y Swagger.
- `/api/v1/usuarios**`, `/api/v1/configuracion` (escritura) → solo ADMIN.
- Un PROFESOR recibe 403 si intenta calificar una evaluación de otra clase o a un estudiante no matriculado en su grupo (verificación en el backend).
- Los estudiantes se redirigen a su **portal de notas** (`/mis-notas`).

## 📡 API REST

Todas las rutas cuelgan de `/api/v1` y devuelven JSON. CRUD completo (crear/listar/actualizar/eliminar) en cada recurso.

| Módulo | Base path |
|--------|-----------|
| Estudiantes (+ matrículas) | `/api/v1/estudiantes` |
| Profesores | `/api/v1/profesores` |
| Materias | `/api/v1/materias` |
| Niveles · Grados · Secciones | `/api/v1/niveles` |
| Grupos | `/api/v1/grupos` |
| Horarios · Aulas · Asignaciones | `/api/v1/horarios` |
| Años Escolares · Períodos | `/api/v1/anios-escolares` |
| Representantes | `/api/v1/representantes` |
| Evaluaciones · Calificaciones | `/api/v1/evaluaciones` |
| Asistencia | `/api/v1/asistencia` |
| Usuarios · Roles · Login | `/api/v1/usuarios` |
| Configuración | `/api/v1/configuracion` |

### Ejemplos destacados

**Iniciar sesión y obtener JWT**
```http
POST /api/v1/usuarios/login
Content-Type: application/json

{ "username": "profesor.demo", "password": "profesor123" }
```

**Asignar roles a un usuario (ADMIN)**
```http
PUT /api/v1/usuarios/{id}/roles
Content-Type: application/json
Authorization: Bearer <token-admin>

{ "roles": ["PROFESOR"] }
```

**Listar roles disponibles (ADMIN)**
```http
GET /api/v1/usuarios/roles
Authorization: Bearer <token-admin>
```

**Capturar / actualizar una calificación (profesor de esa clase)**
```http
POST /api/v1/evaluaciones/calificaciones
Content-Type: application/json
Authorization: Bearer <token-profesor>

{ "idEvaluacion": 1, "idEstudiante": 2, "notaObtenida": 8.5, "observacion": null }
```

**Notas de un estudiante (portal del alumno / ADMIN)**
```http
GET /api/v1/evaluaciones/estudiante/{idEstudiante}/calificaciones
Authorization: Bearer <token-estudiante>
```

### 📚 Documentación Swagger

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs

## 🎨 Frontend

Aplicación web **Astro + Tailwind CSS** en [`front/`](front/) con diseño responsive, login, barra lateral por rol y páginas CRUD dinámicas.

| Página | Ruta | Acceso |
|--------|------|--------|
| Inicio (dashboard) | `/` | Autenticados |
| Iniciar sesión | `/login` | Público |
| Estudiantes | `/estudiantes` | ADMIN / PROFESOR |
| Profesores | `/profesores` | ADMIN / PROFESOR |
| Materias | `/materias` | ADMIN / PROFESOR |
| Niveles y Grados | `/niveles` | ADMIN / PROFESOR |
| Grupos | `/grupos` | ADMIN / PROFESOR |
| Horarios y Aulas | `/horarios` | ADMIN / PROFESOR |
| Evaluaciones | `/evaluaciones` | ADMIN / PROFESOR |
| **Notas** (captura) | `/notas` | ADMIN / PROFESOR (solo sus clases) |
| Asistencia | `/asistencia` | ADMIN / PROFESOR |
| Años Escolares | `/anios-escolares` | ADMIN / PROFESOR |
| Representantes | `/representantes` | ADMIN / PROFESOR |
| **Mis Notas** (portal alumno) | `/mis-notas` | ESTUDIANTE |
| Usuarios y Roles | `/usuarios` | ADMIN |
| Configuración | `/configuracion` | ADMIN |

## 🗄️ Base de Datos

- **Motor:** PostgreSQL
- **Migraciones:** Flyway (`src/main/resources/db/migration/`)
  - `V1__create_school_system.sql` — esquema completo (≈47 secciones) + datos iniciales (roles, permisos, niveles, secciones, config)
  - `V2__asignar_rol_admin_usuarios_existentes.sql` — rellena el rol ADMIN a cuentas `admin` previas a la gestión de roles

Principales tablas: `usuario`, `rol`, `permiso`, `usuario_rol`, `usuario_profesor`, `usuario_estudiante`, `estudiante`, `matricula`, `profesor`, `materia`, `nivel_educativo`, `grado`, `seccion`, `grupo`, `aula`, `bloque_horario`, `asignacion_clase`, `anio_escolar`, `periodo_academico`, `tipo_evaluacion`, `evaluacion`, `calificacion`, `nota_final`, `asistencia`, `representante`, `configuracion`, `concepto_pago`, `cobro`, `pago`, `documento_estudiante`…

> `ddl-auto=none`: el esquema lo controla exclusivamente **Flyway**.

## 🛠️ Desarrollo

### Comandos útiles (backend)

```bash
./gradlew compileJava        # Compilar
./gradlew bootRun            # Ejecutar la API
./gradlew test               # Tests
./gradlew clean build        # Limpiar y compilar
```

### Comandos útiles (frontend)

```bash
cd front
pnpm dev                     # Servidor de desarrollo (4321)
pnpm build                   # Build de producción
pnpm preview                 # Previsualizar el build
```

### Configuración

Backend: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_escolar
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=none
app.jwt.secret=Cl4v3Sup3rSecret4...CambiaEstoEnProduccion
app.jwt.expiration-minutes=480
```

> 🔒 En producción cambia `app.jwt.secret`, credenciales de BD y CORS.

Frontend: la URL de la API se configura con la variable `PUBLIC_API_URL` (por defecto `http://localhost:8080/api/v1`).

### Docker

```bash
docker compose up -d        # Iniciar PostgreSQL
docker compose logs -f      # Ver logs
docker compose down         # Detener (mantiene datos)
docker compose down -v      # Detener y borrar datos
```

## 📁 Estructura del Repositorio

```
api.escuela/
├── src/main/                # Backend Spring Boot (hexagonal)
├── src/test/                # Tests
├── front/                   # Frontend Astro + Tailwind
├── build.gradle             # Dependencias del backend
├── compose.yaml             # PostgreSQL para desarrollo
├── README.md                # Este documento
└── gradlew                  # Wrapper de Gradle
```

## 🤝 Contribuir

1. Haz *fork* del repositorio.
2. Crea tu rama: `git checkout -b feature/mi-mejora`.
3. Realiza cambios y haz *commit*.
4. Sube la rama: `git push origin feature/mi-mejora`.
5. Abre un *Pull Request*.

## 👨‍💻 Autor

**aviles** — API y frontend del Sistema Escolar.

---

<div align="center">

**Hecho con ❤️ usando Spring Boot (Arquitectura Hexagonal) + Astro**

[⬆ Volver arriba](#-sistema-escolar)

</div>
