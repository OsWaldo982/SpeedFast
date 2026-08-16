package main.java;

public class Main {

    public static void main(String[] args) {

        Pedido[] pedidos = {
                new PedidoComida(),
                new PedidoEncomienda(),
                new PedidoExpress()
        };

        System.out.println("=== MÉTODOS SOBRESCRITOS ===");
        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor();
        }

        System.out.println("\n=== MÉTODOS SOBRECARGADOS ===");
        pedidos[0].asignarRepartidor("Carlos");
        pedidos[1].asignarRepartidor("María");
        pedidos[2].asignarRepartidor("Pedro");
    }
}