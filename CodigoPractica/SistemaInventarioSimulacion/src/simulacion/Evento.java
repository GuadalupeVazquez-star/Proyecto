package simulacion;

import modelo.Producto; //usa producto
import java.time.LocalDateTime; //para las fechas con hora

public class Evento implements Comparable<Evento> { //implements Comparable = se puede ordenar por fechas
    public enum Tipo { DEMANDA, PEDIDO_REALIZADO, ENTREGA } //enum = lista de valores fijos, solo usar esos 3 tipos 
    
    private LocalDateTime fecha; //cuando ocurre el evento
    private Tipo tipo; //que tipo de evento es
    private int cantidad; //cuantas oiezas 
    private Producto producto; //sobre que producto

    public Evento(LocalDateTime fecha, Tipo tipo, int cantidad, Producto producto) { //M Constructor
        this.fecha = fecha; 
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    @Override //le dice a java que se sobreescribe el metodo CcompareTo de comparable
    public int compareTo(Evento otro) { //metodo para ordenar P
        return this.fecha.compareTo(otro.fecha); //compara fechas. si this fecha es antes, regresa negativo
    }
    
    // Getters //para leer los datos desde afuera
    public LocalDateTime getFecha() { return fecha; }
    public Tipo getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public Producto getProducto() { return producto; }
}
