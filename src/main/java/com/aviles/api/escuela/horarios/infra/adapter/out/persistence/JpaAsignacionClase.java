package com.aviles.api.escuela.horarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "asignacion_clase")
public class JpaAsignacionClase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long id;

    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    @Column(name = "id_materia", nullable = false)
    private Long idMateria;

    @Column(name = "id_profesor", nullable = false)
    private Long idProfesor;

    @Column(name = "id_horario", nullable = false)
    private Long idHorario;

    @Column(name = "id_aula", nullable = false)
    private Long idAula;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    public JpaAsignacionClase() {}
    public JpaAsignacionClase(Long id, Long idGrupo, Long idMateria, Long idProfesor, Long idHorario, Long idAula,
                               String modalidad, String estado, String observaciones) {
        this.id = id; this.idGrupo = idGrupo; this.idMateria = idMateria; this.idProfesor = idProfesor;
        this.idHorario = idHorario; this.idAula = idAula; this.modalidad = modalidad; this.estado = estado;
        this.observaciones = observaciones;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdGrupo() { return idGrupo; }
    public void setIdGrupo(Long idGrupo) { this.idGrupo = idGrupo; }
    public Long getIdMateria() { return idMateria; }
    public void setIdMateria(Long idMateria) { this.idMateria = idMateria; }
    public Long getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Long idProfesor) { this.idProfesor = idProfesor; }
    public Long getIdHorario() { return idHorario; }
    public void setIdHorario(Long idHorario) { this.idHorario = idHorario; }
    public Long getIdAula() { return idAula; }
    public void setIdAula(Long idAula) { this.idAula = idAula; }
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
