package com.tuempresa.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.facturacion.model.Trabajador;

import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    Optional<Trabajador> findByUsuario(String usuario);
}