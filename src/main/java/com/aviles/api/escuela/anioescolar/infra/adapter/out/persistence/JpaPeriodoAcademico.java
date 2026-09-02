package com.aviles.api.escuela.anioescolar.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "periodo_academico")
public class JpaPeriodoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long id;

    @Column(name = "id_anio_escolar", nullable = false)
    private Long idAnioEscolar;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "numero_periodo", nullable = false)
    private Integer numeroPeriodo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado")
    private String estado;

    public JpaPeriodoAcademico() {}

    public JpaPeriodoAcademico(Long id, Long idAnioEscolar, String nombre, Integer numeroPeriodo,
                                LocalDate fechaInicio, LocalDate fechaFin, String estado) {
        this.id = id;
        this.idAnioEscolar = idAnioEscolar;
        this.nombre = nombre;
        this.numeroPeriodo = numeroPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdAnioEscolar() { return idAnioEscolar; }
    public void setIdAnioEscolar(Long idAnioEscolar) { this.idAnioEscolar = idAnioEscolar; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getNumeroPeriodo() { return numeroPeriodo; }
    public void setNumeroPeriodo(Integer numeroPeriodo) { this.numeroPeriodo = numeroPeriodo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
