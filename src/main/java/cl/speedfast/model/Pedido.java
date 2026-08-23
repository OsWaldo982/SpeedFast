package cl.speedfast.model;

/**
 * Clase base que representa los datos y comportamientos comunes de un pedido
 */
public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private int distanciaKm;

    public Pedido(int idPedido, String direccionEntrega, int distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }

    public void mostrarResumen() {
        System.out.println(getClass().getSimpleName() + " #" + String.format("%03d", idPedido));
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }


    public abstract int calcularTiempoEntrega();

}
