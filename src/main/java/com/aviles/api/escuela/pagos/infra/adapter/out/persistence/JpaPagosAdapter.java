package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.pagos.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaPagosAdapter {

    private final SpringDataCobroRepository cobroRepo;
    private final SpringDataPagoRepository pagoRepo;

    public JpaPagosAdapter(SpringDataCobroRepository cobroRepo, SpringDataPagoRepository pagoRepo) {
        this.cobroRepo = cobroRepo;
        this.pagoRepo = pagoRepo;
    }

    public Cobro saveCobro(Cobro c) { return toDomainCobro(cobroRepo.save(toJpaCobro(c))); }
    public List<Cobro> findAllCobros() { return cobroRepo.findAll().stream().map(this::toDomainCobro).collect(Collectors.toList()); }
    public Pago savePago(Pago p) { return toDomainPago(pagoRepo.save(toJpaPago(p))); }
    public List<Pago> findAllPagos() { return pagoRepo.findAll().stream().map(this::toDomainPago).collect(Collectors.toList()); }

    private Cobro toDomainCobro(JpaCobro j) { return new Cobro(new Id(j.getId()), new Id(j.getIdEstudiante()), new Id(j.getIdConcepto()), new Id(j.getIdAnioEscolar()), j.getFechaVencimiento(), j.getMonto(), j.getEstado(), j.getObservacion()); }
    private JpaCobro toJpaCobro(Cobro d) { return new JpaCobro(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.idConcepto().getValue(), d.idAnioEscolar().getValue(), d.fechaVencimiento(), d.monto(), d.estado(), d.observacion()); }
    private Pago toDomainPago(JpaPago j) { return new Pago(new Id(j.getId()), new Id(j.getIdCobro()), j.getFechaPago(), j.getMonto(), j.getMetodoPago(), j.getReferencia(), j.getObservacion(), j.getIdUsuario() != null ? new Id(j.getIdUsuario()) : null); }
    private JpaPago toJpaPago(Pago d) { return new JpaPago(d.id() != null ? d.id().getValue() : null, d.idCobro().getValue(), d.fechaPago(), d.monto(), d.metodoPago(), d.referencia(), d.observacion(), d.idUsuario() != null ? d.idUsuario().getValue() : null); }
}
