package com.aviles.api.escuela.actividades.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "entrega_actividad")
public class JpaEntregaActividad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long id;

    @Column(name = "id_actividad", nullable = false)
    private Long idActividad;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "fecha_entrega")
    private OffsetDateTime fechaEntrega;

    @Column(name = "archivo_url", length = 500)
    private String archivoUrl;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "nota", precision = 5, scale = 2)
    private BigDecimal nota;

    @Column(name = "estado")
    private String estado;

    public JpaEntregaActividad() {}
    public JpaEntregaActividad(Long id, Long idActividad, Long idEstudiante, OffsetDateTime fechaEntrega, String archivoUrl, String comentario, BigDecimal nota, String estado) {
        this.id = id; this.idActividad = idActividad; this.idEstudiante = idEstudiante; this.fechaEntrega = fechaEntrega;
        this.archivoUrl = archivoUrl; this.comentario = comentario; this.nota = nota; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdActividad() { return idActividad; }
    public void setIdActividad(Long idActividad) { this.idActividad = idActividad; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public OffsetDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(OffsetDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
