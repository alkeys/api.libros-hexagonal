package com.aviles.api.escuela.horarios.application.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.aviles.api.escuela.horarios.application.port.in.*;
import com.aviles.api.escuela.horarios.application.port.out.*;
import com.aviles.api.escuela.horarios.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import com.aviles.api.escuela.auth.application.AuthContext;
import com.aviles.api.escuela.auth.domain.AuthUser;

@Service
public class HorarioService implements CreateAulaCase, GetAllAulasCase,
        CreateBloqueHorarioCase, GetAllBloquesHorarioCase,
        CreateAsignacionClaseCase, GetAllAsignacionesCase,
        UpdateAulaCase, DeleteAulaCase, UpdateBloqueHorarioCase, DeleteBloqueHorarioCase,
        UpdateAsignacionClaseCase, DeleteAsignacionClaseCase {

    private final AulaRepositoryPort aulaRepositoryPort;
    private final BloqueHorarioRepositoryPort bloqueHorarioRepositoryPort;
    private final AsignacionClaseRepositoryPort asignacionClaseRepositoryPort;

    public HorarioService(AulaRepositoryPort aulaRepositoryPort, BloqueHorarioRepositoryPort bloqueHorarioRepositoryPort,
                          AsignacionClaseRepositoryPort asignacionClaseRepositoryPort) {
        this.aulaRepositoryPort = aulaRepositoryPort;
        this.bloqueHorarioRepositoryPort = bloqueHorarioRepositoryPort;
        this.asignacionClaseRepositoryPort = asignacionClaseRepositoryPort;
    }

    @Override
    public Aula create(Aula aula) { return aulaRepositoryPort.save(aula); }

    @Override
    public List<Aula> getAllAulas() { return aulaRepositoryPort.findAll(); }

    @Override
    public BloqueHorario create(BloqueHorario bloque) { return bloqueHorarioRepositoryPort.save(bloque); }

    @Override
    public List<BloqueHorario> getAllBloques() { return bloqueHorarioRepositoryPort.findAllBloques(); }

    @Override
    public AsignacionClase create(AsignacionClase asignacion) { return asignacionClaseRepositoryPort.save(asignacion); }

    @Override
    public List<AsignacionClase> getAllAsignaciones() {
        List<AsignacionClase> todas = asignacionClaseRepositoryPort.findAllAsignaciones();
        // Un PROFESOR solo puede ver las clases que tiene asignadas (idProfesor del JWT)
        AuthUser user = AuthContext.get();
        if (user != null && !user.hasRole("ADMIN") && user.hasRole("PROFESOR")) {
            Long idProfesor = user.idProfesor();
            if (idProfesor == null) return List.of();
            return todas.stream()
                    .filter(a -> a.idProfesor() != null && a.idProfesor().getValue().equals(idProfesor))
                    .collect(Collectors.toList());
        }
        return todas;
    }

    @Override
    public Aula update(Aula aula) {
        Aula existente = aulaRepositoryPort.findAulaById(aula.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aula no encontrada"));
        Aula actualizada = new Aula(existente.id(), aula.codigo(), aula.nombre(), aula.edificio(), aula.piso(),
                aula.capacidad(), aula.tipo(), existente.estado());
        return aulaRepositoryPort.save(actualizada);
    }

    @Override
    public void deleteAula(Id id) {
        if (aulaRepositoryPort.findAulaById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aula no encontrada");
        }
        aulaRepositoryPort.deleteAulaById(id.getValue());
    }

    @Override
    public BloqueHorario update(BloqueHorario bloque) {
        BloqueHorario existente = bloqueHorarioRepositoryPort.findBloqueById(bloque.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bloque horario no encontrado"));
        return bloqueHorarioRepositoryPort.save(new BloqueHorario(existente.id(), bloque.diaSemana(),
                bloque.horaInicio(), bloque.horaFin()));
    }

    @Override
    public void deleteBloque(Id id) {
        if (bloqueHorarioRepositoryPort.findBloqueById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bloque horario no encontrado");
        }
        bloqueHorarioRepositoryPort.deleteBloqueById(id.getValue());
    }

    @Override
    public AsignacionClase update(AsignacionClase asignacion) {
        AsignacionClase existente = asignacionClaseRepositoryPort.findAsignacionById(asignacion.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada"));
        AsignacionClase actualizada = new AsignacionClase(existente.id(), asignacion.idGrupo(), asignacion.idMateria(),
                asignacion.idProfesor(), asignacion.idHorario(), asignacion.idAula(), asignacion.modalidad(),
                existente.estado(), asignacion.observaciones());
        return asignacionClaseRepositoryPort.save(actualizada);
    }

    @Override
    public void deleteAsignacion(Id id) {
        if (asignacionClaseRepositoryPort.findAsignacionById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada");
        }
        asignacionClaseRepositoryPort.deleteAsignacionById(id.getValue());
    }
}
