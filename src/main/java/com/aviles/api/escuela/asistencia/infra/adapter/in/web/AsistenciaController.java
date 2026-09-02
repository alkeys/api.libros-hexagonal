package com.aviles.api.escuela.asistencia.infra.adapter.in.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.asistencia.application.port.in.*;
import com.aviles.api.escuela.asistencia.domain.Asistencia;
import com.aviles.api.escuela.asistencia.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/asistencia")
@Tag(name = "Asistencia", description = "Gestión de asistencia de estudiantes")
public class AsistenciaController {

    private final CreateAsistenciaCase createAsistenciaCase;
    private final GetAsistenciaByAsignacionCase getAsistenciaByAsignacionCase;

    public AsistenciaController(CreateAsistenciaCase createAsistenciaCase, GetAsistenciaByAsignacionCase getAsistenciaByAsignacionCase) {
        this.createAsistenciaCase = createAsistenciaCase;
        this.getAsistenciaByAsignacionCase = getAsistenciaByAsignacionCase;
    }

    @Operation(summary = "Registrar asistencia")
    @PostMapping
    public AsistenciaResponse create(@RequestBody AsistenciaRequest request) {
        Asistencia a = Asistencia.nueva(new Id(request.idEstudiante()), new Id(request.idAsignacion()),
                LocalDate.parse(request.fecha()), request.estado());
        return toResponse(createAsistenciaCase.create(a));
    }

    @Operation(summary = "Obtener asistencia por asignación")
    @GetMapping("/asignacion/{idAsignacion}")
    public List<AsistenciaResponse> getByAsignacion(@PathVariable Long idAsignacion) {
        return getAsistenciaByAsignacionCase.getByAsignacion(new Id(idAsignacion)).stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    private AsistenciaResponse toResponse(Asistencia a) {
        return new AsistenciaResponse(a.id().getValue(), a.idEstudiante().getValue(), a.idAsignacion().getValue(),
                a.fecha().toString(), a.estado(), a.observacion());
    }
}
