package com.example.tpdb.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Factura extends ClaseBase{
    private String fecha;
    private int numero;
    private double descuento;
    private String formaPago;
    private int total;

    @OneToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

}
