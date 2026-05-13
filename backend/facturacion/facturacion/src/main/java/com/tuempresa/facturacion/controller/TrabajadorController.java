package com.tuempresa.facturacion.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.tuempresa.facturacion.repository.TrabajadorRepository;
import com.tuempresa.facturacion.model.Trabajador;

@RestController
@RequestMapping("/trabajadores")
@CrossOrigin(origins = "http://localhost:5173")

public class TrabajadorController {

    private final TrabajadorRepository repo;

    public TrabajadorController(TrabajadorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Trabajador> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Trabajador obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
    }

    @PostMapping
    public Trabajador guardar(@RequestBody Trabajador t) {
        return repo.save(t);
    }

    @PutMapping("/{id}")
    public Trabajador actualizar(@PathVariable Long id, @RequestBody Trabajador datos) {
        Trabajador t = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        t.setNombre(datos.getNombre());
        t.setEmail(datos.getEmail());

        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "Trabajador eliminado correctamente";
    }
}