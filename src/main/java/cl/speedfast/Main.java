package cl.speedfast;

import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.PedidoComida;
import cl.speedfast.model.PedidoEncomienda;
import cl.speedfast.model.PedidoExpress;
import cl.speedfast.model.Repartidor;
import cl.speedfast.service.ControladorDeEnvios;
import cl.speedfast.service.ZonaDeCarga;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println(" SPEEDFAST - SEMANA 5 | SINCRONIZACION");
        System.out.println();

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Se conservan distintos tipos de pedido del proyecto de semanas anteriores.
        List<Pedido> pedidos = List.of(
                new PedidoComida(201, "Av. Providencia 1200", 3),
                new PedidoEncomienda(202, "Calle Los Alerces 45", 8),
                new PedidoExpress(203, "Av. Las Condes 9000", 4),
                new PedidoComida(204, "Av. Irarrazaval 1500", 5),
                new PedidoEncomienda(205, "Calle Moneda 850", 7),
                new PedidoExpress(206, "Av. Vitacura 3200", 2)
        );

        System.out.println("Cargando pedidos en la zona compartida...");
        for (Pedido pedido : pedidos) {
            zonaDeCarga.agregarPedido(pedido);
        }

        System.out.println();
        System.out.println("Iniciando repartidores en paralelo...");
        System.out.println();

        // Los tres repartidores comparten exactamente la misma ZonaDeCarga.
        Repartidor gonzalo = new Repartidor("Gonzalo Perez", zonaDeCarga);
        Repartidor valentina = new Repartidor("Valentina Silva", zonaDeCarga);
        Repartidor camila = new Repartidor("Camila Soto", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(gonzalo);
        executor.execute(valentina);
        executor.execute(camila);
        executor.shutdown();

        boolean finalizaron = esperarFinalizacion(executor);

        if (!finalizaron) {
            System.out.println("No fue posible finalizar todas las entregas dentro del tiempo esperado.");
            return;
        }

        // Verificacion final de integridad del proceso.
        boolean todosEntregados = pedidos.stream()
                .allMatch(pedido -> pedido.getEstado() == EstadoPedido.ENTREGADO);

        System.out.println();
        if (todosEntregados && zonaDeCarga.estaVacia()) {
            System.out.println("Todos los pedidos han sido entregados correctamente");
        } else {
            System.out.println("Advertencia: quedaron pedidos sin entregar.");
        }

        System.out.println();
        pedidos.forEach(controlador::registrarPedido);
        controlador.verHistorial();
    }

    private static boolean esperarFinalizacion(ExecutorService executor) {
        try {
            boolean finalizaron = executor.awaitTermination(1, TimeUnit.MINUTES);

            if (!finalizaron) {
                executor.shutdownNow();
            }

            return finalizaron;

        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido mientras esperaba a los repartidores.");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
