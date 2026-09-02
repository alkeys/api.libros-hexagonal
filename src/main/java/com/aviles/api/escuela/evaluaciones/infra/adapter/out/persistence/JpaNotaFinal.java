package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nota_final")
public class JpaNotaFinal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_final")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_asignacion", nullable = false)
    private Long idAsignacion;

    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Column(name = "nota", nullable = false, precision = 5, scale = 2)
    private BigDecimal nota;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_registro")
    private OffsetDateTime fechaRegistro;

    public JpaNotaFinal() {}
    public JpaNotaFinal(Long id, Long idEstudiante, Long idAsignacion, Long idPeriodo, BigDecimal nota, String estado, String observacion, OffsetDateTime fechaRegistro) {
        this.id = id; this.idEstudiante = idEstudiante; this.idAsignacion = idAsignacion; this.idPeriodo = idPeriodo;
        this.nota = nota; this.estado = estado; this.observacion = observacion; this.fechaRegistro = fechaRegistro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }
    public Long getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Long idPeriodo) { this.idPeriodo = idPeriodo; }
    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public OffsetDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(OffsetDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
