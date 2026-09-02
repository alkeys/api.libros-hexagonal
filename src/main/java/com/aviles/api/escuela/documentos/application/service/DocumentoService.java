package com.aviles.api.escuela.documentos.application.service;

import org.springframework.stereotype.Service;
import com.aviles.api.escuela.documentos.application.port.in.CreateDocumentoCase;
import com.aviles.api.escuela.documentos.application.port.out.DocumentoRepositoryPort;
import com.aviles.api.escuela.documentos.domain.DocumentoEstudiante;

@Service
public class DocumentoService implements CreateDocumentoCase {
    private final DocumentoRepositoryPort documentoRepositoryPort;

    public DocumentoService(DocumentoRepositoryPort documentoRepositoryPort) {
        this.documentoRepositoryPort = documentoRepositoryPort;
    }

    @Override
    public DocumentoEstudiante create(DocumentoEstudiante documento) { return documentoRepositoryPort.save(documento); }
}
