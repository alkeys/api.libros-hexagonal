package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cobro")
public class JpaCobro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobro")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_concepto", nullable = false)
    private Long idConcepto;

    @Column(name = "id_anio_escolar", nullable = false)
    private Long idAnioEscolar;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    public JpaCobro() {}
    public JpaCobro(Long id, Long idEstudiante, Long idConcepto, Long idAnioEscolar, LocalDate fechaVencimiento, BigDecimal monto, String estado, String observacion) {
        this.id = id; this.idEstudiante = idEstudiante; this.idConcepto = idConcepto; this.idAnioEscolar = idAnioEscolar;
        this.fechaVencimiento = fechaVencimiento; this.monto = monto; this.estado = estado; this.observacion = observacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public Long getIdConcepto() { return idConcepto; }
    public void setIdConcepto(Long idConcepto) { this.idConcepto = idConcepto; }
    public Long getIdAnioEscolar() { return idAnioEscolar; }
    public void setIdAnioEscolar(Long idAnioEscolar) { this.idAnioEscolar = idAnioEscolar; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}
