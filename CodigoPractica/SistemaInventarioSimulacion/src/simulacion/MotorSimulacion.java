package simulacion; //Paquete 

import modelo.Producto; //imports
import dao.ProductoDAO;
import javax.swing.SwingWorker;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class MotorSimulacion extends SwingWorker<Void, String> { //hereda de SwingWorker. Void = no regresa nada al final, 
    private PriorityQueue<Evento> colaEventos = new PriorityQueue<>(); //cola que ordena eventos por fecha sola 
    private ProductoDAO dao = new ProductoDAO(); //para guardar en BD
    private List<Producto> productos; //lista de productos
    private GeneradorDemanda generador = new GeneradorDemanda(); //para demanda aleatoria
    private LocalDateTime tiempoActual = LocalDateTime.now(); //reloj de simulacion 
    private boolean ejecutando = true; // para parar el while
    private int velocidadMs = 500; // 0.5 seg por "día" simulado, pausa entre eventos 

    public MotorSimulacion() { //constructor 
        this.productos = dao.listarTodos(); //carga productos BD
        // Generar eventos iniciales de demanda para 30 días
        for (int dia = 0; dia < 30; dia++) { //por 30 dias
            for (Producto p : productos) { //por cada producto
                int demanda = generador.generarDemandaDiaria(); //calcula demanda aleatoria
                if (demanda > 0) { //si ahi demanda 
                    colaEventos.add(new Evento( //mete evento a la cola 
                        tiempoActual.plusDays(dia), Evento.Tipo.DEMANDA, demanda, p //evento en el dia X
                    ));
                }
            }
        }
    }

    @Override //sobrescribe metodo de SwingWorker
    protected Void doInBackground() throws Exception { // esto corre en hilo separado, no congela la UI (Interfaz de Usuario)
        while (ejecutando && !colaEventos.isEmpty()) { //mientras no se pare y haya eventos
            Evento e = colaEventos.poll(); //saca el evento con fecha mas antigua
            tiempoActual = e.getFecha(); //adelanta el reloj al tiempo del evento
            Thread.sleep(velocidadMs); //pausa para ver la simulacion en camara lenta
            
            String log = procesarEvento(e); //procesa y guarda el texto del log
            publish(log); // Manda el log a la UI, publish() = manda a process() que corre en hilo de UI
        }
        return null; //termina
    }

    private String procesarEvento(Evento e) { //logica de cada evento
        Producto p = e.getProducto(); //saca el producto del evento
        switch (e.getTipo()) { //segun el tipo 
            case DEMANDA: //si es una ventana 
                p.reducirStock(e.getCantidad()); //baja el stock
                dao.actualizarStock(p.getId(), p.getStockActual()); //guarda en BD
                if (p.necesitaReorden()) { //si ya ahy poco
                    // Generar evento de pedido
                    LocalDateTime fechaEntrega = tiempoActual.plusDays(p.getTiempoEntrega()); //calcula cuanto llega
                    colaEventos.add(new Evento(fechaEntrega, Evento.Tipo.ENTREGA, //mete evento futuro de entrega
                                    p.getStockMinimo() * 3, p)); //pide 3 vecs el minimo
                    return String.format("[%s] Venta: %d %s | Stock: %d | ¡REORDEN!", //texto para el log
                            e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual());
                }
                return String.format("[%s] Venta: %d %s | Stock: %d", //log sin reorden
                        e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual()); 
            
            case ENTREGA: //Si llego pedido
                p.agregarStock(e.getCantidad());
                dao.actualizarStock(p.getId(), p.getStockActual());
                return String.format("[%s] Llegó pedido: %d %s | Stock: %d", //log 
                        e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual());
        }
        return ""; //por si acaso
    }

    @Override //este metodo corre el hilo de Swing, 
    protected void process(List<String> logs) {
        for (String log : logs) { //por cada evento
            System.out.println(log); 
        }
    }
    
    public void detener() { ejecutando = false; } //para parar el while
    public void setVelocidad(int ms) { velocidadMs = ms; } //cambiar velocidad desde el slider
}
