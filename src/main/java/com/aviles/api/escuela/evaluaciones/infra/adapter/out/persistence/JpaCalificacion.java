package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "calificacion")
public class JpaCalificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Long id;

    @Column(name = "id_evaluacion", nullable = false)
    private Long idEvaluacion;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "nota_obtenida", precision = 5, scale = 2)
    private BigDecimal notaObtenida;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_registro")
    private OffsetDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    public JpaCalificacion() {}
    public JpaCalificacion(Long id, Long idEvaluacion, Long idEstudiante, BigDecimal notaObtenida, String observacion,
                            OffsetDateTime fechaRegistro, OffsetDateTime fechaActualizacion) {
        this.id = id; this.idEvaluacion = idEvaluacion; this.idEstudiante = idEstudiante;
        this.notaObtenida = notaObtenida; this.observacion = observacion; this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEvaluacion() { return idEvaluacion; }
    public void setIdEvaluacion(Long idEvaluacion) { this.idEvaluacion = idEvaluacion; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public BigDecimal getNotaObtenida() { return notaObtenida; }
    public void setNotaObtenida(BigDecimal notaObtenida) { this.notaObtenida = notaObtenida; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public OffsetDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(OffsetDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public OffsetDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(OffsetDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
