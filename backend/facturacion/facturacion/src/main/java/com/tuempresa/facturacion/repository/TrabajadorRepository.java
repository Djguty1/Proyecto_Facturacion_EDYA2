package com.tuempresa.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.facturacion.model.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
}