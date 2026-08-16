package main.java;

public class PedidoExpress extends Pedido{



    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor asignado: " + nombreRepartidor
                + ". Validación: disponibilidad inmediata y cercanía.");
    }

}
