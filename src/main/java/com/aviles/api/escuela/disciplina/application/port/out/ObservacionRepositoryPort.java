package com.aviles.api.escuela.disciplina.application.port.out;

import com.aviles.api.escuela.disciplina.domain.ObservacionAcademica;

public interface ObservacionRepositoryPort {
    ObservacionAcademica save(ObservacionAcademica observacion);
    java.util.List<ObservacionAcademica> findAllObservaciones();
}
