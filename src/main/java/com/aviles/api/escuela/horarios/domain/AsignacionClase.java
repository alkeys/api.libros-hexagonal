package com.aviles.api.escuela.horarios.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una asignación de clase.
 * Vincula un grupo, materia, profesor, horario y aula.
 */
public record AsignacionClase(
    Id id,
    Id idGrupo,
    Id idMateria,
    Id idProfesor,
    Id idHorario,
    Id idAula,
    String modalidad,
    String estado,
    String observaciones
) {
    public AsignacionClase {
        if (idGrupo == null) throw new IllegalArgumentException("El grupo es obligatorio");
        if (idMateria == null) throw new IllegalArgumentException("La materia es obligatoria");
        if (idProfesor == null) throw new IllegalArgumentException("El profesor es obligatorio");
        if (idHorario == null) throw new IllegalArgumentException("El horario es obligatorio");
        if (idAula == null) throw new IllegalArgumentException("El aula es obligatoria");
    }

    public static AsignacionClase nueva(Id idGrupo, Id idMateria, Id idProfesor, Id idHorario, Id idAula, String modalidad) {
        return new AsignacionClase(null, idGrupo, idMateria, idProfesor, idHorario, idAula, modalidad, "ACTIVA", null);
    }
}
