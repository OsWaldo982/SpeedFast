package cl.speedfast;

import cl.speedfast.model.PedidoComida;
import cl.speedfast.model.PedidoEncomienda;
import cl.speedfast.model.PedidoExpress;
import cl.speedfast.model.Repartidor;
import cl.speedfast.service.ControladorDeEnvios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // PEDIDOS

        PedidoComida p201 =
                new PedidoComida(201, "Av. Providencia 1200", 3);

        PedidoEncomienda p202 =
                new PedidoEncomienda(202, "Calle Los Alerces 45", 8);

        PedidoExpress p203 =
                new PedidoExpress(203, "Av. Las Condes 9000", 4);

        PedidoComida p204 =
                new PedidoComida(204, "Av. Irarrazaval 1500", 5);

        PedidoEncomienda p205 =
                new PedidoEncomienda(205, "Calle Moneda 850", 7);

        PedidoExpress p206 =
                new PedidoExpress(206, "Av. Vitacura 3200", 2);


        // REPARTIDORES

        Repartidor gonzalo =
                new Repartidor("Gonzalo Perez");

        Repartidor valentina =
                new Repartidor("Valentina Silva");

        Repartidor camila =
                new Repartidor("Camila Soto");


        // ASIGNACION DE REPARTIDORES

        p201.asignarRepartidor("Gonzalo Perez");
        p202.asignarRepartidor("Gonzalo Perez");

        p203.asignarRepartidor("Valentina Silva");
        p204.asignarRepartidor("Valentina Silva");

        p205.asignarRepartidor("Camila Soto");
        p206.asignarRepartidor("Camila Soto");


        // ASIGNACION DE PEDIDOS

        gonzalo.agregarPedido(p201);
        gonzalo.agregarPedido(p202);

        valentina.agregarPedido(p203);
        valentina.agregarPedido(p204);

        camila.agregarPedido(p205);
        camila.agregarPedido(p206);


        // CONCURRENCIA

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        System.out.println();
        System.out.println("Iniciando entregas...");
        System.out.println();

        executor.execute(gonzalo);
        executor.execute(valentina);
        executor.execute(camila);

        // No se aceptan nuevas tareas
        executor.shutdown();


        // ESPERAR QUE TODOS TERMINEN

        try {

            boolean finalizaron =
                    executor.awaitTermination(
                            1,
                            TimeUnit.MINUTES
                    );

            if (finalizaron) {

                System.out.println();
                System.out.println("=======================================");
                System.out.println(" TODAS LAS ENTREGAS HAN FINALIZADO");
                System.out.println("=======================================");

                // Registrar pedidos
                controlador.registrarPedido(p201);
                controlador.registrarPedido(p202);
                controlador.registrarPedido(p203);
                controlador.registrarPedido(p204);
                controlador.registrarPedido(p205);
                controlador.registrarPedido(p206);

            } else {

                System.out.println(
                        "Se supero el tiempo maximo de espera."
                );

                executor.shutdownNow();
            }

        } catch (InterruptedException e) {

            System.out.println(
                    "La ejecucion principal fue interrumpida."
            );

            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }


        // HISTORIAL

        System.out.println();
        controlador.verHistorial();
    }
}