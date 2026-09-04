package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteAsignacionClaseCase {
    void deleteAsignacion(Id id);
}