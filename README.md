<div align="center">

# 📚 API Libros

**API RESTful para gestión de libros y usuarios**

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.7.1-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

[Descripción](#-descripción) • [Características](#-características) • [Arquitectura](#-arquitectura) • [Instalación](#-instalación) • [API](#-api) • [Base de Datos](#-base-de-datos)

---

</div>

## 📖 Descripción

API Libros es una aplicación backend construida con **Spring Boot** que permite gestionar un catálogo de libros y usuarios. Proyecto desarrollado siguiendo los principios de **Arquitectura Hexagonal** (Puertos y Adaptadores), garantizando una separación clara entre la lógica de negocio y los mecanismos de infraestructura.

## ✨ Características

### Funcionalidades Principales

| Módulo | Funcionalidades |
|--------|-----------------|
| 📚 **Libros** | Crear, listar, restar inventario |
| 👤 **Usuarios** | Crear, listar, actualizar, cambio de contraseña |
| 🔄 **Inventario** | Gestión de stock con operaciones atómicas |
| 📄 **Documentación** | Swagger/OpenAPI integrado |
| 🌐 **CORS** | Configurado para frontend en `localhost:4321` |

### Stack Tecnológico

| Capa | Tecnología |
|------|------------|
| **Framework** | Spring Boot 4.1.1 |
| **Lenguaje** | Java 25 |
| **Base de Datos** | PostgreSQL |
| **ORM** | Spring Data JPA / Hibernate |
| **Migraciones** | Flyway |
| **Build Tool** | Gradle 9.7.1 |
| **Contenedorización** | Docker Compose |
| **Documentación API** | SpringDoc OpenAPI (Swagger UI) |
| **Validación** | Jakarta Bean Validation |
| **Utilidades** | Lombok |

## 🏗️ Arquitectura

El proyecto sigue el patrón de **Arquitectura Hexagonal** (Puertos y Adaptadores):

```
src/main/java/com/aviles/api/libros/
│
├── Application.java                    # Entry point de la aplicación
│
├── libros/                             # 📚 Módulo de Libros
│   ├── application/
│   │   ├── service/
│   │   │   └── LibrosService.java      # Lógica de negocio
│   │   └── port/
│   │       ├── in/                     # Puertos de entrada
│   │       │   ├── GetAllLibrosCase.java
│   │       │   ├── NewLibroCase.java
│   │       │   └── RestarLibroCase.java
│   │       └── out/
│   │           └── LibroRepositoryPort.java  # Puerto de salida
│   └── domain/
│       ├── Libro.java                  # Entidad de dominio
│       └── values/                     # Objetos de valor
│           ├── Id.java
│           ├── DataString.java
│           └── Cantidad.java
│
├── usuarios/                           # 👤 Módulo de Usuarios
│   ├── application/
│   │   ├── service/
│   │   │   └── UsuarioService.java
│   │   └── port/
│   │       ├── in/
│   │       │   ├── CreateUserCase.java
│   │       │   ├── GetAlluserCase.java
│   │       │   ├── UpdateUserCase.java
│   │       │   └── UpdatePassUserCase.java
│   │       └── out/
│   │           └── UsuarioRepositoryPort.java
│   ├── domain/
│   │   ├── Usuario.java
│   │   └── values/
│   │       ├── Nombre.java
│   │       ├── Correo.java
│   │       ├── Contrasema.java
│   │       └── Fecha.java
│   └── infra/
│       └── adapter/
│           └── in/
│               └── web/
│                   ├── UsuarioController.java
│                   └── dto/
│                       ├── UsuariosRequest.java
│                       └── UsuariosReponse.java
│
├── config/
│   └── WebConfig.java                  # Configuración CORS
│
└── utils/
    └── Code/
        └── GenerateCode.java           # Generador de códigos
```

### Diagrama de Arquitectura

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENTES (REST)                          │
└───────────────────────────┬─────────────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────────────┐
│                     ADAPTADORES DE ENTRADA                      │
│  ┌─────────────────────┐    ┌─────────────────────┐            │
│  │  UsuarioController  │    │   LibroController    │            │
│  │   /api/v1/usuarios  │    │    /api/v1/libros    │            │
│  └──────────┬──────────┘    └──────────┬──────────┘            │
└─────────────┼──────────────────────────┼───────────────────────┘
              │                          │
┌─────────────▼──────────────────────────▼───────────────────────┐
│                      PUERTOS DE ENTRADA                        │
│  ┌─────────────────────┐    ┌─────────────────────┐            │
│  │    CreateUserCase   │    │    NewLibroCase      │            │
│  │    GetAlluserCase   │    │    GetAllLibrosCase  │            │
│  │    UpdateUserCase   │    │    RestarLibroCase   │            │
│  └──────────┬──────────┘    └──────────┬──────────┘            │
└─────────────┼──────────────────────────┼───────────────────────┘
              │                          │
┌─────────────▼──────────────────────────▼───────────────────────┐
│                     CAPA DE SERVICIOS                          │
│  ┌─────────────────────┐    ┌─────────────────────┐            │
│  │   UsuarioService    │    │   LibrosService      │            │
│  └──────────┬──────────┘    └──────────┬──────────┘            │
└─────────────┼──────────────────────────┼───────────────────────┘
              │                          │
┌─────────────▼──────────────────────────▼───────────────────────┐
│                     DOMINIO / MODELOS                          │
│  ┌─────────────────────┐    ┌─────────────────────┐            │
│  │      Usuario        │    │       Libro          │            │
│  │  - Nombre           │    │  - Titulo            │            │
│  │  - Correo           │    │  - Autor             │            │
│  │  - ContrasenaHash   │    │  - Descripcion       │            │
│  │  - Fecha            │    │  - UrlImagen         │            │
│  └──────────┬──────────┘    │  - Cantidad          │            │
└─────────────┼───────────────┴──────────┬───────────────────────┘
              │                          │
┌─────────────▼──────────────────────────▼───────────────────────┐
│                     PUERTOS DE SALIDA                          │
│  ┌─────────────────────┐    ┌─────────────────────┐            │
│  │UsuarioRepositoryPort│    │ LibroRepositoryPort  │            │
│  └──────────┬──────────┘    └──────────┬──────────┘            │
└─────────────┼──────────────────────────┼───────────────────────┘
              │                          │
┌─────────────▼──────────────────────────▼───────────────────────┐
│                    ADAPTADORES DE SALIDA                       │
│              ┌─────────────────────────────┐                   │
│              │    Spring Data JPA + Flyway  │                   │
│              │        PostgreSQL           │                   │
│              └─────────────────────────────┘                   │
└───────────────────────────────────────────────────────────────┘
```

## 🚀 Instalación

### Prerrequisitos

- Java 25 o superior
- Docker y Docker Compose
- Gradle 9.7.1 (incluido en el wrapper)

### Pasos para Ejecutar

**1. Clonar el repositorio**

```bash
git clone https://github.com/usuario/api-libros.git
cd api-libros
```

**2. Iniciar la base de datos con Docker Compose**

```bash
docker compose up -d
```

Esto iniciará un contenedor PostgreSQL con la siguiente configuración:

| Parámetro | Valor |
|-----------|-------|
| Puerto | `5432` |
| Base de datos | `libros` |
| Usuario | `admin` |
| Contraseña | `admin` |

**3. Ejecutar la aplicación**

```bash
./gradlew bootRun
```

La aplicación estará disponible en: **http://localhost:8080**

### Variables de Entorno

La aplicación se configura mediante `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/libros
spring.datasource.username=admin
spring.datasource.password=admin

# Pool de conexiones
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false

# Desarrollo
spring.devtools.restart.enabled=true
```

## 📡 API Endpoints

### Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/usuarios` | Crear un usuario |
| `GET` | `/api/v1/usuarios/all` | Obtener todos los usuarios |
| `PUT` | `/api/v1/usuarios/{id}?password=xxx` | Actualizar un usuario |

#### Ejemplo: Crear Usuario

**Request:**
```http
POST /api/v1/usuarios
Content-Type: application/json

{
  "nombre_usuario": "Juan Pérez",
  "correo": "juan.perez@example.com",
  "contrasena_hash": "hashed_password"
}
```

**Response:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "nombre": "Juan Pérez",
  "email": "juan.perez@example.com"
}
```

### Libros

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/libros` | Crear un libro |
| `GET` | `/api/v1/libros` | Obtener todos los libros (paginado) |
| `PUT` | `/api/v1/libros/{id}/restar` | Restar 1 del inventario |

