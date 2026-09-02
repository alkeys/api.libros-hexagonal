package com.aviles.api.escuela.profesores.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.profesores.application.port.out.*;
import com.aviles.api.escuela.profesores.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaProfesorAdapter implements ProfesorRepositoryPort, DepartamentoRepositoryPort {

    private final SpringDataProfesorRepository profesorRepo;
    private final SpringDataDepartamentoRepository departamentoRepo;

    public JpaProfesorAdapter(SpringDataProfesorRepository profesorRepo, SpringDataDepartamentoRepository departamentoRepo) {
        this.profesorRepo = profesorRepo;
        this.departamentoRepo = departamentoRepo;
    }

    @Override
    public Profesor save(Profesor profesor) { return toDomainProfesor(profesorRepo.save(toJpaProfesor(profesor))); }

    @Override
    public List<Profesor> findAll() { return profesorRepo.findAll().stream().map(this::toDomainProfesor).collect(Collectors.toList()); }

    @Override
    public Departamento save(Departamento departamento) { return toDomainDepartamento(departamentoRepo.save(toJpaDepartamento(departamento))); }

    @Override
    public List<Departamento> findAllDepartamentos() { return departamentoRepo.findAll().stream().map(this::toDomainDepartamento).collect(Collectors.toList()); }

    private Profesor toDomainProfesor(JpaProfesor j) {
        return new Profesor(new Id(j.getId()), j.getCodigoProfesor(), j.getNombres(), j.getApellidos(),
                j.getDui(), j.getEspecialidad(), j.getCorreoElectronico(), j.getTelefono(), j.getDireccion(),
                j.getFechaContratacion(), j.getEstado(), j.getFotoUrl(), j.getFechaCreacion());
    }

    private JpaProfesor toJpaProfesor(Profesor d) {
        return new JpaProfesor(d.id() != null ? d.id().getValue() : null, d.codigoProfesor(), d.nombres(), d.apellidos(),
                d.dui(), d.especialidad(), d.correoElectronico(), d.telefono(), d.direccion(),
                d.fechaContratacion(), d.estado(), d.fotoUrl(), d.fechaCreacion());
    }

    private Departamento toDomainDepartamento(JpaDepartamento j) {
        return new Departamento(new Id(j.getId()), j.getNombre(), j.getDescripcion());
    }

    private JpaDepartamento toJpaDepartamento(Departamento d) {
        return new JpaDepartamento(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion());
    }
}
