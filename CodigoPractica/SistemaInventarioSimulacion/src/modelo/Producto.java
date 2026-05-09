package modelo; 

public class Producto { // public = cualquiera puede usar esta clase desde otros paquetes
    
    //private = solo esta clase puede tocar estas variables directo (es Encapsulamiento)
    private int id, stockActual, stockMinimo, tiempoEntrega; //variables enteras
    private String nombre; //variable de texto para el nombre
    private double precio; //variable decimal para precios con centavos
    
    
//metodo constructor = metodo que se llama cuando se hace new Producto, inicializa el objeto
    public Producto(int id, String nombre, int stockActual, int stockMinimo, double precio, int tiempoEntrega) {
        this.id = id; //this.id es la variable de arriba, id el parametro
        this.nombre = nombre; //asigna el nombre a la variable
        this.stockActual = stockActual; //guarda el stock inicial
        this.stockMinimo = stockMinimo; //guarda el punto de reorden
        this.precio = precio; //guarda el precio
        this.tiempoEntrega = tiempoEntrega; //guarda dias que tarda en llegar el pedido
    }

    //(bolean = regresa true o false, si stock actual es 5 y stockMinimo es 10 = true)
    //(Math.max(0,x) = si x es negativo, regresa 0. Evita stock -5)
    //(+= = sumale cantidad a stockActual
    public boolean necesitaReorden() { return stockActual <= stockMinimo; } //metodo que regresa true si ya ahi poco stock
    public void reducirStock(int cantidad) { stockActual = Math.max(0, stockActual - cantidad); } //resta stock pero no baja de 0
    public void agregarStock(int cantidad) { stockActual += cantidad; } //suma stock cuando llega pedido

    // Getters y setters (get=leer, set=escibir)
    public int getId() { return id; } //metodo para leer el id desde afuera
    public String getNombre() { return nombre; } //para leer nombre
    public int getStockActual() { return stockActual; } //para leer stock
    public int getStockMinimo() { return stockMinimo; } //para leer minimo
    public double getPrecio() { return precio; } //para leer precio
    public int getTiempoEntrega() { return tiempoEntrega; } //para leer dias de entrega
    public void setStockActual(int stockActual) { this.stockActual = stockActual; } //para cambiar stock desde afuera
}
