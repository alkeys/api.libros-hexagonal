package com.aviles.api.escuela.representantes.application.port.in;

import com.aviles.api.escuela.representantes.domain.Representante;

public interface CreateRepresentanteCase {
    Representante create(Representante representante);
}