#### Ejemplo: Crear Libro

**Request:**
```http
POST /api/v1/libros
Content-Type: application/json

{
  "titulo": "Cien Años de Soledad",
  "autor": "Gabriel García Márquez",
  "descripcion": "Una obra maestra del realismo mágico",
  "url_imagen": "https://ejemplo.com/imagen.jpg",
  "cantidad": 10
}
```

### 📚 Documentación Swagger

La documentación interactiva de la API está disponible en:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs

## 🗄️ Base de Datos

### Esquema de Tablas

```sql
┌─────────────────────────────────────────────────────────────┐
│                        usuarios                             │
├─────────────────────────────────────────────────────────────┤
│ id UUID (PK)                                                │
│ nombre_usuario VARCHAR(50) NOT NULL UNIQUE                  │
│ correo VARCHAR(255) NOT NULL UNIQUE                         │
│ contrasena_hash VARCHAR(255) NOT NULL                       │
│ fecha_creacion TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP        │
│ fecha_actualizacion TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                         libros                              │
├─────────────────────────────────────────────────────────────┤
│ id UUID (PK)                                                │
│ titulo VARCHAR(255) NOT NULL                                │
│ autor VARCHAR(150) NOT NULL                                 │
│ descripcion TEXT NOT NULL                                   │
│ url_imagen TEXT                                             │
│ cantidad INTEGER DEFAULT 0                                  │
│ fecha_creacion TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP        │
│ fecha_actualizacion TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                      calificaciones                         │
├─────────────────────────────────────────────────────────────┤
│ id UUID (PK)                                                │
│ usuario_id UUID (FK) → usuarios(id) ON DELETE CASCADE       │
│ libro_id UUID (FK) → libros(id) ON DELETE CASCADE           │
│ puntuacion SMALLINT CHECK (1-5)                             │
│ UNIQUE(usuario_id, libro_id)                                │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                       comentarios                           │
├─────────────────────────────────────────────────────────────┤
│ id UUID (PK)                                                │
│ usuario_id UUID (FK) → usuarios(id) ON DELETE CASCADE       │
│ libro_id UUID (FK) → libros(id) ON DELETE CASCADE           │
│ contenido TEXT NOT NULL                                     │
└─────────────────────────────────────────────────────────────┘
```

