package com.example.tpdb.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pedido extends ClaseBase {

    private String Fecha;
    private String estado;
    private String horaEstimadaEntrega;
    private String tipoEnvio;
    private Double total;

    //Dependiendo el tipo de relación, cambia dónde se genera la FK

   @ManyToOne
   @JoinColumn(name = "usuarioId")
   private Usuario usuario;

   @ManyToOne
   @JoinColumn(name = "domicilioId")
   private Domicilio domicilio;

   @ManyToOne
   @JoinColumn(name = "clienteId")
   private Cliente cliente;

    public void mostrarPedido(){



    }
}