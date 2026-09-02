package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "matricula")
public class JpaMatricula {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    @Column(name = "fecha_matricula", nullable = false)
    private LocalDate fechaMatricula;

    @Column(name = "tipo_matricula")
    private String tipoMatricula;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    public JpaMatricula() {}

    public JpaMatricula(Long id, Long idEstudiante, Long idGrupo, LocalDate fechaMatricula,
                         String tipoMatricula, String estado, String observaciones, OffsetDateTime fechaCreacion) {
        this.id = id; this.idEstudiante = idEstudiante; this.idGrupo = idGrupo; this.fechaMatricula = fechaMatricula;
        this.tipoMatricula = tipoMatricula; this.estado = estado; this.observaciones = observaciones; this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public Long getIdGrupo() { return idGrupo; }
    public void setIdGrupo(Long idGrupo) { this.idGrupo = idGrupo; }
    public LocalDate getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(LocalDate fechaMatricula) { this.fechaMatricula = fechaMatricula; }
    public String getTipoMatricula() { return tipoMatricula; }
    public void setTipoMatricula(String tipoMatricula) { this.tipoMatricula = tipoMatricula; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
