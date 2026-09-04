package com.aviles.api.escuela.representantes.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteRepresentanteCase {
    void delete(Id id);
}