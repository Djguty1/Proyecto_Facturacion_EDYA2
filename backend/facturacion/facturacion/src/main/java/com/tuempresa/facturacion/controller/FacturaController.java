package com.tuempresa.facturacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.tuempresa.facturacion.model.Factura;
import com.tuempresa.facturacion.repository.FacturaRepository;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "http://localhost:5173")

public class FacturaController {

    private final FacturaRepository repo;

    public FacturaController(FacturaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Factura> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Factura obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    @PostMapping
    public Factura guardar(@RequestBody Factura f) {
        return repo.save(f);
    }

    @PutMapping("/{id}")
    public Factura actualizar(@PathVariable Long id, @RequestBody Factura datos) {

        Factura f = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        f.setMonto(datos.getMonto());
        f.setEstado(datos.getEstado());
        f.setFecha(datos.getFecha());
        f.setTrabajador(datos.getTrabajador());

        return repo.save(f);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "Factura eliminada";
    }
}