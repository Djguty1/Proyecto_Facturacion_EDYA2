package com.tuempresa.facturacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.tuempresa.facturacion.model.Pago;
import com.tuempresa.facturacion.repository.PagoRepository;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "http://localhost:5173")

public class PagoController {

    private final PagoRepository repo;

    public PagoController(PagoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Pago> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Pago obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @PostMapping
    public Pago guardar(@RequestBody Pago p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Long id, @RequestBody Pago datos) {

        Pago p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        p.setMonto(datos.getMonto());
        p.setFecha(datos.getFecha());
        p.setFactura(datos.getFactura());

        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "Pago eliminado";
    }
}