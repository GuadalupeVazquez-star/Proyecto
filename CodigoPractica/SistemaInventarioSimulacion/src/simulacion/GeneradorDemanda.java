package simulacion;

import java.util.Random;

public class GeneradorDemanda {
    private Random random = new Random(12345); // Semilla fija para repetir resultados, crea generador con semillas 12345
    //semilla fija siempre da los mismos numeros aleatorios

    // Distribución normal: promedio 12 piezas/día, desviación 4
    public int generarDemandaDiaria() { //metodo
        int demanda = (int) Math.round(random.nextGaussian() * 4 + 12); //nextGaussian = normal con media 0, desv 1, *4+12 = media 12, desv 4
        return Math.max(0, demanda); // No negativos, si sale -3, regresa 0. no se vende -3 piezas
    }
}
