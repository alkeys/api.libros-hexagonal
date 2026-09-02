package com.aviles.api.escuela.documentos.application.port.in;

import com.aviles.api.escuela.documentos.domain.DocumentoEstudiante;

public interface CreateDocumentoCase {
    DocumentoEstudiante create(DocumentoEstudiante documento);
}
