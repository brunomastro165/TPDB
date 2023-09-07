package com.example.tpdb.Entidades;

import com.example.tpdb.ENUMS.Estado;
import com.example.tpdb.ENUMS.TipoEnvio;
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
    private Estado estado;
    private String horaEstimadaEntrega;
    private TipoEnvio tipoEnvio;
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