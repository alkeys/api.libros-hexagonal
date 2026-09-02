package com.aviles.api.escuela.configuracion.infra.adapter.in.web;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.configuracion.application.port.in.GetConfiguracionCase;
import com.aviles.api.escuela.configuracion.application.port.in.UpdateConfiguracionCase;
import com.aviles.api.escuela.configuracion.domain.Configuracion;
import com.aviles.api.escuela.configuracion.infra.adapter.in.web.dto.ConfiguracionRequest;
import com.aviles.api.escuela.configuracion.infra.adapter.in.web.dto.ConfiguracionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para la gestión de la configuración del sistema escolar.
 * Expone los endpoints para consultar y actualizar la configuración institucional.
 */
@RestController
@RequestMapping("/api/v1/configuracion")
@Tag(name = "Configuración", description = "Gestión de la configuración del sistema escolar")
public class ConfiguracionController {

    private final GetConfiguracionCase getConfiguracionCase;
    private final UpdateConfiguracionCase updateConfiguracionCase;

    public ConfiguracionController(GetConfiguracionCase getConfiguracionCase,
                                    UpdateConfiguracionCase updateConfiguracionCase) {
        this.getConfiguracionCase = getConfiguracionCase;
        this.updateConfiguracionCase = updateConfiguracionCase;
    }

    @Operation(summary = "Obtener configuración", description = "Obtiene la configuración actual del sistema escolar")
    @GetMapping
    public ConfiguracionResponse getConfiguracion() {
        Configuracion config = getConfiguracionCase.getConfiguracion();
        return toResponse(config);
    }

    @Operation(summary = "Actualizar configuración", description = "Actualiza la configuración del sistema escolar")
    @PutMapping
    public ConfiguracionResponse updateConfiguracion(@RequestBody ConfiguracionRequest request) {
        Configuracion config = toDomain(request);
        Configuracion updated = updateConfiguracionCase.updateConfiguracion(config);
        return toResponse(updated);
    }

    private Configuracion toDomain(ConfiguracionRequest request) {
        return new Configuracion(
            null,
            request.nombreInstitucion(),
            request.direccion(),
            request.telefono(),
            request.correo(),
            request.sitioWeb(),
            request.logoUrl(),
            request.escalaMinima(),
            request.escalaMaxima(),
            request.notaAprobacion(),
            request.moneda(),
            request.zonaHoraria(),
            null
        );
    }

    private ConfiguracionResponse toResponse(Configuracion config) {
        return new ConfiguracionResponse(
            config.id() != null ? config.id().getValue() : null,
            config.nombreInstitucion(),
            config.direccion(),
            config.telefono(),
            config.correo(),
            config.sitioWeb(),
            config.logoUrl(),
            config.escalaMinima(),
            config.escalaMaxima(),
            config.notaAprobacion(),
            config.moneda(),
            config.zonaHoraria()
        );
    }
}
