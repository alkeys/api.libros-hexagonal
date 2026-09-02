package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "concepto_pago")
public class JpaConceptoPago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "obligatorio")
    private Boolean obligatorio;

    @Column(name = "estado")
    private String estado;

    public JpaConceptoPago() {}
    public JpaConceptoPago(Long id, String nombre, String descripcion, BigDecimal monto, Boolean obligatorio, String estado) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
        this.monto = monto; this.obligatorio = obligatorio; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Boolean getObligatorio() { return obligatorio; }
    public void setObligatorio(Boolean obligatorio) { this.obligatorio = obligatorio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
