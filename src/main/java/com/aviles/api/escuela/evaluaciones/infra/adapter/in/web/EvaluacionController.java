package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.evaluaciones.application.port.in.*;
import com.aviles.api.escuela.evaluaciones.domain.*;
import com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(name = "Evaluaciones", description = "Gestión de evaluaciones, calificaciones y notas finales")
public class EvaluacionController {

    private final CreateEvaluacionCase createEvaluacionCase;
    private final GetAllEvaluacionesCase getAllEvaluacionesCase;
    private final UpdateEvaluacionCase updateEvaluacionCase;
    private final DeleteEvaluacionCase deleteEvaluacionCase;
    private final GetCalificacionesByEvaluacionCase getCalificacionesByEvaluacionCase;
    private final UpsertCalificacionCase upsertCalificacionCase;
    private final DeleteCalificacionCase deleteCalificacionCase;
    private final GetNotasEstudianteCase getNotasEstudianteCase;

    public EvaluacionController(CreateEvaluacionCase createEvaluacionCase, GetAllEvaluacionesCase getAllEvaluacionesCase,
                                 UpdateEvaluacionCase updateEvaluacionCase, DeleteEvaluacionCase deleteEvaluacionCase,
                                 GetCalificacionesByEvaluacionCase getCalificacionesByEvaluacionCase,
                                 UpsertCalificacionCase upsertCalificacionCase,
                                 DeleteCalificacionCase deleteCalificacionCase,
                                 GetNotasEstudianteCase getNotasEstudianteCase) {
        this.createEvaluacionCase = createEvaluacionCase;
        this.getAllEvaluacionesCase = getAllEvaluacionesCase;
        this.updateEvaluacionCase = updateEvaluacionCase;
        this.deleteEvaluacionCase = deleteEvaluacionCase;
        this.getCalificacionesByEvaluacionCase = getCalificacionesByEvaluacionCase;
        this.upsertCalificacionCase = upsertCalificacionCase;
        this.deleteCalificacionCase = deleteCalificacionCase;
        this.getNotasEstudianteCase = getNotasEstudianteCase;
    }

    @Operation(summary = "Crear evaluación")
    @PostMapping
    public EvaluacionResponse create(@RequestBody EvaluacionRequest request) {
        Evaluacion e = Evaluacion.nueva(new Id(request.idAsignacion()), new Id(request.idPeriodo()),
                new Id(request.idTipoEvaluacion()), request.nombre(), LocalDate.parse(request.fechaEvaluacion()),
                request.porcentaje(), request.notaMaxima());
        return toEvaluacionResponse(createEvaluacionCase.create(e));
    }

    @Operation(summary = "Obtener todas las evaluaciones")
    @GetMapping
    public List<EvaluacionResponse> getAll() {
        return getAllEvaluacionesCase.getAll().stream().map(this::toEvaluacionResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar evaluación")
    @PutMapping("/{id}")
    public EvaluacionResponse update(@PathVariable Long id, @RequestBody EvaluacionRequest request) {
        Evaluacion e = new Evaluacion(new Id(id), new Id(request.idAsignacion()), new Id(request.idPeriodo()),
                new Id(request.idTipoEvaluacion()), request.nombre(), request.descripcion(),
                LocalDate.parse(request.fechaEvaluacion()), request.porcentaje(), request.notaMaxima(), null);
        return toEvaluacionResponse(updateEvaluacionCase.update(e));
    }

    @Operation(summary = "Eliminar evaluación")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteEvaluacionCase.delete(new Id(id));
    }

    @Operation(summary = "Registrar o actualizar calificación (upsert)")
    @PostMapping("/calificaciones")
    public CalificacionResponse upsertCalificacion(@RequestBody CalificacionRequest request) {
        Calificacion c = Calificacion.nueva(new Id(request.idEvaluacion()), new Id(request.idEstudiante()),
                request.notaObtenida(), request.observacion());
        return toCalificacionResponse(upsertCalificacionCase.upsert(c));
    }

    @Operation(summary = "Obtener calificaciones por evaluación")
    @GetMapping("/{idEvaluacion}/calificaciones")
    public List<CalificacionResponse> getCalificaciones(@PathVariable Long idEvaluacion) {
        return getCalificacionesByEvaluacionCase.getByEvaluacion(new Id(idEvaluacion)).stream()
                .map(this::toCalificacionResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Notas de un estudiante (portal del alumno)")
    @GetMapping("/estudiante/{idEstudiante}/calificaciones")
    public List<NotaEstudianteResponse> getNotasEstudiante(@PathVariable Long idEstudiante) {
        return getNotasEstudianteCase.getNotasByEstudiante(new Id(idEstudiante)).stream()
                .map(this::toNotaEstudianteResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Eliminar calificación")
    @DeleteMapping("/calificaciones/{id}")
    public void deleteCalificacion(@PathVariable Long id) {
        deleteCalificacionCase.deleteCalificacion(new Id(id));
    }

    private EvaluacionResponse toEvaluacionResponse(Evaluacion e) {
        return new EvaluacionResponse(e.id().getValue(), e.idAsignacion().getValue(), e.idPeriodo().getValue(),
                e.idTipoEvaluacion().getValue(), e.nombre(), e.descripcion(), e.fechaEvaluacion().toString(),
                e.porcentaje(), e.notaMaxima(), e.estado());
    }

    private CalificacionResponse toCalificacionResponse(Calificacion c) {
        return new CalificacionResponse(c.id().getValue(), c.idEvaluacion().getValue(), c.idEstudiante().getValue(),
                c.notaObtenida(), c.observacion(), c.fechaRegistro().toString());
    }

    private NotaEstudianteResponse toNotaEstudianteResponse(NotaEstudiante n) {
        return new NotaEstudianteResponse(n.idCalificacion().getValue(), n.idEvaluacion().getValue(),
                n.nombreEvaluacion(), n.descripcionEvaluacion(),
                n.fechaEvaluacion() != null ? n.fechaEvaluacion().toString() : null,
                n.porcentaje(), n.notaMaxima(), n.idAsignacion().getValue(),
                n.notaObtenida(), n.observacion(), n.estadoEvaluacion(),
                n.fechaRegistro() != null ? n.fechaRegistro().toString() : null);
    }
}