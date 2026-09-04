package com.aviles.api.escuela.anioescolar.application.port.in;

import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;

public interface UpdateAnioEscolarCase {
    AnioEscolar update(AnioEscolar anioEscolar);
}