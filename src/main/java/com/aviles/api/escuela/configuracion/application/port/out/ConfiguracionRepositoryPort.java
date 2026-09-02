package com.aviles.api.escuela.configuracion.application.port.out;

import com.aviles.api.escuela.configuracion.domain.Configuracion;

/**
 * Puerto de salida para la persistencia de la configuración del sistema.
 */
public interface ConfiguracionRepositoryPort {
    Configuracion find();
    Configuracion save(Configuracion configuracion);
}
