package com.aviles.api.escuela.usuarios.application.port.out;

import java.util.Optional;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface UsuarioVinculoRepositoryPort {
    Optional<Long> findProfesorIdByUsuario(Id idUsuario);
    Optional<Long> findEstudianteIdByUsuario(Id idUsuario);
    void saveVinculoProfesor(Id idUsuario, Long idProfesor);
    void saveVinculoEstudiante(Id idUsuario, Long idEstudiante);
}