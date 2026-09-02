package com.aviles.api.escuela.horarios.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "bloque_horario")
public class JpaBloqueHorario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long id;

    @Column(name = "dia_semana", nullable = false)
    private String diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    public JpaBloqueHorario() {}
    public JpaBloqueHorario(Long id, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id; this.diaSemana = diaSemana; this.horaInicio = horaInicio; this.horaFin = horaFin;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
}
