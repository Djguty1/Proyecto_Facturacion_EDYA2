package com.tuempresa.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.facturacion.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
