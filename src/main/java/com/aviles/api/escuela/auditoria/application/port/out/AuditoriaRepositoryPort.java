package com.aviles.api.escuela.auditoria.application.port.out;

import com.aviles.api.escuela.auditoria.domain.Auditoria;

public interface AuditoriaRepositoryPort {
    Auditoria save(Auditoria auditoria);
    java.util.List<Auditoria> findAll();
}
