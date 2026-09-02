package com.aviles.api.escuela.disciplina.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "observacion_academica")
public class JpaObservacionAcademica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observacion")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_profesor")
    private Long idProfesor;

    @Column(name = "id_periodo")
    private Long idPeriodo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    public JpaObservacionAcademica() {}
    public JpaObservacionAcademica(Long id, Long idEstudiante, Long idProfesor, Long idPeriodo, LocalDate fecha, String tipo, String descripcion) {
        this.id = id; this.idEstudiante = idEstudiante; this.idProfesor = idProfesor; this.idPeriodo = idPeriodo;
        this.fecha = fecha; this.tipo = tipo; this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public Long getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Long idProfesor) { this.idProfesor = idProfesor; }
    public Long getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Long idPeriodo) { this.idPeriodo = idPeriodo; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
