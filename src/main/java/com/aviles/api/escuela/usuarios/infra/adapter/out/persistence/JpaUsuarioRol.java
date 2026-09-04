package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_rol")
@IdClass(JpaUsuarioRolId.class)
public class JpaUsuarioRol {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Id
    @Column(name = "id_rol")
    private Long idRol;

    public JpaUsuarioRol() {}

    public JpaUsuarioRol(Long idUsuario, Long idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }
}