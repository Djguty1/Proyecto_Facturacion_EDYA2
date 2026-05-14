package com.tuempresa.facturacion.controller;

import com.tuempresa.facturacion.security.JwtUtil;
import com.tuempresa.facturacion.dto.LoginRequest;
import com.tuempresa.facturacion.dto.LoginResponse;
import com.tuempresa.facturacion.model.Trabajador;
import com.tuempresa.facturacion.repository.TrabajadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private TrabajadorRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        Trabajador trabajador = repository
                .findByUsuario(request.getUsuario())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        boolean passwordCorrecta = encoder.matches(
                request.getPassword(),
                trabajador.getPassword()
        );

        if (!passwordCorrecta) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(
                trabajador.getUsuario()
        );

        return new LoginResponse(token);
    }
}