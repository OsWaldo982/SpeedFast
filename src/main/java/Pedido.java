package main.java;

public class Pedido {

    private int idPedido;

    private String direccionEntrega;
    private String tipoPedido;


    public void asignarRepartidor() {

        System.out.println("se ha asignado un repartidor");

    }

    public void asignarRepartidor(String nombreRepartidor){

        System.out.println("Nombre del repartidor asignado: " + nombreRepartidor);


    }



}
