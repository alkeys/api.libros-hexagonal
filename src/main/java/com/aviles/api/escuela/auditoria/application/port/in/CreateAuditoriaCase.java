package com.aviles.api.escuela.auditoria.application.port.in;

import com.aviles.api.escuela.auditoria.domain.Auditoria;

public interface CreateAuditoriaCase {
    Auditoria create(Auditoria auditoria);
}
