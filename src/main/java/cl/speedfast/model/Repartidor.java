package cl.speedfast.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidosAsignados;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.pedidosAsignados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPedido(Pedido pedido) {
        pedidosAsignados.add(pedido);
    }

    @Override
    public void run() {

        System.out.println(
                "[Repartidor: " + nombre + "] Iniciando entregas."
        );

        for (Pedido pedido : pedidosAsignados) {

            try {

                System.out.println(
                        "[Repartidor: " + nombre + "] Entregando "
                                + pedido.getClass().getSimpleName()
                                + " #" + pedido.getIdPedido() + "..."
                );

                // Tiempo aleatorio entre 1 y 3 segundos
                int tiempoEspera =
                        ThreadLocalRandom.current().nextInt(1000, 3001);


                // Realiza el despacho del pedido
                Thread.sleep(tiempoEspera);

                pedido.despachar();

                System.out.println(
                        "[Repartidor: " + nombre + "] Pedido #"
                                + pedido.getIdPedido()
                                + " entregado."
                );

            } catch (InterruptedException e) {

                System.out.println(
                        "[Repartidor: " + nombre
                                + "] Entrega interrumpida."
                );

                Thread.currentThread().interrupt();
                return;

            } catch (Exception e) {

                System.out.println(
                        "[Repartidor: " + nombre
                                + "] Error al procesar el pedido #"
                                + pedido.getIdPedido()
                );
            }
        }

        System.out.println(
                "[Repartidor: " + nombre
                        + "] Finalizó todas sus entregas."
        );
    }
}