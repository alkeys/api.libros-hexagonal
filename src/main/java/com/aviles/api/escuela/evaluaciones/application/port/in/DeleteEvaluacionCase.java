package com.aviles.api.escuela.evaluaciones.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteEvaluacionCase {
    void delete(Id id);
}