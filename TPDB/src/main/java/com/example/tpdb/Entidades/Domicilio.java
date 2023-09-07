package com.example.tpdb.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Domicilio extends ClaseBase{
    private String calle;
    private String numero;
    private String localidad;

    @OneToMany
    @JoinColumn(name = "domicilioId")
    private List<Pedido> pedidos=new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "domicilioId")
    private List<Cliente> clientes = new ArrayList<>();

}
