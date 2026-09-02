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
    private final CreateGradoCase createGradoCase;
    private final GetGradosByNivelCase getGradosByNivelCase;
    private final CreateSeccionCase createSeccionCase;
    private final GetAllSeccionesCase getAllSeccionesCase;

    public NivelesController(CreateNivelCase createNivelCase, GetAllNivelesCase getAllNivelesCase,
                             CreateGradoCase createGradoCase, GetGradosByNivelCase getGradosByNivelCase,
                             CreateSeccionCase createSeccionCase, GetAllSeccionesCase getAllSeccionesCase) {
        this.createNivelCase = createNivelCase;
        this.getAllNivelesCase = getAllNivelesCase;
        this.createGradoCase = createGradoCase;
        this.getGradosByNivelCase = getGradosByNivelCase;
        this.createSeccionCase = createSeccionCase;
        this.getAllSeccionesCase = getAllSeccionesCase;
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

    @Operation(summary = "Crear grado")
    @PostMapping("/grados")
    public GradoResponse createGrado(@RequestBody GradoRequest request) {
        return toGradoResponse(createGradoCase.create(Grado.nuevo(new Id(request.idNivel()), request.nombreGrado(), request.descripcion())));
    }

    @Operation(summary = "Obtener grados por nivel")
    @GetMapping("/{idNivel}/grados")
    public List<GradoResponse> getGradosByNivel(@PathVariable Long idNivel) {
        return getGradosByNivelCase.getByNivel(new Id(idNivel)).stream().map(this::toGradoResponse).collect(Collectors.toList());
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
