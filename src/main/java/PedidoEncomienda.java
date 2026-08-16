package main.java;

public class PedidoEncomienda extends Pedido {


    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor asignado: " + nombreRepartidor
                + ". Validación: peso y embalaje verificados.");
    }

}
