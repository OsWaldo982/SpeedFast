package main.java;

public class PedidoComida extends Pedido{


    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor asignado: " + nombreRepartidor
                + ". Validación: debe contar con mochila térmica.");
    }

}
