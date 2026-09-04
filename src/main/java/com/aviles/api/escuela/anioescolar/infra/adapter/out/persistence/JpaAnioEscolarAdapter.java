package com.aviles.api.escuela.anioescolar.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.anioescolar.application.port.out.AnioEscolarRepositoryPort;
import com.aviles.api.escuela.anioescolar.application.port.out.PeriodoRepositoryPort;
import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;
import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaAnioEscolarAdapter implements AnioEscolarRepositoryPort, PeriodoRepositoryPort {

    private final SpringDataAnioEscolarRepository anioRepository;
    private final SpringDataPeriodoRepository periodoRepository;

    public JpaAnioEscolarAdapter(SpringDataAnioEscolarRepository anioRepository,
                                  SpringDataPeriodoRepository periodoRepository) {
        this.anioRepository = anioRepository;
        this.periodoRepository = periodoRepository;
    }

    // === AnioEscolar ===

    @Override
    public AnioEscolar save(AnioEscolar anioEscolar) {
        JpaAnioEscolar jpa = toJpaAnio(anioEscolar);
        JpaAnioEscolar saved = anioRepository.save(jpa);
        return toDomainAnio(saved);
    }

    @Override
    public List<AnioEscolar> findAll() {
        return anioRepository.findAll().stream().map(this::toDomainAnio).collect(Collectors.toList());
    }

    @Override
    public Optional<AnioEscolar> findAnioEscolarById(Long id) {
        return anioRepository.findById(id).map(this::toDomainAnio);
    }

    @Override
    public void deleteAnioEscolarById(Long id) {
        anioRepository.deleteById(id);
    }

    // === PeriodoAcademico ===

    @Override
    public PeriodoAcademico save(PeriodoAcademico periodo) {
        JpaPeriodoAcademico jpa = toJpaPeriodo(periodo);
        JpaPeriodoAcademico saved = periodoRepository.save(jpa);
        return toDomainPeriodo(saved);
    }

    @Override
    public List<PeriodoAcademico> findByAnioEscolar(Id idAnioEscolar) {
        return periodoRepository.findByIdAnioEscolar(idAnioEscolar.getValue()).stream()
                .map(this::toDomainPeriodo).collect(Collectors.toList());
    }

    @Override
    public Optional<PeriodoAcademico> findPeriodoById(Long id) {
        return periodoRepository.findById(id).map(this::toDomainPeriodo);
    }

    @Override
    public void deletePeriodoById(Long id) {
        periodoRepository.deleteById(id);
    }

    // === Mappers ===

    private AnioEscolar toDomainAnio(JpaAnioEscolar jpa) {
        return new AnioEscolar(new Id(jpa.getId()), jpa.getNombre(), jpa.getAnio(),
                jpa.getFechaInicio(), jpa.getFechaFin(), jpa.getEstado());
    }

    private JpaAnioEscolar toJpaAnio(AnioEscolar domain) {
        return new JpaAnioEscolar(domain.id() != null ? domain.id().getValue() : null,
                domain.nombre(), domain.anio(), domain.fechaInicio(), domain.fechaFin(), domain.estado());
    }

    private PeriodoAcademico toDomainPeriodo(JpaPeriodoAcademico jpa) {
        return new PeriodoAcademico(new Id(jpa.getId()), new Id(jpa.getIdAnioEscolar()),
                jpa.getNombre(), jpa.getNumeroPeriodo(), jpa.getFechaInicio(), jpa.getFechaFin(), jpa.getEstado());
    }

    private JpaPeriodoAcademico toJpaPeriodo(PeriodoAcademico domain) {
        return new JpaPeriodoAcademico(domain.id() != null ? domain.id().getValue() : null,
                domain.idAnioEscolar().getValue(), domain.nombre(), domain.numeroPeriodo(),
                domain.fechaInicio(), domain.fechaFin(), domain.estado());
    }
}
