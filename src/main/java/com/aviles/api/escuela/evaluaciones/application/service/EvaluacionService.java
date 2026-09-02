package com.aviles.api.escuela.evaluaciones.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.evaluaciones.application.port.in.*;
import com.aviles.api.escuela.evaluaciones.application.port.out.*;
import com.aviles.api.escuela.evaluaciones.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Servicio que implementa los casos de uso del módulo de evaluaciones.
 * Maneja la lógica de negocio para evaluaciones, calificaciones y notas finales.
 */
@Service
public class EvaluacionService implements CreateEvaluacionCase, GetAllEvaluacionesCase,
        CreateCalificacionCase, GetCalificacionesByEvaluacionCase, CreateNotaFinalCase {

    private final EvaluacionRepositoryPort evaluacionRepositoryPort;
    private final CalificacionRepositoryPort calificacionRepositoryPort;
    private final NotaFinalRepositoryPort notaFinalRepositoryPort;

    public EvaluacionService(EvaluacionRepositoryPort evaluacionRepositoryPort, CalificacionRepositoryPort calificacionRepositoryPort,
                              NotaFinalRepositoryPort notaFinalRepositoryPort) {
        this.evaluacionRepositoryPort = evaluacionRepositoryPort;
        this.calificacionRepositoryPort = calificacionRepositoryPort;
        this.notaFinalRepositoryPort = notaFinalRepositoryPort;
    }

    @Override
    public Evaluacion create(Evaluacion evaluacion) { return evaluacionRepositoryPort.save(evaluacion); }

    @Override
    public List<Evaluacion> getAll() { return evaluacionRepositoryPort.findAllEvaluaciones(); }

    @Override
    public Calificacion create(Calificacion calificacion) { return calificacionRepositoryPort.save(calificacion); }

    @Override
    public List<Calificacion> getByEvaluacion(Id idEvaluacion) { return calificacionRepositoryPort.findByEvaluacion(idEvaluacion); }

    @Override
    public NotaFinal create(NotaFinal notaFinal) { return notaFinalRepositoryPort.save(notaFinal); }
}
