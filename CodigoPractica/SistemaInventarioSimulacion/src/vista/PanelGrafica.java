package vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Date;

public class PanelGrafica extends JPanel {
    private TimeSeriesCollection dataset = new TimeSeriesCollection();
    private JFreeChart chart;

    public PanelGrafica() {
        setLayout(new BorderLayout());
        chart = ChartFactory.createTimeSeriesChart(
            "Nivel de Inventario vs Tiempo", "Fecha", "Stock", dataset);
        add(new ChartPanel(chart), BorderLayout.CENTER);
    }

    public void agregarSerie(String nombreProducto) {
        TimeSeries serie = new TimeSeries(nombreProducto);
        dataset.addSeries(serie);
    }

    public void agregarPunto(String nombreProducto, Date fecha, int stock) {
        TimeSeries serie = dataset.getSeries(nombreProducto);
        if (serie!= null) {
            serie.addOrUpdate(new Day(fecha), stock);
        }
    }
}