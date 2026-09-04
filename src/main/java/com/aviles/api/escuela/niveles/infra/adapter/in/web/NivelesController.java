package com.aviles.api.escuela.niveles.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.niveles.application.port.in.*;
import com.aviles.api.escuela.niveles.domain.*;
import com.aviles.api.escuela.niveles.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/niveles")
@Tag(name = "Niveles Educativos", description = "Gestión de niveles, grados y secciones")
public class NivelesController {

    private final CreateNivelCase createNivelCase;
    private final GetAllNivelesCase getAllNivelesCase;
    private final UpdateNivelCase updateNivelCase;
    private final DeleteNivelCase deleteNivelCase;
    private final CreateGradoCase createGradoCase;
    private final GetGradosByNivelCase getGradosByNivelCase;
    private final GetAllGradosCase getAllGradosCase;
    private final UpdateGradoCase updateGradoCase;
    private final DeleteGradoCase deleteGradoCase;
    private final CreateSeccionCase createSeccionCase;
    private final GetAllSeccionesCase getAllSeccionesCase;
    private final UpdateSeccionCase updateSeccionCase;
    private final DeleteSeccionCase deleteSeccionCase;

    public NivelesController(CreateNivelCase createNivelCase, GetAllNivelesCase getAllNivelesCase,
                             UpdateNivelCase updateNivelCase, DeleteNivelCase deleteNivelCase,
                             CreateGradoCase createGradoCase, GetGradosByNivelCase getGradosByNivelCase,
                             GetAllGradosCase getAllGradosCase,
                             UpdateGradoCase updateGradoCase, DeleteGradoCase deleteGradoCase,
                             CreateSeccionCase createSeccionCase, GetAllSeccionesCase getAllSeccionesCase,
                             UpdateSeccionCase updateSeccionCase, DeleteSeccionCase deleteSeccionCase) {
        this.createNivelCase = createNivelCase;
        this.getAllNivelesCase = getAllNivelesCase;
        this.updateNivelCase = updateNivelCase;
        this.deleteNivelCase = deleteNivelCase;
        this.createGradoCase = createGradoCase;
        this.getGradosByNivelCase = getGradosByNivelCase;
        this.getAllGradosCase = getAllGradosCase;
        this.updateGradoCase = updateGradoCase;
        this.deleteGradoCase = deleteGradoCase;
        this.createSeccionCase = createSeccionCase;
        this.getAllSeccionesCase = getAllSeccionesCase;
        this.updateSeccionCase = updateSeccionCase;
        this.deleteSeccionCase = deleteSeccionCase;
    }

    @Operation(summary = "Crear nivel educativo")
    @PostMapping
    public NivelResponse createNivel(@RequestBody NivelRequest request) {
        return toNivelResponse(createNivelCase.create(NivelEducativo.nuevo(request.nombre(), request.descripcion())));
    }

    @Operation(summary = "Obtener todos los niveles educativos")
    @GetMapping
    public List<NivelResponse> getAllNiveles() {
        return getAllNivelesCase.getAll().stream().map(this::toNivelResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar nivel educativo")
    @PutMapping("/{id}")
    public NivelResponse updateNivel(@PathVariable Long id, @RequestBody NivelRequest request) {
        return toNivelResponse(updateNivelCase.update(new NivelEducativo(new Id(id), request.nombre(), request.descripcion())));
    }

    @Operation(summary = "Eliminar nivel educativo")
    @DeleteMapping("/{id}")
    public void deleteNivel(@PathVariable Long id) {
        deleteNivelCase.deleteNivel(new Id(id));
    }

    @Operation(summary = "Crear grado")
    @PostMapping("/grados")
    public GradoResponse createGrado(@RequestBody GradoRequest request) {
        return toGradoResponse(createGradoCase.create(Grado.nuevo(new Id(request.idNivel()), request.nombreGrado(), request.descripcion())));
    }

    @Operation(summary = "Obtener todos los grados")
    @GetMapping("/grados")
    public List<GradoResponse> getAllGrados() {
        return getAllGradosCase.getAllGrados().stream().map(this::toGradoResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Obtener grados por nivel")
    @GetMapping("/{idNivel}/grados")
    public List<GradoResponse> getGradosByNivel(@PathVariable Long idNivel) {
        return getGradosByNivelCase.getByNivel(new Id(idNivel)).stream().map(this::toGradoResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar grado")
    @PutMapping("/grados/{id}")
    public GradoResponse updateGrado(@PathVariable Long id, @RequestBody GradoRequest request) {
        return toGradoResponse(updateGradoCase.update(new Grado(new Id(id), new Id(request.idNivel()), request.nombreGrado(), request.descripcion())));
    }

    @Operation(summary = "Eliminar grado")
    @DeleteMapping("/grados/{id}")
    public void deleteGrado(@PathVariable Long id) {
        deleteGradoCase.deleteGrado(new Id(id));
    }

    @Operation(summary = "Crear sección")
    @PostMapping("/secciones")
    public SeccionResponse createSeccion(@RequestBody SeccionRequest request) {
        return toSeccionResponse(createSeccionCase.create(Seccion.nueva(request.nombre(), request.descripcion())));
    }

    @Operation(summary = "Obtener todas las secciones")
    @GetMapping("/secciones")
    public List<SeccionResponse> getAllSecciones() {
        return getAllSeccionesCase.getAllSecciones().stream().map(this::toSeccionResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar sección")
    @PutMapping("/secciones/{id}")
    public SeccionResponse updateSeccion(@PathVariable Long id, @RequestBody SeccionRequest request) {
        return toSeccionResponse(updateSeccionCase.update(new Seccion(new Id(id), request.nombre(), request.descripcion())));
    }

    @Operation(summary = "Eliminar sección")
    @DeleteMapping("/secciones/{id}")
    public void deleteSeccion(@PathVariable Long id) {
        deleteSeccionCase.deleteSeccion(new Id(id));
    }

    private NivelResponse toNivelResponse(NivelEducativo n) {
        return new NivelResponse(n.id().getValue(), n.nombre(), n.descripcion());
    }

    private GradoResponse toGradoResponse(Grado g) {
        return new GradoResponse(g.id().getValue(), g.idNivel().getValue(), g.nombreGrado(), g.descripcion());
    }

    private SeccionResponse toSeccionResponse(Seccion s) {
        return new SeccionResponse(s.id().getValue(), s.nombre(), s.descripcion());
    }
}