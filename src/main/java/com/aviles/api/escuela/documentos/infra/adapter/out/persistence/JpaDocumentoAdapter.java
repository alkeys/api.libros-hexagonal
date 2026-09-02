package com.aviles.api.escuela.documentos.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.documentos.application.port.out.DocumentoRepositoryPort;
import com.aviles.api.escuela.documentos.domain.DocumentoEstudiante;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaDocumentoAdapter implements DocumentoRepositoryPort {

    private final SpringDataDocumentoRepository repository;

    public JpaDocumentoAdapter(SpringDataDocumentoRepository repository) { this.repository = repository; }

    @Override
    public DocumentoEstudiante save(DocumentoEstudiante d) { return toDomain(repository.save(toJpa(d))); }
    @Override
    public List<DocumentoEstudiante> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private DocumentoEstudiante toDomain(JpaDocumentoEstudiante j) {
        return new DocumentoEstudiante(new Id(j.getId()), new Id(j.getIdEstudiante()), j.getTipoDocumento(), j.getNombreArchivo(), j.getUrlArchivo(), j.getFechaSubida(), j.getEstado(), j.getObservacion());
    }
    private JpaDocumentoEstudiante toJpa(DocumentoEstudiante d) {
        return new JpaDocumentoEstudiante(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.tipoDocumento(), d.nombreArchivo(), d.urlArchivo(), d.fechaSubida(), d.estado(), d.observacion());
    }
}
