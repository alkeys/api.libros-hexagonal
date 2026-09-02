package com.aviles.api.escuela.niveles.infra.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataGradoRepository extends JpaRepository<JpaGrado, Long> {
    List<JpaGrado> findByIdNivel(Long idNivel);
}
