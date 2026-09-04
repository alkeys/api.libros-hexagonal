package com.aviles.api.escuela.evaluaciones.application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.aviles.api.escuela.evaluaciones.application.port.in.*;
import com.aviles.api.escuela.evaluaciones.application.port.out.*;
import com.aviles.api.escuela.evaluaciones.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import com.aviles.api.escuela.auth.application.AuthContext;
import com.aviles.api.escuela.auth.domain.AuthUser;
import com.aviles.api.escuela.horarios.application.port.out.AsignacionClaseRepositoryPort;
import com.aviles.api.escuela.horarios.domain.AsignacionClase;
import com.aviles.api.escuela.estudiantes.application.port.out.MatriculaRepositoryPort;

/**
 * Servicio que implementa los casos de uso del módulo de evaluaciones.
 * Maneja la lógica de negocio para evaluaciones, calificaciones y notas finales.
 */
@Service
public class EvaluacionService implements CreateEvaluacionCase, GetAllEvaluacionesCase,
        GetCalificacionesByEvaluacionCase, CreateNotaFinalCase, UpsertCalificacionCase,
        UpdateEvaluacionCase, DeleteEvaluacionCase, DeleteCalificacionCase, GetNotasEstudianteCase {

    private final EvaluacionRepositoryPort evaluacionRepositoryPort;
    private final CalificacionRepositoryPort calificacionRepositoryPort;
    private final NotaFinalRepositoryPort notaFinalRepositoryPort;
    private final AsignacionClaseRepositoryPort asignacionClaseRepositoryPort;
    private final MatriculaRepositoryPort matriculaRepositoryPort;

    public EvaluacionService(EvaluacionRepositoryPort evaluacionRepositoryPort, CalificacionRepositoryPort calificacionRepositoryPort,
                              NotaFinalRepositoryPort notaFinalRepositoryPort,
                              AsignacionClaseRepositoryPort asignacionClaseRepositoryPort,
                              MatriculaRepositoryPort matriculaRepositoryPort) {
        this.evaluacionRepositoryPort = evaluacionRepositoryPort;
        this.calificacionRepositoryPort = calificacionRepositoryPort;
        this.notaFinalRepositoryPort = notaFinalRepositoryPort;
        this.asignacionClaseRepositoryPort = asignacionClaseRepositoryPort;
        this.matriculaRepositoryPort = matriculaRepositoryPort;
    }

    @Override
    public Evaluacion create(Evaluacion evaluacion) { return evaluacionRepositoryPort.save(evaluacion); }

    @Override
    public List<Evaluacion> getAll() {
        List<Evaluacion> todas = evaluacionRepositoryPort.findAllEvaluaciones();
        // Un PROFESOR solo ve las evaluaciones de las clases que tiene asignadas
        if (!esProfesorRestringido()) return todas;
        Set<Long> misAsignaciones = asignacionesDeMiProfesor();
        return todas.stream()
                .filter(e -> misAsignaciones.contains(e.idAsignacion().getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Calificacion> getByEvaluacion(Id idEvaluacion) {
        verificarAccesoEvaluacion(idEvaluacion);
        return calificacionRepositoryPort.findByEvaluacion(idEvaluacion);
    }

    @Override
    public List<NotaEstudiante> getNotasByEstudiante(Id idEstudiante) {
        List<Calificacion> calificaciones = calificacionRepositoryPort.findByEstudiante(idEstudiante);
        if (calificaciones.isEmpty()) return List.of();

        Map<Long, Evaluacion> evaluacionesPorId = evaluacionRepositoryPort.findAllEvaluaciones().stream()
                .collect(Collectors.toMap(e -> e.id().getValue(), e -> e));

        // Un PROFESOR solo puede consultar las notas de estudiantes en sus propias clases
        Set<Long> misAsignaciones = esProfesorRestringido() ? asignacionesDeMiProfesor() : null;

        return calificaciones.stream()
                .filter(c -> misAsignaciones == null
                        || (evaluacionesPorId.containsKey(c.idEvaluacion().getValue())
                            && misAsignaciones.contains(evaluacionesPorId.get(c.idEvaluacion().getValue()).idAsignacion().getValue())))
                .map(c -> {
                    Evaluacion e = evaluacionesPorId.get(c.idEvaluacion().getValue());
                    if (e == null) return null;
                    return new NotaEstudiante(
                            c.id(), c.idEvaluacion(), e.nombre(), e.descripcion(), e.fechaEvaluacion(),
                            e.porcentaje(), e.notaMaxima(), e.idAsignacion(), e.idPeriodo(),
                            c.notaObtenida(), c.observacion(), e.estado(), c.fechaRegistro());
                })
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Calificacion upsert(Calificacion calificacion) {
        AsignacionClase asignacion = verificarAccesoEvaluacion(calificacion.idEvaluacion());
        if (asignacion != null) {
            verificarEstudianteMatriculadoEnLaClase(asignacion.idGrupo(), calificacion.idEstudiante());
        }
        Optional<Calificacion> existing = calificacionRepositoryPort
                .findByEvaluacionAndEstudiante(calificacion.idEvaluacion(), calificacion.idEstudiante());
        if (existing.isPresent()) {
            Calificacion actualizada = new Calificacion(
                existing.get().id(),
                calificacion.idEvaluacion(),
                calificacion.idEstudiante(),
                calificacion.notaObtenida(),
                calificacion.observacion(),
                existing.get().fechaRegistro()
            );
            return calificacionRepositoryPort.save(actualizada);
        }
        return calificacionRepositoryPort.save(calificacion);
    }

    @Override
    public NotaFinal create(NotaFinal notaFinal) { return notaFinalRepositoryPort.save(notaFinal); }

    @Override
    public Evaluacion update(Evaluacion evaluacion) {
        Evaluacion existente = evaluacionRepositoryPort.findEvaluacionById(evaluacion.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluación no encontrada"));
        Evaluacion actualizada = new Evaluacion(existente.id(), evaluacion.idAsignacion(), evaluacion.idPeriodo(),
                evaluacion.idTipoEvaluacion(), evaluacion.nombre(), evaluacion.descripcion(),
                evaluacion.fechaEvaluacion(), evaluacion.porcentaje(), evaluacion.notaMaxima(), existente.estado());
        return evaluacionRepositoryPort.save(actualizada);
    }

    @Override
    public void delete(Id id) {
        if (evaluacionRepositoryPort.findEvaluacionById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluación no encontrada");
        }
        evaluacionRepositoryPort.deleteEvaluacionById(id.getValue());
    }

    @Override
    public void deleteCalificacion(Id id) {
        Calificacion calificacion = calificacionRepositoryPort.findCalificacionById(id.getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificación no encontrada"));
        verificarAccesoEvaluacion(calificacion.idEvaluacion());
        calificacionRepositoryPort.deleteCalificacionById(id.getValue());
    }

    // ---------- Autorización por clase (PROFESOR) ----------

    /**
     * Verdadero si el usuario actual es PROFESOR sin rol ADMIN (los ADMIN siempre pasan).
     */
    private boolean esProfesorRestringido() {
        AuthUser user = AuthContext.get();
        return user != null && !user.hasRole("ADMIN") && user.hasRole("PROFESOR");
    }

    private Long idProfesorActual() {
        AuthUser user = AuthContext.get();
        return user == null ? null : user.idProfesor();
    }

    /**
     * Ids de las asignaciones que pertenecen al profesor autenticado.
     */
    private Set<Long> asignacionesDeMiProfesor() {
        Long idProfesor = idProfesorActual();
        if (idProfesor == null) return Set.of();
        return asignacionClaseRepositoryPort.findAllAsignaciones().stream()
                .filter(a -> a.idProfesor() != null && a.idProfesor().getValue().equals(idProfesor))
                .map(a -> a.id().getValue())
                .collect(Collectors.toSet());
    }

    /**
     * Si el usuario es PROFESOR (no ADMIN), solo puede operar sobre evaluaciones
     * cuyas asignaciones le pertenecen. Lanza 403 en caso contrario.
     * Devuelve la asignación de la evaluación cuando el profesor está autorizado
     * (o null si no aplica restricción).
     */
    private AsignacionClase verificarAccesoEvaluacion(Id idEvaluacion) {
        if (!esProfesorRestringido()) return null;
        Evaluacion evaluacion = evaluacionRepositoryPort.findEvaluacionById(idEvaluacion.getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluación no encontrada"));
        AsignacionClase asignacion = asignacionClaseRepositoryPort
                .findAsignacionById(evaluacion.idAsignacion().getValue())
                .orElse(null);
        Long idProfesor = idProfesorActual();
        boolean esMia = asignacion != null && idProfesor != null
                && asignacion.idProfesor() != null
                && asignacion.idProfesor().getValue().equals(idProfesor);
        if (!esMia) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Solo puedes calificar estudiantes de tus propias clases");
        }
        return asignacion;
    }

    /**
     * Verifica que el estudiante esté matriculado en el grupo de la clase
     * (solo se invoca cuando el profesor ya está autorizado sobre la evaluación).
     */
    private void verificarEstudianteMatriculadoEnLaClase(Id idGrupo, Id idEstudiante) {
        boolean matriculado = matriculaRepositoryPort.findByGrupo(idGrupo).stream()
                .anyMatch(m -> m.idEstudiante().getValue().equals(idEstudiante.getValue()));
        if (!matriculado) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "El estudiante no está matriculado en esta clase");
        }
    }
}
