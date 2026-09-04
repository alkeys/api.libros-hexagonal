package com.aviles.api.escuela.materias.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteMateriaCase {
    void delete(Id id);
}