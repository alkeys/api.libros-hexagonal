package com.aviles.api.escuela.representantes.application.port.in;

import com.aviles.api.escuela.representantes.domain.Representante;

public interface UpdateRepresentanteCase {
    Representante update(Representante representante);
}