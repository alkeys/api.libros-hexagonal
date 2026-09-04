package com.aviles.api.escuela.niveles.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.niveles.application.port.out.*;
import com.aviles.api.escuela.niveles.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaNivelesAdapter implements NivelRepositoryPort, GradoRepositoryPort, SeccionRepositoryPort {

    private final SpringDataNivelRepository nivelRepo;
    private final SpringDataGradoRepository gradoRepo;
    private final SpringDataSeccionRepository seccionRepo;

    public JpaNivelesAdapter(SpringDataNivelRepository nivelRepo, SpringDataGradoRepository gradoRepo,
                              SpringDataSeccionRepository seccionRepo) {
        this.nivelRepo = nivelRepo;
        this.gradoRepo = gradoRepo;
        this.seccionRepo = seccionRepo;
    }

    @Override
    public NivelEducativo save(NivelEducativo nivel) {
        return toDomainNivel(nivelRepo.save(toJpaNivel(nivel)));
    }

    @Override
    public List<NivelEducativo> findAll() {
        return nivelRepo.findAll().stream().map(this::toDomainNivel).collect(Collectors.toList());
    }

    @Override
    public Optional<NivelEducativo> findNivelById(Long id) {
        return nivelRepo.findById(id).map(this::toDomainNivel);
    }

    @Override
    public void deleteNivelById(Long id) {
        nivelRepo.deleteById(id);
    }

    @Override
    public Grado save(Grado grado) {
        return toDomainGrado(gradoRepo.save(toJpaGrado(grado)));
    }

    @Override
    public List<Grado> findAllGrados() {
        return gradoRepo.findAll().stream().map(this::toDomainGrado).collect(Collectors.toList());
    }

    @Override
    public Optional<Grado> findGradoById(Long id) {
        return gradoRepo.findById(id).map(this::toDomainGrado);
    }

    @Override
    public void deleteGradoById(Long id) {
        gradoRepo.deleteById(id);
    }

    @Override
    public List<Grado> findByNivel(Id idNivel) {
        return gradoRepo.findByIdNivel(idNivel.getValue()).stream().map(this::toDomainGrado).collect(Collectors.toList());
    }

    @Override
    public Seccion save(Seccion seccion) {
        return toDomainSeccion(seccionRepo.save(toJpaSeccion(seccion)));
    }

    @Override
    public List<Seccion> findAllSecciones() {
        return seccionRepo.findAll().stream().map(this::toDomainSeccion).collect(Collectors.toList());
    }

    @Override
    public Optional<Seccion> findSeccionById(Long id) {
        return seccionRepo.findById(id).map(this::toDomainSeccion);
    }

    @Override
    public void deleteSeccionById(Long id) {
        seccionRepo.deleteById(id);
    }

    private NivelEducativo toDomainNivel(JpaNivelEducativo j) {
        return new NivelEducativo(new Id(j.getId()), j.getNombre(), j.getDescripcion());
    }

    private JpaNivelEducativo toJpaNivel(NivelEducativo d) {
        return new JpaNivelEducativo(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion());
    }

    private Grado toDomainGrado(JpaGrado j) {
        return new Grado(new Id(j.getId()), new Id(j.getIdNivel()), j.getNombreGrado(), j.getDescripcion());
    }

    private JpaGrado toJpaGrado(Grado d) {
        return new JpaGrado(d.id() != null ? d.id().getValue() : null, d.idNivel().getValue(), d.nombreGrado(), d.descripcion());
    }

    private Seccion toDomainSeccion(JpaSeccion j) {
        return new Seccion(new Id(j.getId()), j.getNombre(), j.getDescripcion());
    }

    private JpaSeccion toJpaSeccion(Seccion d) {
        return new JpaSeccion(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion());
    }
}
