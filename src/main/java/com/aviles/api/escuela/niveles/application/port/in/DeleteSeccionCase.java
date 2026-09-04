package com.aviles.api.escuela.niveles.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteSeccionCase {
    void deleteSeccion(Id id);
}