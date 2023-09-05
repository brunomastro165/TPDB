package com.example.tpdb;

import com.example.tpdb.Entidades.*;
import com.example.tpdb.Repositorios.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class TpbdApplication {

    @Autowired
    PedidoR pedidoRepositorio;

    @Autowired
    UsuarioR usuarioRepositorio;

    @Autowired
    ClienteR clienteRepositorio;

    @Autowired
    DetallePedidoR detallePedidoRepositorio;

    @Autowired
    DomicilioR domicilioRepositorio;

    @Autowired
    FacturaR facturaRepositorio;

    @Autowired
    ProductoR productoRepositorio;

    @Autowired
    RubroR rubroRepositorio;


    public static void main(String[] args) {
        SpringApplication.run(TpbdApplication.class, args);
    }

    @Bean
    CommandLineRunner demo() {
        return args -> {

            //Patrón de diseño builder
            Rubro rubro = Rubro.builder()
                    .denominacion("Comida")
                    .build();
            rubroRepositorio.save(rubro);

            Usuario usuario = Usuario.builder()
                    .rol("Rol")
                    .password("******")
                    .nombre("Bruno")
                    .build();
            usuarioRepositorio.save(usuario);

            //Añadimos el objeto rubro a producto
            Producto producto = Producto.builder()
                    .denominacion("Hamburguesa")
                    .precioVenta(500.5)
                    .precioCompra(200.0)
                    .foto("IMAGEN")
                    .rubro(rubro)
                    .receta("Carne | Pan | Lechuga")
                    .stockActual(14)
                    .stockMinimo(1)
                    .tiempoEstimadoCocina(15)
                    .tipo("Chatarra")
                    .unidadMedida("kg")
                    .build();
            productoRepositorio.save(producto);

            Cliente cliente = Cliente.builder()
                    .apellido("Mastropietro")
                    .nombre("Bruno")
                    .email("brunomastro165")
                    .telefono("2613998137")
                    .domicilios(new ArrayList<Domicilio>())
                    .build();
            clienteRepositorio.save(cliente);

            Domicilio domicilio = Domicilio.builder()
                    .calle("Calle")
                    .clientes(new ArrayList<Cliente>())
                    .localidad("Mendoza")
                    .numero("1234")
                    .pedidos(new ArrayList<Pedido>())
                    .build();
            domicilioRepositorio.save(domicilio);

            Pedido pedido = Pedido.builder()
                    .Fecha("5/9/23")
                    .estado("Actual")
                    .total(1.0)
                    .tipoEnvio("Retiro en local")
                    .horaEstimadaEntrega("2")
                    .cliente(cliente)
                    .domicilio(domicilio)
                    .usuario(usuario)
                    .build();
            pedidoRepositorio.save(pedido);

            DetallePedido detallePedido = DetallePedido.builder()
                    .producto(producto)
                    .cantidad(1)
                    .subtotal(500.0)
                    .pedido(pedido)
                    .build();
            detallePedidoRepositorio.save(detallePedido);


            Factura factura = Factura.builder()
                    .descuento(100)
                    .fecha("5/9/23")
                    .formaPago("Efectivo")
                    .numero(1)
                    .pedido(pedido)
                    .total(400)
                    .build();
            facturaRepositorio.save(factura);




            System.out.println("Escenario de creación de datos de prueba completado.");
        };
    }
}
