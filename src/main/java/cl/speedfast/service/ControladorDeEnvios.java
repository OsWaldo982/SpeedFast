package cl.speedfast.service;

import cl.speedfast.interfaces.Rastreable;
import cl.speedfast.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Rastreable {

    private List<Pedido> historialPedidos;

    public ControladorDeEnvios() {
        this.historialPedidos = new ArrayList<>();
    }

    // Registra un pedido en la lista del sistema
    public void registrarPedido(Pedido pedido) {
        historialPedidos.add(pedido);
    }

    // Método exigido por la interfaz Rastreable
    @Override
    public void verHistorial() {
        System.out.println("Historial:");
        for (Pedido p : historialPedidos) {
            if (p.isDespachado()) {
                System.out.println("- " + p.getClass().getSimpleName() + " #" + String.format("%03d", p.getIdPedido()) +
                        " – entregado por " + p.getRepartidorAsignado());
            } else if (p.isCancelado()) {
                System.out.println("- " + p.getClass().getSimpleName() + " #" + String.format("%03d", p.getIdPedido()) +
                        " – CANCELADO");
            }
        }
    }
}