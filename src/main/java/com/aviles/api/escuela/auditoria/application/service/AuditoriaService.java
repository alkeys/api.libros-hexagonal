package com.aviles.api.escuela.auditoria.application.service;

import org.springframework.stereotype.Service;
import com.aviles.api.escuela.auditoria.application.port.in.CreateAuditoriaCase;
import com.aviles.api.escuela.auditoria.application.port.out.AuditoriaRepositoryPort;
import com.aviles.api.escuela.auditoria.domain.Auditoria;

@Service
public class AuditoriaService implements CreateAuditoriaCase {
    private final AuditoriaRepositoryPort auditoriaRepositoryPort;

    public AuditoriaService(AuditoriaRepositoryPort auditoriaRepositoryPort) {
        this.auditoriaRepositoryPort = auditoriaRepositoryPort;
    }

    @Override
    public Auditoria create(Auditoria auditoria) { return auditoriaRepositoryPort.save(auditoria); }
}
