package com.aviles.api.escuela.representantes.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.representantes.application.port.in.*;
import com.aviles.api.escuela.representantes.domain.Representante;
import com.aviles.api.escuela.representantes.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/representantes")
@Tag(name = "Representantes", description = "Gestión de representantes de estudiantes")
public class RepresentanteController {
    private final CreateRepresentanteCase createRepresentanteCase;
    private final GetAllRepresentantesCase getAllRepresentantesCase;
    private final UpdateRepresentanteCase updateRepresentanteCase;
    private final DeleteRepresentanteCase deleteRepresentanteCase;

    public RepresentanteController(CreateRepresentanteCase createRepresentanteCase, GetAllRepresentantesCase getAllRepresentantesCase,
                                   UpdateRepresentanteCase updateRepresentanteCase, DeleteRepresentanteCase deleteRepresentanteCase) {
        this.createRepresentanteCase = createRepresentanteCase;
        this.getAllRepresentantesCase = getAllRepresentantesCase;
        this.updateRepresentanteCase = updateRepresentanteCase;
        this.deleteRepresentanteCase = deleteRepresentanteCase;
    }

    @Operation(summary = "Crear representante")
    @PostMapping
    public RepresentanteResponse create(@RequestBody RepresentanteRequest request) {
        return toResponse(createRepresentanteCase.create(
                Representante.nuevo(request.nombres(), request.apellidos(), request.dui(), request.correoElectronico(), request.telefono())));
    }

    @Operation(summary = "Obtener todos los representantes")
    @GetMapping
    public List<RepresentanteResponse> getAll() {
        return getAllRepresentantesCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar representante")
    @PutMapping("/{id}")
    public RepresentanteResponse update(@PathVariable Long id, @RequestBody RepresentanteRequest request) {
        Representante representante = new Representante(new Id(id), request.nombres(), request.apellidos(),
                request.dui(), request.correoElectronico(), request.telefono(), null, null, null, null);
        return toResponse(updateRepresentanteCase.update(representante));
    }

    @Operation(summary = "Eliminar representante")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteRepresentanteCase.delete(new Id(id));
    }

    private RepresentanteResponse toResponse(Representante r) {
        return new RepresentanteResponse(r.id().getValue(), r.nombres(), r.apellidos(), r.dui(), r.correoElectronico(), r.telefono(), r.estado());
    }
}