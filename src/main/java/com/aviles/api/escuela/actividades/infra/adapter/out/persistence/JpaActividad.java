package com.aviles.api.escuela.actividades.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "actividad")
public class JpaActividad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id;

    @Column(name = "id_asignacion", nullable = false)
    private Long idAsignacion;

    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_publicacion")
    private OffsetDateTime fechaPublicacion;

    @Column(name = "fecha_entrega")
    private OffsetDateTime fechaEntrega;

    @Column(name = "porcentaje", precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "estado")
    private String estado;

    public JpaActividad() {}
    public JpaActividad(Long id, Long idAsignacion, Long idPeriodo, String titulo, String descripcion, OffsetDateTime fechaPublicacion, OffsetDateTime fechaEntrega, BigDecimal porcentaje, String estado) {
        this.id = id; this.idAsignacion = idAsignacion; this.idPeriodo = idPeriodo; this.titulo = titulo;
        this.descripcion = descripcion; this.fechaPublicacion = fechaPublicacion; this.fechaEntrega = fechaEntrega;
        this.porcentaje = porcentaje; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }
    public Long getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Long idPeriodo) { this.idPeriodo = idPeriodo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public OffsetDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(OffsetDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
    public OffsetDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(OffsetDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
