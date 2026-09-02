package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "evaluacion")
public class JpaEvaluacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private Long id;

    @Column(name = "id_asignacion", nullable = false)
    private Long idAsignacion;

    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Column(name = "id_tipo_evaluacion", nullable = false)
    private Long idTipoEvaluacion;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_evaluacion", nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(name = "porcentaje", precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "nota_maxima", precision = 5, scale = 2)
    private BigDecimal notaMaxima;

    @Column(name = "estado")
    private String estado;

    public JpaEvaluacion() {}
    public JpaEvaluacion(Long id, Long idAsignacion, Long idPeriodo, Long idTipoEvaluacion, String nombre,
                          String descripcion, LocalDate fechaEvaluacion, BigDecimal porcentaje, BigDecimal notaMaxima, String estado) {
        this.id = id; this.idAsignacion = idAsignacion; this.idPeriodo = idPeriodo; this.idTipoEvaluacion = idTipoEvaluacion;
        this.nombre = nombre; this.descripcion = descripcion; this.fechaEvaluacion = fechaEvaluacion;
        this.porcentaje = porcentaje; this.notaMaxima = notaMaxima; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }
    public Long getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Long idPeriodo) { this.idPeriodo = idPeriodo; }
    public Long getIdTipoEvaluacion() { return idTipoEvaluacion; }
    public void setIdTipoEvaluacion(Long idTipoEvaluacion) { this.idTipoEvaluacion = idTipoEvaluacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaEvaluacion() { return fechaEvaluacion; }
    public void setFechaEvaluacion(LocalDate fechaEvaluacion) { this.fechaEvaluacion = fechaEvaluacion; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public BigDecimal getNotaMaxima() { return notaMaxima; }
    public void setNotaMaxima(BigDecimal notaMaxima) { this.notaMaxima = notaMaxima; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
