package com.aviles.api.escuela.horarios.infra.adapter.in.web;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.horarios.application.port.in.*;
import com.aviles.api.escuela.horarios.domain.*;
import com.aviles.api.escuela.horarios.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/horarios")
@Tag(name = "Horarios y Aulas", description = "Gestión de aulas, horarios y asignaciones de clase")
public class HorarioController {

    private final CreateAulaCase createAulaCase;
    private final GetAllAulasCase getAllAulasCase;
    private final CreateBloqueHorarioCase createBloqueCase;
    private final GetAllBloquesHorarioCase getAllBloquesCase;
    private final CreateAsignacionClaseCase createAsignacionCase;
    private final GetAllAsignacionesCase getAllAsignacionesCase;

    public HorarioController(CreateAulaCase createAulaCase, GetAllAulasCase getAllAulasCase,
                              CreateBloqueHorarioCase createBloqueCase, GetAllBloquesHorarioCase getAllBloquesCase,
                              CreateAsignacionClaseCase createAsignacionCase, GetAllAsignacionesCase getAllAsignacionesCase) {
        this.createAulaCase = createAulaCase;
        this.getAllAulasCase = getAllAulasCase;
        this.createBloqueCase = createBloqueCase;
        this.getAllBloquesCase = getAllBloquesCase;
        this.createAsignacionCase = createAsignacionCase;
        this.getAllAsignacionesCase = getAllAsignacionesCase;
    }

    @Operation(summary = "Crear aula")
    @PostMapping("/aulas")
    public AulaResponse createAula(@RequestBody AulaRequest request) {
        return toAulaResponse(createAulaCase.create(Aula.nueva(request.codigo(), request.nombre(), request.edificio(), request.piso(), request.capacidad(), request.tipo())));
    }

    @Operation(summary = "Obtener todas las aulas")
    @GetMapping("/aulas")
    public List<AulaResponse> getAllAulas() {
        return getAllAulasCase.getAllAulas().stream().map(this::toAulaResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Crear bloque horario")
    @PostMapping("/bloques")
    public BloqueHorarioResponse createBloque(@RequestBody BloqueHorarioRequest request) {
        return toBloqueResponse(createBloqueCase.create(BloqueHorario.nuevo(request.diaSemana(), LocalTime.parse(request.horaInicio()), LocalTime.parse(request.horaFin()))));
    }

    @Operation(summary = "Obtener todos los bloques horarios")
    @GetMapping("/bloques")
    public List<BloqueHorarioResponse> getAllBloques() {
        return getAllBloquesCase.getAllBloques().stream().map(this::toBloqueResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Crear asignación de clase")
    @PostMapping("/asignaciones")
    public AsignacionClaseResponse createAsignacion(@RequestBody AsignacionClaseRequest request) {
        AsignacionClase a = AsignacionClase.nueva(new Id(request.idGrupo()), new Id(request.idMateria()),
                new Id(request.idProfesor()), new Id(request.idHorario()), new Id(request.idAula()), request.modalidad());
        return toAsignacionResponse(createAsignacionCase.create(a));
    }

    @Operation(summary = "Obtener todas las asignaciones de clase")
    @GetMapping("/asignaciones")
    public List<AsignacionClaseResponse> getAllAsignaciones() {
        return getAllAsignacionesCase.getAllAsignaciones().stream().map(this::toAsignacionResponse).collect(Collectors.toList());
    }

    private AulaResponse toAulaResponse(Aula a) {
        return new AulaResponse(a.id().getValue(), a.codigo(), a.nombre(), a.edificio(), a.piso(), a.capacidad(), a.tipo(), a.estado());
    }

    private BloqueHorarioResponse toBloqueResponse(BloqueHorario b) {
        return new BloqueHorarioResponse(b.id().getValue(), b.diaSemana(), b.horaInicio().toString(), b.horaFin().toString());
    }

    private AsignacionClaseResponse toAsignacionResponse(AsignacionClase a) {
        return new AsignacionClaseResponse(a.id().getValue(), a.idGrupo().getValue(), a.idMateria().getValue(),
                a.idProfesor().getValue(), a.idHorario().getValue(), a.idAula().getValue(), a.modalidad(), a.estado(), a.observaciones());
    }
}
