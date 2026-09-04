package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_estudiante")
public class JpaUsuarioEstudiante {
    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    public JpaUsuarioEstudiante() {}

    public JpaUsuarioEstudiante(Long idUsuario, Long idEstudiante) {
        this.idUsuario = idUsuario;
        this.idEstudiante = idEstudiante;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
}