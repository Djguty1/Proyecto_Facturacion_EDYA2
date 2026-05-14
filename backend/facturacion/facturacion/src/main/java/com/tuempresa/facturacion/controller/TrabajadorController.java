package com.tuempresa.facturacion.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.tuempresa.facturacion.repository.TrabajadorRepository;
import com.tuempresa.facturacion.model.Trabajador;

@RestController
@RequestMapping("/trabajadores")
@CrossOrigin(origins = "http://localhost:5173")

public class TrabajadorController {

    private final TrabajadorRepository repo;
    private final BCryptPasswordEncoder encoder;

    public TrabajadorController(
            TrabajadorRepository repo,
            BCryptPasswordEncoder encoder
    ) {
        this.repo = repo;
        this.encoder = encoder;
    }


    @GetMapping
    public List<Trabajador> listar() {
        return repo.findAll();
    }


    @GetMapping("/{id}")
    public Trabajador obtenerPorId(@PathVariable Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Trabajador no encontrado"));
    }

    @PostMapping
    public Trabajador guardar(@RequestBody Trabajador t) {

        // ENCRIPTAR PASSWORD
        t.setPassword(
                encoder.encode(t.getPassword())
        );

        return repo.save(t);
    }

    @PutMapping("/{id}")
    public Trabajador actualizar(
            @PathVariable Long id,
            @RequestBody Trabajador datos
    ) {

        Trabajador t = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Trabajador no encontrado"));

        t.setNombre(datos.getNombre());
        t.setEmail(datos.getEmail());
        t.setUsuario(datos.getUsuario());
        t.setRol(datos.getRol());

        if (datos.getPassword() != null &&
                !datos.getPassword().isEmpty()) {

            t.setPassword(
                    encoder.encode(datos.getPassword())
            );
        }

        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        repo.deleteById(id);

        return "Trabajador eliminado correctamente";
    }
}