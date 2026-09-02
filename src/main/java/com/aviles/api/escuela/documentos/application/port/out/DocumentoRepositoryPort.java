package com.aviles.api.escuela.documentos.application.port.out;

import com.aviles.api.escuela.documentos.domain.DocumentoEstudiante;

public interface DocumentoRepositoryPort {
    DocumentoEstudiante save(DocumentoEstudiante documento);
    java.util.List<DocumentoEstudiante> findAll();
}
