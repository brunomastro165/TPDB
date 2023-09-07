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

    @OneToOne
    @JoinColumn(name="facturaId")
    private Factura factura;

    @OneToMany
    @JoinColumn(name = "pedidoId")
    private List<DetallePedido>detallePedido = new ArrayList<>();

}