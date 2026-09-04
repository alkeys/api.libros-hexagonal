package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_profesor")
public class JpaUsuarioProfesor {
    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_profesor", nullable = false)
    private Long idProfesor;

    public JpaUsuarioProfesor() {}

    public JpaUsuarioProfesor(Long idUsuario, Long idProfesor) {
        this.idUsuario = idUsuario;
        this.idProfesor = idProfesor;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Long idProfesor) { this.idProfesor = idProfesor; }
}