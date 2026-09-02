package com.aviles.api.escuela.disciplina.application.port.out;

import com.aviles.api.escuela.disciplina.domain.IncidenteDisciplinario;

public interface IncidenteRepositoryPort {
    IncidenteDisciplinario save(IncidenteDisciplinario incidente);
    java.util.List<IncidenteDisciplinario> findAllIncidentes();
}
