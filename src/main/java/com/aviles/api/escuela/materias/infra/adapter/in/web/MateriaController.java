package com.aviles.api.escuela.materias.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.materias.application.port.in.*;
import com.aviles.api.escuela.materias.domain.Materia;
import com.aviles.api.escuela.materias.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/materias")
@Tag(name = "Materias", description = "Gestión de materias o asignaturas")
public class MateriaController {

    private final CreateMateriaCase createMateriaCase;
    private final GetAllMateriasCase getAllMateriasCase;
    private final UpdateMateriaCase updateMateriaCase;
    private final DeleteMateriaCase deleteMateriaCase;

    public MateriaController(CreateMateriaCase createMateriaCase, GetAllMateriasCase getAllMateriasCase,
                             UpdateMateriaCase updateMateriaCase, DeleteMateriaCase deleteMateriaCase) {
        this.createMateriaCase = createMateriaCase;
        this.getAllMateriasCase = getAllMateriasCase;
        this.updateMateriaCase = updateMateriaCase;
        this.deleteMateriaCase = deleteMateriaCase;
    }

    @Operation(summary = "Crear materia")
    @PostMapping
    public MateriaResponse create(@RequestBody MateriaRequest request) {
        Materia materia = Materia.nueva(request.codigoMateria(), request.nombreMateria(), request.descripcion(),
                request.horasSemanales(), request.tipo());
        return toResponse(createMateriaCase.create(materia));
    }

    @Operation(summary = "Obtener todas las materias")
    @GetMapping
    public List<MateriaResponse> getAll() {
        return getAllMateriasCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar materia")
    @PutMapping("/{id}")
    public MateriaResponse update(@PathVariable Long id, @RequestBody MateriaRequest request) {
        Materia materia = new Materia(new Id(id), request.codigoMateria(), request.nombreMateria(), request.descripcion(),
                request.horasSemanales(), request.tipo(), null);
        return toResponse(updateMateriaCase.update(materia));
    }

    @Operation(summary = "Eliminar materia")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteMateriaCase.delete(new Id(id));
    }

    private MateriaResponse toResponse(Materia m) {
        return new MateriaResponse(m.id().getValue(), m.codigoMateria(), m.nombreMateria(), m.descripcion(),
                m.horasSemanales(), m.tipo(), m.estado());
    }
}