package com.aviles.api.escuela.asistencia.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "asistencia")
public class JpaAsistencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_asignacion", nullable = false)
    private Long idAsignacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_registro")
    private OffsetDateTime fechaRegistro;

    public JpaAsistencia() {}
    public JpaAsistencia(Long id, Long idEstudiante, Long idAsignacion, LocalDate fecha, String estado, String observacion, OffsetDateTime fechaRegistro) {
        this.id = id; this.idEstudiante = idEstudiante; this.idAsignacion = idAsignacion;
        this.fecha = fecha; this.estado = estado; this.observacion = observacion; this.fechaRegistro = fechaRegistro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public OffsetDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(OffsetDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
