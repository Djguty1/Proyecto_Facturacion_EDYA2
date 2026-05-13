package com.tuempresa.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.facturacion.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
