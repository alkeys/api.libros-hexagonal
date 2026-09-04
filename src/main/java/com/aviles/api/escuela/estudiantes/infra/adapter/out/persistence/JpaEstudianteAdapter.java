package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.estudiantes.application.port.out.*;
import com.aviles.api.escuela.estudiantes.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaEstudianteAdapter implements EstudianteRepositoryPort, MatriculaRepositoryPort, ContactoEmergenciaRepositoryPort {

    private final SpringDataEstudianteRepository estudianteRepo;
    private final SpringDataMatriculaRepository matriculaRepo;
    private final SpringDataContactoEmergenciaRepository contactoRepo;

    public JpaEstudianteAdapter(SpringDataEstudianteRepository estudianteRepo, SpringDataMatriculaRepository matriculaRepo,
                                 SpringDataContactoEmergenciaRepository contactoRepo) {
        this.estudianteRepo = estudianteRepo;
        this.matriculaRepo = matriculaRepo;
        this.contactoRepo = contactoRepo;
    }

    @Override
    public Estudiante save(Estudiante estudiante) { return toDomainEstudiante(estudianteRepo.save(toJpaEstudiante(estudiante))); }

    @Override
    public List<Estudiante> findAll() { return estudianteRepo.findAll().stream().map(this::toDomainEstudiante).collect(Collectors.toList()); }

    @Override
    public Optional<Estudiante> findById(Long id) { return estudianteRepo.findById(id).map(this::toDomainEstudiante); }

    @Override
    public void deleteById(Long id) { estudianteRepo.deleteById(id); }

    @Override
    public Matricula save(Matricula matricula) { return toDomainMatricula(matriculaRepo.save(toJpaMatricula(matricula))); }

    @Override
    public List<Matricula> findByGrupo(Id idGrupo) {
        return matriculaRepo.findByIdGrupo(idGrupo.getValue()).stream().map(this::toDomainMatricula).collect(Collectors.toList());
    }

    @Override
    public ContactoEmergencia save(ContactoEmergencia contacto) {
        return toDomainContacto(contactoRepo.save(toJpaContacto(contacto)));
    }

    private Estudiante toDomainEstudiante(JpaEstudiante j) {
        return new Estudiante(new Id(j.getId()), j.getCodigoEstudiante(), j.getNombres(), j.getApellidos(),
                j.getFechaNacimiento(), j.getGenero(), j.getNacionalidad(), j.getDui(), j.getNie(),
                j.getCorreoElectronico(), j.getTelefono(), j.getDireccion(), j.getFechaIngreso(),
                j.getEstado(), j.getFotoUrl(), j.getFechaCreacion(), j.getFechaActualizacion());
    }

    private JpaEstudiante toJpaEstudiante(Estudiante d) {
        return new JpaEstudiante(d.id() != null ? d.id().getValue() : null, d.codigoEstudiante(), d.nombres(), d.apellidos(),
                d.fechaNacimiento(), d.genero(), d.nacionalidad(), d.dui(), d.nie(), d.correoElectronico(),
                d.telefono(), d.direccion(), d.fechaIngreso(), d.estado(), d.fotoUrl(), d.fechaCreacion(), d.fechaActualizacion());
    }

    private Matricula toDomainMatricula(JpaMatricula j) {
        return new Matricula(new Id(j.getId()), new Id(j.getIdEstudiante()), new Id(j.getIdGrupo()),
                j.getFechaMatricula(), j.getTipoMatricula(), j.getEstado(), j.getObservaciones(), j.getFechaCreacion());
    }

    private JpaMatricula toJpaMatricula(Matricula d) {
        return new JpaMatricula(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.idGrupo().getValue(),
                d.fechaMatricula(), d.tipoMatricula(), d.estado(), d.observaciones(), d.fechaCreacion());
    }

    private ContactoEmergencia toDomainContacto(JpaContactoEmergencia j) {
        return new ContactoEmergencia(new Id(j.getId()), new Id(j.getIdEstudiante()), j.getNombres(), j.getApellidos(),
                j.getParentesco(), j.getTelefono(), j.getTelefonoAlternativo(), j.getPrioridad());
    }

    private JpaContactoEmergencia toJpaContacto(ContactoEmergencia d) {
        return new JpaContactoEmergencia(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(),
                d.nombres(), d.apellidos(), d.parentesco(), d.telefono(), d.telefonoAlternativo(), d.prioridad());
    }
}
