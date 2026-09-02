package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "usuario")
public class JpaUsuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "correo", unique = true, length = 150)
    private String correo;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    public JpaUsuario() {}
    public JpaUsuario(Long id, String username, String passwordHash, String correo, String estado, Integer intentosFallidos, OffsetDateTime fechaCreacion, OffsetDateTime fechaActualizacion) {
        this.id = id; this.username = username; this.passwordHash = passwordHash; this.correo = correo;
        this.estado = estado; this.intentosFallidos = intentosFallidos; this.fechaCreacion = fechaCreacion; this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getIntentosFallidos() { return intentosFallidos; }
    public void setIntentosFallidos(Integer intentosFallidos) { this.intentosFallidos = intentosFallidos; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public OffsetDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(OffsetDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
