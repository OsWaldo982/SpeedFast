package cl.speedfast.model;

import cl.speedfast.interfaces.Cancelable;
import cl.speedfast.interfaces.Despachable;

/**
 * Clase base que representa los datos y comportamientos comunes de un pedido
 */
public abstract class Pedido implements Despachable, Cancelable {

    private int idPedido;
    private String direccionEntrega;
    private int distanciaKm;
    protected String repartidorAsignado;
    protected boolean cancelado;
    protected boolean despachado;

    public Pedido(int idPedido, String direccionEntrega, int distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.repartidorAsignado = "Sin asignar";
        this.cancelado = false;
        this.despachado = false;
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

    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public boolean isDespachado() {
        return despachado;
    }

    // --- POLIMORFISMO POR SOBRECARGA ---
    // por defecto
    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor Automático";
    }

    // manual recibiendo el nombre
    public void asignarRepartidor(String nombreRepartidor) {
        this.repartidorAsignado = nombreRepartidor;
    }



    public void mostrarResumen() {
        System.out.println("[" + getClass().getSimpleName() + "]");
        System.out.println("Pedido #" + String.format("%03d", idPedido));
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidorAsignado);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " minutos");
    }

    //IMPLEMENTACIÓN DE INTERFACES
    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println("No se puede despachar. El pedido #" + idPedido + " fue cancelado.");
        } else {
            this.despachado = true;
            System.out.println("Pedido despachado correctamente.");
        }
    }

    @Override
    public void cancelar() {
        if (despachado) {
            System.out.println("No se puede cancelar el pedido #" + idPedido + " porque ya fue despachado.");
        } else {
            this.cancelado = true;
            System.out.println("→ Pedido cancelado exitosamente.");
        }
    }


    public abstract int calcularTiempoEntrega();
}