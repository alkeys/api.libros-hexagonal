package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pago")
public class JpaPago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @Column(name = "id_cobro", nullable = false)
    private Long idCobro;

    @Column(name = "fecha_pago")
    private OffsetDateTime fechaPago;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "referencia", length = 100)
    private String referencia;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "id_usuario")
    private Long idUsuario;

    public JpaPago() {}
    public JpaPago(Long id, Long idCobro, OffsetDateTime fechaPago, BigDecimal monto, String metodoPago, String referencia, String observacion, Long idUsuario) {
        this.id = id; this.idCobro = idCobro; this.fechaPago = fechaPago; this.monto = monto;
        this.metodoPago = metodoPago; this.referencia = referencia; this.observacion = observacion; this.idUsuario = idUsuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdCobro() { return idCobro; }
    public void setIdCobro(Long idCobro) { this.idCobro = idCobro; }
    public OffsetDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(OffsetDateTime fechaPago) { this.fechaPago = fechaPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
}
