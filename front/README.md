<div align="center">

# 🎨 Sistema Escolar — Frontend

**Aplicación web del Sistema Escolar** — Astro + Tailwind CSS
<br />
*Login JWT · Dashboard · CRUDs por módulo · Captura de notas · Portal del estudiante · Roles y permisos*

![Astro](https://img.shields.io/badge/Astro-7.3.1-FF5D01?style=for-the-badge&logo=astro&logoColor=white)
![Tailwind](https://img.shields.io/badge/Tailwind_CSS-4.3.3-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-strict-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Node](https://img.shields.io/badge/Node-%E2%89%A522.12-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)
![pnpm](https://img.shields.io/badge/pnpm-F69220?style=for-the-badge&logo=pnpm&logoColor=white)

---

[Descripción](#-descripción) •
[Stack](#-stack-tecnológico) •
[Estructura](#-estructura) •
[Páginas](#-páginas) •
[Cómo funciona](#-cómo-funciona) •
[Cliente API y sesión](#-cliente-api-y-sesión) •
[Diseño](#-diseño-y-componentes) •
[Inicio rápido](#-inicio-rápido) •
[Desarrollo](#-desarrollo)

---

</div>

## 📖 Descripción

Frontend del **Sistema Escolar** (backend REST en la [raíz del repositorio](../README.md)). Está construido con **Astro + Tailwind CSS 4** y JavaScript moderno (sin framework de componentes): cada página `.astro` monta su lógica con *scripts* nativos y módulos `lib/` reutilizables.

La aplicación consume la API en `http://localhost:8080/api/v1` directamente vía **CORS** (configurado en el backend, `WebConfig`), adjuntando el **JWT** en cada petición.

### Funcionalidades clave

- 🔐 **Login JWT** con redirección según rol (ADMIN / PROFESOR / ESTUDIANTE)
- 📊 **Dashboard** con tarjetas de conteo, gráfica de género y estados
- 📝 **CRUDs dinámicos** generados por una misma factoría (tabla + buscador + modal)
- 🧑‍🏫 **Captura de notas** por clase — el profesor solo ve/edita **sus clases** (también verificado en backend)
- 🎓 **Portal del estudiante** (`/mis-notas`) — cada alumno consulta únicamente sus calificaciones
- 🧩 **Menú lateral por rol** y protección de rutas en el cliente

## 🧰 Stack Tecnológico

| Capa | Tecnología | Detalle |
|------|-----------|---------|
| **Framework** | [Astro](https://astro.build) `7.3.1` | Páginas `.astro`, renderizado en el servidor |
| **Estilos** | Tailwind CSS `4.3.3` | Plugin `@tailwindcss/vite` + `global.css` con tokens |
| **Fuente** | Plus Jakarta Sans | Google Fonts (400–800) |
| **Lenguaje de página** | JavaScript (`type="module"`) | Sin framework de componentes |
| **TypeScript** | `astro/tsconfigs/strict` | Solo para el tooling (`.astro`/tipos) |
| **Runtime** | Node.js **≥ 22.12** | `engines` en `package.json` |
| **Package manager** | pnpm | Monorepo workspace (`pnpm-workspace.yaml`) |

> **Nota:** no hay dependencias de UI ni de estado: tablas, modales, toasts y formularios son módulos propios en `src/lib/` sobre Tailwind.

## 📁 Estructura

```text
front/
├── public/                       # Estáticos (favicon.svg, favicon.ico)
├── src/
│   ├── layouts/
│   │   └── Layout.astro          # Layout principal: sidebar por rol, header,
│   │                             #   sesión, logout, estado de la API
│   ├── pages/                    # Una ruta por archivo .astro (16 páginas)
│   │   ├── login.astro
│   │   ├── index.astro           # Dashboard
│   │   ├── estudiantes.astro     # … (ver tabla de Páginas)
│   │   └── …
│   ├── lib/                      # Lógica de cliente reutilizable
│   │   ├── api.js                # Cliente fetch + JWT + manejo de errores/401
│   │   ├── session.js            # Sesión en localStorage
│   │   ├── crud.js               # Factoría createCrudPage(config)
│   │   └── ui.js                 # toast, openModal, badge, esc, formatos
│   └── styles/
│       └── global.css            # Tokens de color/fuente + clases base
├── astro.config.mjs              # @tailwindcss/vite
├── package.json
└── tsconfig.json                 # strict
```

## 🗺️ Páginas

Todas las rutas son públicas en el servidor; el **control de acceso** ocurre en el cliente:
`Layout.astro` redirige a `/login` sin sesión, manda a `/mis-notas` a los estudiantes, y oculta los enlaces del menú que no corresponden al rol de la sesión. (La autorización *real* de datos se valida en el backend.)

| Página | Ruta | Rol que la usa | Qué hace |
|--------|------|----------------|----------|
| 🔑 Iniciar sesión | `/login` | Público | Login → guarda JWT y redirige según rol |
| 📊 Inicio | `/` | Autenticados | Dashboard: nombre de la institución, conteos por módulo, gráfica de género y estados |
| 👥 Estudiantes | `/estudiantes` | ADMIN / PROFESOR | CRUD de estudiantes |
| 🧑‍🏫 Profesores | `/profesores` | ADMIN / PROFESOR | CRUD de profesores |
| 📚 Materias | `/materias` | ADMIN / PROFESOR | CRUD de materias |
| 🪜 Niveles y Grados | `/niveles` | ADMIN / PROFESOR | CRUD de niveles, grados y secciones |
| 🏫 Grupos | `/grupos` | ADMIN / PROFESOR | CRUD de grupos por grado/sección/año |
| 🕐 Horarios y Aulas | `/horarios` | ADMIN / PROFESOR | Aulas, bloques y asignaciones clase→profesor |
| 📝 Evaluaciones | `/evaluaciones` | ADMIN / PROFESOR | Tipos y evaluaciones por asignación/período |
| ✏️ Notas | `/notas` | ADMIN / PROFESOR | Captura de calificaciones — el profesor solo ve **sus clases** |
| ✅ Asistencia | `/asistencia` | ADMIN / PROFESOR | Registro de asistencia por clase y fecha |
| 🗓️ Años Escolares | `/anios-escolares` | ADMIN / PROFESOR | CRUD de años lectivos y períodos |
| 🧑‍🤝‍🧑 Representantes | `/representantes` | ADMIN / PROFESOR | CRUD de padres/encargados |
| 🎓 Mis Notas | `/mis-notas` | ESTUDIANTE | Portal del alumno: calificaciones del estudiante vinculado |
| 🔐 Usuarios | `/usuarios` | ADMIN | CRUD de cuentas + asignación de roles |
| ⚙️ Configuración | `/configuracion` | ADMIN | Datos de la institución y escala de notas |

## ⚙️ Cómo Funciona

### Arquitectura por página

```text
Página .astro
 ├── <Layout title active />        # Sidebar, header, guardas de sesión
 └── <script type="module">
      ├── import { apiGet } from '../lib/api'
      ├── import { createCrudPage } from '../lib/crud'   # CRUDs simples
      └── API → DOM (render con template literals)        # Vistas a medida
```

### Flujo de sesión

```text
POST /usuarios/login ──► { token, roles, username, idProfesor, idEstudiante }
        │                        │
        ▼                        ▼
  localStorage                Cada fetch adjunta:
  (escuela_session)          Authorization: Bearer <token>
                                   │
        401 (token expirado/inválido) ▼
        limpia sesión → redirige a /login
```

## 🔌 Cliente API y Sesión

### `src/lib/api.js` — cliente HTTP
- Base URL: `import.meta.env.PUBLIC_API_URL` (por defecto `http://localhost:8080/api/v1`).
- Exporta `apiGet`, `apiPost`, `apiPut`, `apiDelete` (JSON, con `Content-Type` automático).
- Lee el token de la sesión y añade `Authorization: Bearer …` cuando existe.
- Errores: lanza `Error(message)` con el `message`/`detail` del backend; si el servidor responde **401** limpia la sesión y redirige a `/login`.

### `src/lib/session.js` — sesión
- `getSession / setSession / clearSession` sobre la clave `escuela_session` en `localStorage`.

### `src/lib/crud.js` — factoría de CRUDs
`createCrudPage(config)` genera toda una página de administración con **una sola llamada**:
- Tabla con **esqueleto de carga** (skeleton) y estado de **error con botón "Reintentar"**
- **Buscador** cliente sobre las columnas `searchKeys`
- Modal de **crear / editar** (botón según endpoint) con campos por tipo: `text`, `number`, `date`, `email`, `tel`, `password`, `textarea`, `select` (con opciones estáticas o una función `async` que consulta otra API)
- Confirmación de **eliminar** y acciones extra por fila (`extraActions`)

Ejemplo de configuración (patrón usado por las páginas CRUD):

```js
createCrudPage({
  root: '#app',                       // contenedor
  endpoint: '/estudiantes',           // ruta dentro de /api/v1
  title: 'Estudiantes',
  columns: [ { key: 'nombres', label: 'Nombres', render: fullName }, … ],
  fields:  [ { name: 'idGrado', label: 'Grado', type: 'select', options: () => apiGet('/niveles/grados') }, … ],
  searchKeys: ['nombres', 'apellidos'],
});
```

### `src/lib/ui.js` — utilidades de UI
- `toast(message, type)` — notificaciones (success / error / info) con salida animada.
- `openModal({ title, body, submitLabel, onSubmit })` — modal reutilizable de formulario.
- `badge(value)` — insignias de color según estado (ACTIVO, AUSENTE, PENDIENTE, PAGADO…).
- `esc()`, `fmtDate()`, `initials()`, `fullName()` — escape HTML y formatos (fechas `es-SV`).

## 🎨 Diseño y Componentes

- **Paleta:** violeta (`brand-*`) + acento (`flare-*`) definidos como tokens de Tailwind en `global.css` (`@theme`), sobre degradados oscuros en la barra lateral y tarjetas claras en el contenido.
- **Componentes CSS** (clases utilitarias en `global.css`): `.card`, `.btn-primary`, `.btn-soft`, `.btn-ghost`, `.btn-icon(-danger)`, `.input`, `.label`, `.badge`, `.skeleton`, `.th`/`.td`, animaciones `fade-in` y `modal-anim`.
- **Layout (`Layout.astro`):**
  - Sidebar fija (responsive, deslizable en móvil) con **íconos SVG inline** y enlaces filtrados por rol (`data-nav-roles`).
  - Header con fecha del día, año lectivo, usuario, avatar iniciales y botón de **logout**.
  - Indicador en vivo del estado de la API (verde "API en línea" / rojo "API fuera de línea") consultando `/configuracion`.
- **Interacciones:** hover con elevación en tarjetas, transiciones en enlaces/botones, toasts deslizantes y modales con `backdrop-blur`.

## 🚀 Inicio Rápido

### Prerrequisitos
- Node.js **≥ 22.12** y **pnpm**
- Backend corriendo en `http://localhost:8080` (ver [README raíz](../README.md#-inicio-rápido))

### Instalar y ejecutar

```bash
cd front
pnpm install        # instala dependencias
pnpm dev            # servidor de desarrollo → http://localhost:4321
```

> 📝 La URL del backend se cambia con la variable `PUBLIC_API_URL` (crea un archivo `.env` en `front/`):
> ```env
> PUBLIC_API_URL=http://localhost:8080/api/v1
> ```
> `.env` está en `.gitignore`.

### Cuentas demo (las siembra el backend)

| Usuario | Contraseña | Rol | Experiencia |
|---------|-----------|-----|-------------|
| `admin` | `admin` | ADMIN | Todas las secciones, incl. Usuarios y Configuración |
| `profesor.demo` | `profesor123` | PROFESOR | Solo las **clases asignadas** en Notas/Asistencia |
| `estudiante.demo` | `estudiante123` | ESTUDIANTE | Redirigido a **Mis Notas** |

> ⚠️ Si una cuenta con el correo `admin@admin.com` ya existía en tu BD (p. ej. bajo otro username), la siembra **no** crea un `admin`: inicia sesión con esa cuenta existente (ver [README raíz](../README.md#-cuentas-demo)).

## 🛠️ Desarrollo

| Comando | Acción |
|---------|--------|
| `pnpm dev` | Servidor de desarrollo en `localhost:4321` |
| `pnpm build` | Build de producción a `./dist/` |
| `pnpm preview` | Previsualizar el build localmente |
| `pnpm astro check` | Chequeo de tipos/types del proyecto |

### Pautas para nuevas páginas CRUD

1. Crea `src/pages/<recurso>.astro` con `<Layout title="…" active="…">` y un `<div id="app">`.
2. En el `<script>`, importa `createCrudPage` desde `../lib/crud` y configura `endpoint`, `columns`, `fields` y `searchKeys` (los selects referencian catálogos por su función `options`).
3. Si el backend aún no expone un listado/CRUD para la tabla, agrégalo primero (ver cómo se añadió `GET /niveles/grados`).
4. Verifica el rol del enlace en el array `nav` de `layouts/Layout.astro`.

### Vistas a medida (no CRUD)

Dashboard (`index`), notas, asistencia, portal del alumno, usuarios y configuración tienen scripts propios sobre `api.js` + `ui.js`. Mantén ahí los componentes compartidos (toasts, modales, badges) en lugar de duplicarlos.

---

<div align="center">

**Frontend del Sistema Escolar — Astro + Tailwind CSS · [⬆ Volver arriba](#-sistema-escolar--frontend)**

</div>
