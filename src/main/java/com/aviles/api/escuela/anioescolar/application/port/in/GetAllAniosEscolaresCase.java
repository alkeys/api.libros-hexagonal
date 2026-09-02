package com.aviles.api.escuela.anioescolar.application.port.in;

import java.util.List;
import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;

/**
 * Puerto de entrada para obtener todos los años escolares.
 */
public interface GetAllAniosEscolaresCase {
    List<AnioEscolar> getAll();
}
