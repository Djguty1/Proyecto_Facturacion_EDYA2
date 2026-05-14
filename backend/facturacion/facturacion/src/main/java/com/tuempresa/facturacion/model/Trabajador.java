package com.tuempresa.facturacion.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trabajador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @Column(unique = true)
    private String usuario;
    private String password;
    private String rol;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
