package com.aviles.api.escuela.configuracion.infra.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.configuracion.application.port.out.ConfiguracionRepositoryPort;
import com.aviles.api.escuela.configuracion.domain.Configuracion;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Adaptador de salida que implementa el puerto de persistencia de configuración.
 * Convierte entre la entidad de dominio y la entidad JPA.
 */
@Component
public class JpaConfiguracionAdapter implements ConfiguracionRepositoryPort {

    private final SpringDataConfiguracionRepository repository;

    public JpaConfiguracionAdapter(SpringDataConfiguracionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Configuracion find() {
        return repository.findAll().stream()
                .findFirst()
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public Configuracion save(Configuracion configuracion) {
        JpaConfiguracion jpa = toJpa(configuracion);
        JpaConfiguracion saved = repository.save(jpa);
        return toDomain(saved);
    }

    private Configuracion toDomain(JpaConfiguracion jpa) {
        return new Configuracion(
            new Id(jpa.getId()),
            jpa.getNombreInstitucion(),
            jpa.getDireccion(),
            jpa.getTelefono(),
            jpa.getCorreo(),
            jpa.getSitioWeb(),
            jpa.getLogoUrl(),
            jpa.getEscalaMinima(),
            jpa.getEscalaMaxima(),
            jpa.getNotaAprobacion(),
            jpa.getMoneda(),
            jpa.getZonaHoraria(),
            jpa.getFechaCreacion()
        );
    }

    private JpaConfiguracion toJpa(Configuracion domain) {
        return new JpaConfiguracion(
            domain.id() != null ? domain.id().getValue() : null,
            domain.nombreInstitucion(),
            domain.direccion(),
            domain.telefono(),
            domain.correo(),
            domain.sitioWeb(),
            domain.logoUrl(),
            domain.escalaMinima(),
            domain.escalaMaxima(),
            domain.notaAprobacion(),
            domain.moneda(),
            domain.zonaHoraria(),
            domain.fechaCreacion()
        );
    }
}
