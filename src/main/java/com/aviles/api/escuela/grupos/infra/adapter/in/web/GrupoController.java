package com.aviles.api.escuela.grupos.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.grupos.application.port.in.*;
import com.aviles.api.escuela.grupos.domain.Grupo;
import com.aviles.api.escuela.grupos.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/grupos")
@Tag(name = "Grupos", description = "Gestión de grupos escolares")
public class GrupoController {

    private final CreateGrupoCase createGrupoCase;
    private final GetAllGruposCase getAllGruposCase;
    private final UpdateGrupoCase updateGrupoCase;
    private final DeleteGrupoCase deleteGrupoCase;

    public GrupoController(CreateGrupoCase createGrupoCase, GetAllGruposCase getAllGruposCase,
                           UpdateGrupoCase updateGrupoCase, DeleteGrupoCase deleteGrupoCase) {
        this.createGrupoCase = createGrupoCase;
        this.getAllGruposCase = getAllGruposCase;
        this.updateGrupoCase = updateGrupoCase;
        this.deleteGrupoCase = deleteGrupoCase;
    }

    @Operation(summary = "Crear grupo escolar")
    @PostMapping
    public GrupoResponse create(@RequestBody GrupoRequest request) {
        Grupo grupo = Grupo.nuevo(new Id(request.idGrado()), new Id(request.idSeccion()),
                new Id(request.idAnioEscolar()), request.nombre(), request.capacidad(), request.turno());
        return toResponse(createGrupoCase.create(grupo));
    }

    @Operation(summary = "Obtener todos los grupos")
    @GetMapping
    public List<GrupoResponse> getAll() {
        return getAllGruposCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar grupo escolar")
    @PutMapping("/{id}")
    public GrupoResponse update(@PathVariable Long id, @RequestBody GrupoRequest request) {
        Grupo grupo = new Grupo(new Id(id), new Id(request.idGrado()), new Id(request.idSeccion()),
                new Id(request.idAnioEscolar()), request.nombre(), request.capacidad(), request.turno(), null);
        return toResponse(updateGrupoCase.update(grupo));
    }

    @Operation(summary = "Eliminar grupo escolar")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteGrupoCase.delete(new Id(id));
    }

    private GrupoResponse toResponse(Grupo g) {
        return new GrupoResponse(g.id().getValue(), g.idGrado().getValue(), g.idSeccion().getValue(),
                g.idAnioEscolar().getValue(), g.nombre(), g.capacidad(), g.turno(), g.estado());
    }
}