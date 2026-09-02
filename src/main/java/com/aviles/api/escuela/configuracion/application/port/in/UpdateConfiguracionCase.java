package com.aviles.api.escuela.configuracion.application.port.in;

import com.aviles.api.escuela.configuracion.domain.Configuracion;

/**
 * Puerto de entrada para actualizar la configuración del sistema escolar.
 */
public interface UpdateConfiguracionCase {
    Configuracion updateConfiguracion(Configuracion configuracion);
}
