package com.aviles.api.escuela.horarios.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.horarios.application.port.out.*;
import com.aviles.api.escuela.horarios.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaHorarioAdapter implements AulaRepositoryPort, BloqueHorarioRepositoryPort, AsignacionClaseRepositoryPort {

    private final SpringDataAulaRepository aulaRepo;
    private final SpringDataBloqueHorarioRepository bloqueRepo;
    private final SpringDataAsignacionClaseRepository asignacionRepo;

    public JpaHorarioAdapter(SpringDataAulaRepository aulaRepo, SpringDataBloqueHorarioRepository bloqueRepo,
                              SpringDataAsignacionClaseRepository asignacionRepo) {
        this.aulaRepo = aulaRepo;
        this.bloqueRepo = bloqueRepo;
        this.asignacionRepo = asignacionRepo;
    }

    @Override
    public Aula save(Aula aula) { return toDomainAula(aulaRepo.save(toJpaAula(aula))); }
    @Override
    public List<Aula> findAll() { return aulaRepo.findAll().stream().map(this::toDomainAula).collect(Collectors.toList()); }
    @Override
    public Optional<Aula> findAulaById(Long id) { return aulaRepo.findById(id).map(this::toDomainAula); }
    @Override
    public void deleteAulaById(Long id) { aulaRepo.deleteById(id); }
    @Override
    public BloqueHorario save(BloqueHorario bloque) { return toDomainBloque(bloqueRepo.save(toJpaBloque(bloque))); }
    @Override
    public List<BloqueHorario> findAllBloques() { return bloqueRepo.findAll().stream().map(this::toDomainBloque).collect(Collectors.toList()); }
    @Override
    public Optional<BloqueHorario> findBloqueById(Long id) { return bloqueRepo.findById(id).map(this::toDomainBloque); }
    @Override
    public void deleteBloqueById(Long id) { bloqueRepo.deleteById(id); }
    @Override
    public AsignacionClase save(AsignacionClase asignacion) { return toDomainAsignacion(asignacionRepo.save(toJpaAsignacion(asignacion))); }
    @Override
    public List<AsignacionClase> findAllAsignaciones() { return asignacionRepo.findAll().stream().map(this::toDomainAsignacion).collect(Collectors.toList()); }
    @Override
    public Optional<AsignacionClase> findAsignacionById(Long id) { return asignacionRepo.findById(id).map(this::toDomainAsignacion); }
    @Override
    public void deleteAsignacionById(Long id) { asignacionRepo.deleteById(id); }

    private Aula toDomainAula(JpaAula j) { return new Aula(new Id(j.getId()), j.getCodigo(), j.getNombre(), j.getEdificio(), j.getPiso(), j.getCapacidad(), j.getTipo(), j.getEstado()); }
    private JpaAula toJpaAula(Aula d) { return new JpaAula(d.id() != null ? d.id().getValue() : null, d.codigo(), d.nombre(), d.edificio(), d.piso(), d.capacidad(), d.tipo(), d.estado()); }
    private BloqueHorario toDomainBloque(JpaBloqueHorario j) { return new BloqueHorario(new Id(j.getId()), j.getDiaSemana(), j.getHoraInicio(), j.getHoraFin()); }
    private JpaBloqueHorario toJpaBloque(BloqueHorario d) { return new JpaBloqueHorario(d.id() != null ? d.id().getValue() : null, d.diaSemana(), d.horaInicio(), d.horaFin()); }
    private AsignacionClase toDomainAsignacion(JpaAsignacionClase j) { return new AsignacionClase(new Id(j.getId()), new Id(j.getIdGrupo()), new Id(j.getIdMateria()), new Id(j.getIdProfesor()), new Id(j.getIdHorario()), new Id(j.getIdAula()), j.getModalidad(), j.getEstado(), j.getObservaciones()); }
    private JpaAsignacionClase toJpaAsignacion(AsignacionClase d) { return new JpaAsignacionClase(d.id() != null ? d.id().getValue() : null, d.idGrupo().getValue(), d.idMateria().getValue(), d.idProfesor().getValue(), d.idHorario().getValue(), d.idAula().getValue(), d.modalidad(), d.estado(), d.observaciones()); }
}
