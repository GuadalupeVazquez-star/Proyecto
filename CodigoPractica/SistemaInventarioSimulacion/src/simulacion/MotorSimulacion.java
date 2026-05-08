package simulacion;

import modelo.Producto;
import dao.ProductoDAO;
import javax.swing.SwingWorker;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class MotorSimulacion extends SwingWorker<Void, String> {
    private PriorityQueue<Evento> colaEventos = new PriorityQueue<>();
    private ProductoDAO dao = new ProductoDAO();
    private List<Producto> productos;
    private GeneradorDemanda generador = new GeneradorDemanda();
    private LocalDateTime tiempoActual = LocalDateTime.now();
    private boolean ejecutando = true;
    private int velocidadMs = 500; // 0.5 seg por "día" simulado

    public MotorSimulacion() {
        this.productos = dao.listarTodos();
        // Generar eventos iniciales de demanda para 30 días
        for (int dia = 0; dia < 30; dia++) {
            for (Producto p : productos) {
                int demanda = generador.generarDemandaDiaria();
                if (demanda > 0) {
                    colaEventos.add(new Evento(
                        tiempoActual.plusDays(dia), Evento.Tipo.DEMANDA, demanda, p
                    ));
                }
            }
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (ejecutando && !colaEventos.isEmpty()) {
            Evento e = colaEventos.poll();
            tiempoActual = e.getFecha();
            Thread.sleep(velocidadMs);
            
            String log = procesarEvento(e);
            publish(log); // Manda el log a la UI
        }
        return null;
    }

    private String procesarEvento(Evento e) {
        Producto p = e.getProducto();
        switch (e.getTipo()) {
            case DEMANDA:
                p.reducirStock(e.getCantidad());
                dao.actualizarStock(p.getId(), p.getStockActual());
                if (p.necesitaReorden()) {
                    // Generar evento de pedido
                    LocalDateTime fechaEntrega = tiempoActual.plusDays(p.getTiempoEntrega());
                    colaEventos.add(new Evento(fechaEntrega, Evento.Tipo.ENTREGA, 
                                    p.getStockMinimo() * 3, p));
                    return String.format("[%s] Venta: %d %s | Stock: %d | ¡REORDEN!", 
                            e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual());
                }
                return String.format("[%s] Venta: %d %s | Stock: %d", 
                        e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual());
            
            case ENTREGA:
                p.agregarStock(e.getCantidad());
                dao.actualizarStock(p.getId(), p.getStockActual());
                return String.format("[%s] Llegó pedido: %d %s | Stock: %d", 
                        e.getFecha().toLocalDate(), e.getCantidad(), p.getNombre(), p.getStockActual());
        }
        return "";
    }

    @Override
    protected void process(List<String> logs) {
        for (String log : logs) {
            System.out.println(log); 
        }
    }
    
    public void detener() { ejecutando = false; }
    public void setVelocidad(int ms) { velocidadMs = ms; }
}
