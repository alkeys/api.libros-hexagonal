package com.aviles.api.escuela.disciplina.application.port.in;

import com.aviles.api.escuela.disciplina.domain.ObservacionAcademica;

public interface CreateObservacionCase {
    ObservacionAcademica create(ObservacionAcademica observacion);
}
