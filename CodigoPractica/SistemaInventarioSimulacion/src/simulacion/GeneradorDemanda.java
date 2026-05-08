package simulacion;

import java.util.Random;

public class GeneradorDemanda {
    private Random random = new Random(12345); // Semilla fija para repetir resultados

    // Distribución normal: promedio 12 piezas/día, desviación 4
    public int generarDemandaDiaria() {
        int demanda = (int) Math.round(random.nextGaussian() * 4 + 12);
        return Math.max(0, demanda); // No negativos
    }
}
