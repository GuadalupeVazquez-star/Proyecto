package simulacion;

import modelo.Producto;
import java.time.LocalDateTime;

public class Evento implements Comparable<Evento> {
    public enum Tipo { DEMANDA, PEDIDO_REALIZADO, ENTREGA }
    
    private LocalDateTime fecha;
    private Tipo tipo;
    private int cantidad;
    private Producto producto;

    public Evento(LocalDateTime fecha, Tipo tipo, int cantidad, Producto producto) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    @Override
    public int compareTo(Evento otro) { 
        return this.fecha.compareTo(otro.fecha); 
    }
    
    // Getters
    public LocalDateTime getFecha() { return fecha; }
    public Tipo getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public Producto getProducto() { return producto; }
}
