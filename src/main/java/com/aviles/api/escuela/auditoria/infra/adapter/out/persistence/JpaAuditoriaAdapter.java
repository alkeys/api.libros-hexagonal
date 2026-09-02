package com.aviles.api.escuela.auditoria.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.auditoria.application.port.out.AuditoriaRepositoryPort;
import com.aviles.api.escuela.auditoria.domain.Auditoria;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaAuditoriaAdapter implements AuditoriaRepositoryPort {

    private final SpringDataAuditoriaRepository repository;

    public JpaAuditoriaAdapter(SpringDataAuditoriaRepository repository) { this.repository = repository; }

    @Override
    public Auditoria save(Auditoria a) { return toDomain(repository.save(toJpa(a))); }
    @Override
    public List<Auditoria> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private Auditoria toDomain(JpaAuditoria j) {
        return new Auditoria(new Id(j.getId()), j.getIdUsuario() != null ? new Id(j.getIdUsuario()) : null, j.getTablaAfectada(), j.getIdRegistro(), j.getAccion(), j.getDatosAnteriores(), j.getDatosNuevos(), j.getIp() != null ? j.getIp().getHostAddress() : null, j.getFecha());
    }
    private JpaAuditoria toJpa(Auditoria d) {
        return new JpaAuditoria(d.id() != null ? d.id().getValue() : null, d.idUsuario() != null ? d.idUsuario().getValue() : null, d.tablaAfectada(), d.idRegistro(), d.accion(), d.datosAnteriores(), d.datosNuevos(), null, d.fecha());
    }
}
