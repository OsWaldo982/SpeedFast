package cl.speedfast.model;

import cl.speedfast.service.ZonaDeCarga;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Tarea concurrente que consume pedidos desde una ZonaDeCarga compartida.
 */
public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del repartidor es obligatorio.");
        }
        if (zonaDeCarga == null) {
            throw new IllegalArgumentException("La zona de carga es obligatoria.");
        }

        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        System.out.println("[Repartidor - " + nombre + "] Iniciando jornada.");

        while (!Thread.currentThread().isInterrupted()) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            pedido.asignarRepartidor(nombre);

            try {
                System.out.println(
                        "[Repartidor - " + nombre + "] Retirando pedido #"
                                + pedido.getIdPedido() + "..."
                );
                System.out.println(
                        "[Repartidor - " + nombre + "] Estado: " + pedido.getEstado()
                );
                System.out.println(
                        "[Repartidor - " + nombre + "] Entregando pedido #"
                                + pedido.getIdPedido() + "..."
                );

                // Simula una entrega entre 1 y 3 segundos.
                int tiempoEntrega = ThreadLocalRandom.current().nextInt(1000, 3001);
                Thread.sleep(tiempoEntrega);

                pedido.despachar();

                System.out.println(
                        "[Repartidor - " + nombre + "] Pedido #"
                                + pedido.getIdPedido() + " entregado."
                );
                System.out.println(
                        "[Repartidor - " + nombre + "] Estado: " + pedido.getEstado()
                );

            } catch (InterruptedException e) {
                System.out.println(
                        "[Repartidor - " + nombre + "] Proceso interrumpido durante la entrega del pedido #"
                                + pedido.getIdPedido() + "."
                );
                Thread.currentThread().interrupt();
                return;
            } catch (RuntimeException e) {
                System.out.println(
                        "[Repartidor - " + nombre + "] Error con pedido #"
                                + pedido.getIdPedido() + ": " + e.getMessage()
                );
            }
        }

        System.out.println("[Repartidor - " + nombre + "] No quedan pedidos. Jornada finalizada.");
    }
}
