package com.aviles.api.escuela.anioescolar.application.port.in;

import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;

/**
 * Puerto de entrada para crear un año escolar.
 */
public interface CreateAnioEscolarCase {
    AnioEscolar create(AnioEscolar anioEscolar);
}