### Índices Optimizados

```sql
-- Libros
CREATE INDEX idx_libros_titulo ON libros (titulo);
CREATE INDEX idx_libros_autor ON libros (autor);
CREATE INDEX idx_libros_fecha_creacion ON libros (fecha_creacion DESC);

-- Calificaciones
CREATE INDEX idx_calificaciones_libro_id ON calificaciones (libro_id);
CREATE INDEX idx_calificaciones_usuario_id ON calificaciones (usuario_id);

-- Comentarios
CREATE INDEX idx_comentarios_libro_id ON comentarios (libro_id);
CREATE INDEX idx_comentarios_usuario_id ON comentarios (usuario_id);
CREATE INDEX idx_comentarios_libro_fecha ON comentarios (libro_id, fecha_creacion DESC);
```

## 🧪 Testing

### Ejecutar Todos los Tests

```bash
./gradlew test
```

### Ejecutar Tests Específicos

```bash
# Tests de LibrosService
./gradlew test --tests "com.aviles.api.libros.libros.application.service.LibrosServiceTest"

# Tests de CreateLibro
./gradlew test --tests "com.aviles.api.libros.libros.application.service.LibrosServiceTest$CreateLibroTests"

# Tests de RestarLibro
./gradlew test --tests "com.aviles.api.libros.libros.application.service.LibrosServiceTest$RestarLibroTests"

# Tests de GetAllLibros
./gradlew test --tests "com.aviles.api.libros.libros.application.service.LibrosServiceTest$GetAllLibrosTests"
```

### Ver Reportes de Tests

Los reportes HTML se generan en: `build/reports/tests/test/index.html`

## 🛠️ Desarrollo

### Comandos Útiles

```bash
# Compilar el proyecto
./gradlew build

# Limpiar y compilar
./gradlew clean build

# Ejecutar la aplicación
./gradlew bootRun

# Ver dependencias
./gradlew dependencies

# Generar JAR ejecutable
./gradlew bootJar
```

### Estructura de Pruebas

```
src/test/java/com/aviles/api/libros/
├── ApplicationTests.java
└── libros/
    └── application/
        └── service/
            └── LibrosServiceTest.java
                ├── CreateLibroTests
                ├── GetAllLibrosTests
                └── RestarLibroTests
```

## 🐳 Docker

### docker-compose.yml

```yaml
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=libros'
      - 'POSTGRES_PASSWORD=admin'
      - 'POSTGRES_USER=admin'
    ports:
      - '5432:5432'
```

### Comandos Docker

```bash
# Iniciar servicios
docker compose up -d

# Ver logs
docker compose logs -f

# Detener servicios
docker compose down

# Detener y eliminar volúmenes
docker compose down -v
```

## 📁 Archivos Importantes

| Archivo | Descripción |
|---------|-------------|
| `build.gradle` | Configuración de dependencias y plugins |
| `compose.yaml` | Configuración de Docker Compose |
| `application.properties` | Configuración de la aplicación |
| `sql/data.sql` | Script de creación de tablas e índices |
| `gradlew` / `gradlew.bat` | Wrapper de Gradle |

## 🤝 Contribuir

1. Fork el proyecto
2. Crear una rama para la nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Hacer commit de los cambios (`git commit -m 'Add nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**aviles** - Desarrollador del proyecto API Libros

---

<div align="center">

**Hecho con ❤️ usando Spring Boot y Arquitectura Hexagonal**

[⬆ Volver arriba](#-api-libros)

</div>
