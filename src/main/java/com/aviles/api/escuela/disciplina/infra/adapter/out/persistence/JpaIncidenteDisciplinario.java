package com.aviles.api.escuela.disciplina.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "incidente_disciplinario")
public class JpaIncidenteDisciplinario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidente")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "motivo", nullable = false, length = 255)
    private String motivo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "medida_tomada", columnDefinition = "TEXT")
    private String medidaTomada;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_profesor")
    private Long idProfesor;

    @Column(name = "fecha_registro")
    private OffsetDateTime fechaRegistro;

    public JpaIncidenteDisciplinario() {}
    public JpaIncidenteDisciplinario(Long id, Long idEstudiante, LocalDate fecha, String tipo, String motivo, String descripcion, String medidaTomada, String estado, Long idProfesor, OffsetDateTime fechaRegistro) {
        this.id = id; this.idEstudiante = idEstudiante; this.fecha = fecha; this.tipo = tipo; this.motivo = motivo;
        this.descripcion = descripcion; this.medidaTomada = medidaTomada; this.estado = estado; this.idProfesor = idProfesor; this.fechaRegistro = fechaRegistro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getMedidaTomada() { return medidaTomada; }
    public void setMedidaTomada(String medidaTomada) { this.medidaTomada = medidaTomada; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Long idProfesor) { this.idProfesor = idProfesor; }
    public OffsetDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(OffsetDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
