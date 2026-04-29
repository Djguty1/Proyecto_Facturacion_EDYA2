package com.tuempresa.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.facturacion.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
