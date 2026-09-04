package com.aviles.api.escuela.auth.infra;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aviles.api.escuela.auth.application.AuthContext;
import com.aviles.api.escuela.auth.domain.AuthUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor de autorización por roles sobre los endpoints del API.
 *
 * <p>Reglas:
 * <ul>
 *   <li>Público: POST /api/v1/usuarios/login y Swagger.</li>
 *   <li>Toda ruta /api/v1/** exige token JWT válido (401).</li>
 *   <li>ADMIN: acceso total.</li>
 *   <li>PROFESOR: lectura total (incluye matrículas para capturar notas);
 *       escritura solo en calificaciones y asistencia.</li>
 *   <li>ESTUDIANTE: solo lectura de catálogos y sus propias notas.</li>
 *   <li>Usuarios y escritura de configuración: solo ADMIN.</li>
 * </ul>
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // Escrituras de admin (POST/PUT/DELETE) en estos módulos no las cubre PROFESOR_WRITE
    private static final String[] MODULE_PREFIXES = {
            "/api/v1/estudiantes", "/api/v1/profesores", "/api/v1/materias",
            "/api/v1/niveles", "/api/v1/grupos", "/api/v1/horarios",
            "/api/v1/anios-escolares", "/api/v1/representantes",
    };

    // Prefijos de escritura permitidos al PROFESOR
    private static final String[] PROFESOR_WRITE_PREFIXES = {
            "/api/v1/evaluaciones/calificaciones",
            "/api/v1/asistencia",
    };

    // Catálogos que un ESTUDIANTE puede leer (para nombres en su portal)
    private static final String[] ESTUDIANTE_READ_PREFIXES = {
            "/api/v1/grupos", "/api/v1/materias", "/api/v1/niveles",
            "/api/v1/horarios", "/api/v1/anios-escolares", "/api/v1/profesores",
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true; // preflight CORS
        }

        String path = request.getRequestURI();
        String method = request.getMethod();

        if (isPublic(path, method)) {
            return true;
        }
        if (!path.startsWith("/api/v1")) {
            return true; // estáticos u otros recursos
        }

        AuthUser user = AuthContext.get();
        if (user == null) {
            return writeError(response, 401, "No autenticado: se requiere un token JWT válido");
        }

        boolean isWrite = !HttpMethod.GET.matches(method);

        // ADMIN: acceso total
        if (user.hasRole("ADMIN")) {
            return true;
        }

        // Usuarios: solo ADMIN (login ya es público y se resuelve antes)
        if (path.startsWith("/api/v1/usuarios")) {
            return writeError(response, 403, "Se requiere rol ADMIN para esta acción");
        }

        // Configuración: lectura permitida a autenticados; escritura solo ADMIN
        if (path.startsWith("/api/v1/configuracion")) {
            if (isWrite) {
                return writeError(response, 403, "Se requiere rol ADMIN para esta acción");
            }
            return true;
        }

        // PROFESOR: lee todo; escribe solo calificaciones y asistencia
        if (user.hasRole("PROFESOR")) {
            if (!isWrite) {
                return true;
            }
            for (String prefix : PROFESOR_WRITE_PREFIXES) {
                if (path.startsWith(prefix)) {
                    return true;
                }
            }
            return writeError(response, 403, "Se requiere rol ADMIN para esta acción");
        }

        // Resto (ESTUDIANTE u otros): sin escrituras
        if (isWrite) {
            return writeError(response, 403, "No tienes permiso para esta acción");
        }

        // ESTUDIANTE (o cualquier rol sin regla específica): portal de notas propio + catálogos
        String misNotas = user.idEstudiante() != null
                ? "/api/v1/evaluaciones/estudiante/" + user.idEstudiante() + "/calificaciones"
                : null;
        if (misNotas != null && path.equals(misNotas)) {
            return true;
        }
        for (String prefix : ESTUDIANTE_READ_PREFIXES) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }
        // Configuración en modo lectura: permitido a cualquier autenticado (dashboard del Layout)
        if (path.startsWith("/api/v1/configuracion")) {
            return true;
        }
        return writeError(response, 403, "No tienes permiso para esta acción");
    }

    private boolean isPublic(String path, String method) {
        if (HttpMethod.POST.matches(method) && path.endsWith("/api/v1/usuarios/login")) {
            return true;
        }
        return path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-resources")
                || path.equals("/swagger-ui.html")
                || path.equals("/favicon.ico");
    }

    private boolean writeError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"message\":\"" + message.replace("\"", "\\\"") + "\"}");
        return false;
    }
}