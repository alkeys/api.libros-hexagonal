package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto_emergencia")
public class JpaContactoEmergencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "parentesco", length = 50)
    private String parentesco;

    @Column(name = "telefono", nullable = false, length = 30)
    private String telefono;

    @Column(name = "telefono_alternativo", length = 30)
    private String telefonoAlternativo;

    @Column(name = "prioridad")
    private Integer prioridad;

    public JpaContactoEmergencia() {}

    public JpaContactoEmergencia(Long id, Long idEstudiante, String nombres, String apellidos, String parentesco,
                                  String telefono, String telefonoAlternativo, Integer prioridad) {
        this.id = id; this.idEstudiante = idEstudiante; this.nombres = nombres; this.apellidos = apellidos;
        this.parentesco = parentesco; this.telefono = telefono; this.telefonoAlternativo = telefonoAlternativo; this.prioridad = prioridad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTelefonoAlternativo() { return telefonoAlternativo; }
    public void setTelefonoAlternativo(String telefonoAlternativo) { this.telefonoAlternativo = telefonoAlternativo; }
    public Integer getPrioridad() { return prioridad; }
    public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }
}
