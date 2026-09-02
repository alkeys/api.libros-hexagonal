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
    private final CreateCalificacionCase createCalificacionCase;
    private final GetCalificacionesByEvaluacionCase getCalificacionesByEvaluacionCase;

    public EvaluacionController(CreateEvaluacionCase createEvaluacionCase, GetAllEvaluacionesCase getAllEvaluacionesCase,
                                 CreateCalificacionCase createCalificacionCase, GetCalificacionesByEvaluacionCase getCalificacionesByEvaluacionCase) {
        this.createEvaluacionCase = createEvaluacionCase;
        this.getAllEvaluacionesCase = getAllEvaluacionesCase;
        this.createCalificacionCase = createCalificacionCase;
        this.getCalificacionesByEvaluacionCase = getCalificacionesByEvaluacionCase;
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

    @Operation(summary = "Registrar calificación")
    @PostMapping("/calificaciones")
    public CalificacionResponse createCalificacion(@RequestBody CalificacionRequest request) {
        Calificacion c = Calificacion.nueva(new Id(request.idEvaluacion()), new Id(request.idEstudiante()),
                request.notaObtenida(), request.observacion());
        return toCalificacionResponse(createCalificacionCase.create(c));
    }

    @Operation(summary = "Obtener calificaciones por evaluación")
    @GetMapping("/{idEvaluacion}/calificaciones")
    public List<CalificacionResponse> getCalificaciones(@PathVariable Long idEvaluacion) {
        return getCalificacionesByEvaluacionCase.getByEvaluacion(new Id(idEvaluacion)).stream()
                .map(this::toCalificacionResponse).collect(Collectors.toList());
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
}
