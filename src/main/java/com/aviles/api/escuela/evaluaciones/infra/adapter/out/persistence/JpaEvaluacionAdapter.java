package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.evaluaciones.application.port.out.*;
import com.aviles.api.escuela.evaluaciones.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaEvaluacionAdapter implements EvaluacionRepositoryPort, CalificacionRepositoryPort, NotaFinalRepositoryPort {

    private final SpringDataEvaluacionRepository evaluacionRepo;
    private final SpringDataCalificacionRepository calificacionRepo;
    private final SpringDataNotaFinalRepository notaFinalRepo;

    public JpaEvaluacionAdapter(SpringDataEvaluacionRepository evaluacionRepo, SpringDataCalificacionRepository calificacionRepo,
                                 SpringDataNotaFinalRepository notaFinalRepo) {
        this.evaluacionRepo = evaluacionRepo;
        this.calificacionRepo = calificacionRepo;
        this.notaFinalRepo = notaFinalRepo;
    }

    @Override
    public Evaluacion save(Evaluacion e) { return toDomainEvaluacion(evaluacionRepo.save(toJpaEvaluacion(e))); }
    @Override
    public List<Evaluacion> findAllEvaluaciones() { return evaluacionRepo.findAll().stream().map(this::toDomainEvaluacion).collect(Collectors.toList()); }
    @Override
    public Calificacion save(Calificacion c) { return toDomainCalificacion(calificacionRepo.save(toJpaCalificacion(c))); }
    @Override
    public List<Calificacion> findByEvaluacion(Id idEvaluacion) { return calificacionRepo.findByIdEvaluacion(idEvaluacion.getValue()).stream().map(this::toDomainCalificacion).collect(Collectors.toList()); }
    @Override
    public NotaFinal save(NotaFinal n) { return toDomainNotaFinal(notaFinalRepo.save(toJpaNotaFinal(n))); }
    @Override
    public List<NotaFinal> findAllNotasFinales() { return notaFinalRepo.findAll().stream().map(this::toDomainNotaFinal).collect(Collectors.toList()); }

    private Evaluacion toDomainEvaluacion(JpaEvaluacion j) { return new Evaluacion(new Id(j.getId()), new Id(j.getIdAsignacion()), new Id(j.getIdPeriodo()), new Id(j.getIdTipoEvaluacion()), j.getNombre(), j.getDescripcion(), j.getFechaEvaluacion(), j.getPorcentaje(), j.getNotaMaxima(), j.getEstado()); }
    private JpaEvaluacion toJpaEvaluacion(Evaluacion d) { return new JpaEvaluacion(d.id() != null ? d.id().getValue() : null, d.idAsignacion().getValue(), d.idPeriodo().getValue(), d.idTipoEvaluacion().getValue(), d.nombre(), d.descripcion(), d.fechaEvaluacion(), d.porcentaje(), d.notaMaxima(), d.estado()); }
    private Calificacion toDomainCalificacion(JpaCalificacion j) { return new Calificacion(new Id(j.getId()), new Id(j.getIdEvaluacion()), new Id(j.getIdEstudiante()), j.getNotaObtenida(), j.getObservacion(), j.getFechaRegistro()); }
    private JpaCalificacion toJpaCalificacion(Calificacion d) { return new JpaCalificacion(d.id() != null ? d.id().getValue() : null, d.idEvaluacion().getValue(), d.idEstudiante().getValue(), d.notaObtenida(), d.observacion(), d.fechaRegistro(), null); }
    private NotaFinal toDomainNotaFinal(JpaNotaFinal j) { return new NotaFinal(new Id(j.getId()), new Id(j.getIdEstudiante()), new Id(j.getIdAsignacion()), new Id(j.getIdPeriodo()), j.getNota(), j.getEstado(), j.getObservacion(), j.getFechaRegistro()); }
    private JpaNotaFinal toJpaNotaFinal(NotaFinal d) { return new JpaNotaFinal(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.idAsignacion().getValue(), d.idPeriodo().getValue(), d.nota(), d.estado(), d.observacion(), d.fechaRegistro()); }
}
