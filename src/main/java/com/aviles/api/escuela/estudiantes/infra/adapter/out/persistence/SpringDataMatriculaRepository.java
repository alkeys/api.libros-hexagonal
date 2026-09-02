package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMatriculaRepository extends JpaRepository<JpaMatricula, Long> {
    List<JpaMatricula> findByIdGrupo(Long idGrupo);
}
