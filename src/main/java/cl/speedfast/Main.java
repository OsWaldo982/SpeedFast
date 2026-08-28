package cl.speedfast;

import cl.speedfast.model.PedidoComida;
import cl.speedfast.model.PedidoEncomienda;
import cl.speedfast.model.PedidoExpress;
import cl.speedfast.service.ControladorDeEnvios;

public class Main {

    public static void main(String[] args) {
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        //Datos
        PedidoComida p201 = new PedidoComida(201, "Av. Providencia 1200", 3);
        PedidoEncomienda p202 = new PedidoEncomienda(202, "Calle los Alerces 45", 8);
        PedidoExpress p203 = new PedidoExpress(203, "Av. Las Condes 9000", 4);

        //repartidores
        p201.asignarRepartidor("Gonzalo Perez");
        p202.asignarRepartidor("Valentina Silva");
        p203.asignarRepartidor(); // Usa la asignación automática por defecto


        System.out.println("[Pedido Encomienda]");
        p202.mostrarResumen();
        p202.despachar();
        controlador.registrarPedido(p202);

        System.out.println();

        System.out.println("Cancelando Pedido Express #" + p203.getIdPedido() + "...");
        p203.cancelar();
        controlador.registrarPedido(p203);

        // Registramos el pedido de comida entregado para el historial
        p201.despachar();
        controlador.registrarPedido(p201);

        System.out.println();

        // Mostrar historial de entregas
        controlador.verHistorial();
    }
}