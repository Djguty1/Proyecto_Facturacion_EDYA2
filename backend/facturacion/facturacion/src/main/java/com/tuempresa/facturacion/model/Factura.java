package com.tuempresa.facturacion.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;
    private String estado;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

}