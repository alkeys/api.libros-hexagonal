package com.aviles.api.escuela.anioescolar.application.port.in;

import java.util.List;
import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Puerto de entrada para obtener los períodos de un año escolar.
 */
public interface GetPeriodosByAnioCase {
    List<PeriodoAcademico> getByAnioEscolar(Id idAnioEscolar);
}
