package com.example.tpdb;

import com.example.tpdb.ENUMS.Estado;
import com.example.tpdb.ENUMS.FormaPago;
import com.example.tpdb.ENUMS.Tipo;
import com.example.tpdb.ENUMS.TipoEnvio;
import com.example.tpdb.Entidades.*;
import com.example.tpdb.Repositorios.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
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

           Factura factura = Factura.builder()
                   .total(800)
                   .numero(444567)
                   .fecha("7/9/2023")
                   .formaPago(FormaPago.EFECTIVO)
                   .build();
           facturaRepositorio.save(factura);

           Producto producto = Producto.builder()
                   .denominacion("Hamburguesa")
                   .precioVenta(500.5)
                   .precioCompra(200.0)
                   .foto("IMAGEN")
                   .receta("Carne | Pan | Lechuga")
                   .stockActual(14)
                   .stockMinimo(1)
                   .tiempoEstimadoCocina(15)
                   .tipo(Tipo.MANUFACTURADO)
                   .unidadMedida("kg")
                   .build();
           productoRepositorio.save(producto);

            DetallePedido detallePedido = DetallePedido.builder()
                    .producto(producto)
                    .cantidad(1)
                    .subtotal(500.0)
                    .producto(producto)
                    .build();
            detallePedidoRepositorio.save(detallePedido);

            Rubro rubro = Rubro.builder()
                    .denominacion("Comida")
                    .productos(new ArrayList<>())
                    .build();
            rubro.getProductos().add(producto);
            rubroRepositorio.save(rubro);

            Pedido pedido = Pedido.builder()
                    .Fecha("7/9/23")
                    .estado(Estado.PREPARACION)
                    .total(1.0)
                    .tipoEnvio(TipoEnvio.DELIVERY)
                    .horaEstimadaEntrega("2")
                    .factura(factura)
                    .detallePedido(new ArrayList<>())
                    .build();
            pedido.getDetallePedido().add(detallePedido);
            pedidoRepositorio.save(pedido);

            Usuario usuario = Usuario.builder()
                    .rol("Rol")
                    .password("******")
                    .nombre("Bruno")
                    .pedidos(new ArrayList<>())
                    .build();
            usuario.getPedidos().add(pedido);
            usuarioRepositorio.save(usuario);

            Cliente cliente = Cliente.builder()
                    .apellido("Mastropietro")
                    .nombre("Bruno")
                    .email("brunomastro165")
                    .telefono("2613998137")
                    .pedidos(new ArrayList<>())
                    .build();
            cliente.getPedidos().add(pedido);
            clienteRepositorio.save(cliente);

            //Añadimos el objeto rubro a producto
            Domicilio domicilio = Domicilio.builder()
                    .calle("Calle")
                    .localidad("Mendoza")
                    .numero("1234")
                    .pedidos(new ArrayList<>())
                    .cliente(cliente)
                    .build();
            domicilio.getPedidos().add(pedido);
            domicilioRepositorio.save(domicilio);

            System.out.println("Escenario de creación de datos de prueba completado.");
        };
    }
}
