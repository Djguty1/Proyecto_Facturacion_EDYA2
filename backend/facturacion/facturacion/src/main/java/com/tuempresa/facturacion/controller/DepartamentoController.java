package com.tuempresa.facturacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.tuempresa.facturacion.model.Departamento;
import com.tuempresa.facturacion.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin(origins = "http://localhost:5173")

public class DepartamentoController {

    private final DepartamentoRepository repo;

    public DepartamentoController(DepartamentoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Departamento> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Departamento obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
    }

    @PostMapping
    public Departamento guardar(@RequestBody Departamento d) {
        return repo.save(d);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @RequestBody Departamento datos) {

        Departamento d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        d.setNombre(datos.getNombre());

        return repo.save(d);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "Departamento eliminado";
    }
}