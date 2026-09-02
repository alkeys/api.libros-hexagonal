package com.aviles.api.escuela.configuracion.application.service;

import org.springframework.stereotype.Service;

import com.aviles.api.escuela.configuracion.application.port.in.GetConfiguracionCase;
import com.aviles.api.escuela.configuracion.application.port.in.UpdateConfiguracionCase;
import com.aviles.api.escuela.configuracion.application.port.out.ConfiguracionRepositoryPort;
import com.aviles.api.escuela.configuracion.domain.Configuracion;

/**
 * Servicio que implementa los casos de uso de configuración del sistema escolar.
 * Maneja la lógica de negocio para consultar y actualizar la configuración institucional.
 */
@Service
public class ConfiguracionService implements GetConfiguracionCase, UpdateConfiguracionCase {

    private final ConfiguracionRepositoryPort configuracionRepositoryPort;

    public ConfiguracionService(ConfiguracionRepositoryPort configuracionRepositoryPort) {
        this.configuracionRepositoryPort = configuracionRepositoryPort;
    }

    @Override
    public Configuracion getConfiguracion() {
        return configuracionRepositoryPort.find();
    }

    @Override
    public Configuracion updateConfiguracion(Configuracion configuracion) {
        return configuracionRepositoryPort.save(configuracion);
    }
}
