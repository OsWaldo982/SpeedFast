package cl.speedfast.service;

import cl.speedfast.interfaces.Rastreable;
import cl.speedfast.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene el historial final de pedidos procesados.
 */
public class ControladorDeEnvios implements Rastreable {

    private final List<Pedido> historialPedidos;

    public ControladorDeEnvios() {
        this.historialPedidos = new ArrayList<>();
    }

    public void registrarPedido(Pedido pedido) {
        if (pedido != null) {
            historialPedidos.add(pedido);
        }
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial final:");

        for (Pedido pedido : historialPedidos) {
            System.out.println(
                    "- " + pedido.getClass().getSimpleName()
                            + " #" + String.format("%03d", pedido.getIdPedido())
                            + " | " + pedido.getEstado()
                            + " | repartidor: " + pedido.getRepartidorAsignado()
            );
        }
    }
}
