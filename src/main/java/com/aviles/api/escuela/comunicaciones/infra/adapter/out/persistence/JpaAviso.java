package com.aviles.api.escuela.comunicaciones.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "aviso")
public class JpaAviso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aviso")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_publicacion")
    private OffsetDateTime fechaPublicacion;

    @Column(name = "fecha_expiracion")
    private OffsetDateTime fechaExpiracion;

    @Column(name = "prioridad")
    private String prioridad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_usuario_autor")
    private Long idUsuarioAutor;

    public JpaAviso() {}
    public JpaAviso(Long id, String titulo, String contenido, OffsetDateTime fechaPublicacion, OffsetDateTime fechaExpiracion, String prioridad, String estado, Long idUsuarioAutor) {
        this.id = id; this.titulo = titulo; this.contenido = contenido; this.fechaPublicacion = fechaPublicacion;
        this.fechaExpiracion = fechaExpiracion; this.prioridad = prioridad; this.estado = estado; this.idUsuarioAutor = idUsuarioAutor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public OffsetDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(OffsetDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
    public OffsetDateTime getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(OffsetDateTime fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getIdUsuarioAutor() { return idUsuarioAutor; }
    public void setIdUsuarioAutor(Long idUsuarioAutor) { this.idUsuarioAutor = idUsuarioAutor; }
}
