package com.aviles.api.escuela.estudiantes.infra.adapter.in.web;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.estudiantes.application.port.in.*;
import com.aviles.api.escuela.estudiantes.domain.*;
import com.aviles.api.escuela.estudiantes.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Gestión de estudiantes del sistema escolar")
public class EstudianteController {

    private final CreateEstudianteCase createEstudianteCase;
    private final GetAllEstudiantesCase getAllEstudiantesCase;
    private final GetEstudianteByIdCase getEstudianteByIdCase;
    private final CreateMatriculaCase createMatriculaCase;
    private final GetMatriculasByGrupoCase getMatriculasByGrupoCase;

    public EstudianteController(CreateEstudianteCase createEstudianteCase, GetAllEstudiantesCase getAllEstudiantesCase,
                                 GetEstudianteByIdCase getEstudianteByIdCase, CreateMatriculaCase createMatriculaCase,
                                 GetMatriculasByGrupoCase getMatriculasByGrupoCase) {
        this.createEstudianteCase = createEstudianteCase;
        this.getAllEstudiantesCase = getAllEstudiantesCase;
        this.getEstudianteByIdCase = getEstudianteByIdCase;
        this.createMatriculaCase = createMatriculaCase;
        this.getMatriculasByGrupoCase = getMatriculasByGrupoCase;
    }

    @Operation(summary = "Crear estudiante")
    @PostMapping
    public EstudianteResponse create(@RequestBody EstudianteRequest request) {
        Estudiante estudiante = Estudiante.nuevo(request.codigoEstudiante(), request.nombres(), request.apellidos(),
                request.fechaNacimiento() != null ? LocalDate.parse(request.fechaNacimiento()) : null, request.genero());
        return toResponse(createEstudianteCase.create(estudiante));
    }

    @Operation(summary = "Obtener todos los estudiantes")
    @GetMapping
    public List<EstudianteResponse> getAll() {
        return getAllEstudiantesCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Obtener estudiante por ID")
    @GetMapping("/{id}")
    public EstudianteResponse getById(@PathVariable Long id) {
        return getEstudianteByIdCase.getById(new Id(id)).map(this::toResponse).orElse(null);
    }

    @Operation(summary = "Crear matrícula")
    @PostMapping("/matriculas")
    public MatriculaResponse createMatricula(@RequestBody MatriculaRequest request) {
        Matricula matricula = Matricula.nueva(new Id(request.idEstudiante()), new Id(request.idGrupo()), request.tipoMatricula());
        return toMatriculaResponse(createMatriculaCase.create(matricula));
    }

    @Operation(summary = "Obtener matrículas por grupo")
    @GetMapping("/matriculas/grupo/{idGrupo}")
    public List<MatriculaResponse> getMatriculasByGrupo(@PathVariable Long idGrupo) {
        return getMatriculasByGrupoCase.getByGrupo(new Id(idGrupo)).stream()
                .map(this::toMatriculaResponse).collect(Collectors.toList());
    }

    private EstudianteResponse toResponse(Estudiante e) {
        return new EstudianteResponse(e.id().getValue(), e.codigoEstudiante(), e.nombres(), e.apellidos(),
                e.fechaNacimiento() != null ? e.fechaNacimiento().toString() : null, e.genero(), e.nacionalidad(),
                e.dui(), e.nie(), e.correoElectronico(), e.telefono(), e.direccion(),
                e.fechaIngreso() != null ? e.fechaIngreso().toString() : null, e.estado(), e.fotoUrl());
    }

    private MatriculaResponse toMatriculaResponse(Matricula m) {
        return new MatriculaResponse(m.id().getValue(), m.idEstudiante().getValue(), m.idGrupo().getValue(),
                m.fechaMatricula().toString(), m.tipoMatricula(), m.estado(), m.observaciones());
    }
}
