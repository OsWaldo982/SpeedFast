package cl.speedfast;

import cl.speedfast.model.Pedido;
import cl.speedfast.model.PedidoComida;
import cl.speedfast.model.PedidoEncomienda;
import cl.speedfast.model.PedidoExpress;

public class Main {

    public static void main(String[] args) {
        // No se puede crear new Pedido() porque Pedido es una clase abstracta
        Pedido[] pedidos = {
                new PedidoComida(1, "Av. Italia 456", 4),
                new PedidoEncomienda(2, "Av. Independencia 123", 6),
                new PedidoExpress(3, "Av. Apoquindo 1500", 7)
        };

        System.out.println("=== TIEMPOS ESTIMADOS DE ENTREGA ===");
        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
            System.out.println("Tiempo estimado de entrega: "
                    + pedido.calcularTiempoEntrega() + " minutos");
            System.out.println();
        }
    }
}
