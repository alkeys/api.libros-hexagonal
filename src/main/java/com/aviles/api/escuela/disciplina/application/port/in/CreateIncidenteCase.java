package com.aviles.api.escuela.disciplina.application.port.in;

import com.aviles.api.escuela.disciplina.domain.IncidenteDisciplinario;

public interface CreateIncidenteCase {
    IncidenteDisciplinario create(IncidenteDisciplinario incidente);
}
