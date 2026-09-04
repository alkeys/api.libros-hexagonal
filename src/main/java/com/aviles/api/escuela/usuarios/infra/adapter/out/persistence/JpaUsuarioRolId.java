package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.io.Serializable;
import java.util.Objects;

public class JpaUsuarioRolId implements Serializable {
    private Long idUsuario;
    private Long idRol;

    public JpaUsuarioRolId() {}

    public JpaUsuarioRolId(Long idUsuario, Long idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaUsuarioRolId that)) return false;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idRol, that.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idRol);
    }
}