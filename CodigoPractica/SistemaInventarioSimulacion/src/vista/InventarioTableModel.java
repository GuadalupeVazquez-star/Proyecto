package vista;

import modelo.Producto;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InventarioTableModel extends AbstractTableModel {
    private final String[] columnas = {"ID", "Producto", "Stock", "Mínimo", "Estado"};
    private List<Producto> productos = new ArrayList<>();

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public void actualizarProducto(Producto p) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == p.getId()) {
                productos.set(i, p);
                fireTableRowsUpdated(i, i);
                return;
            }
        }
    }

    @Override public int getRowCount() { return productos.size(); }
    @Override public int getColumnCount() { return columnas.length; }
    @Override public String getColumnName(int col) { return columnas[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Producto p = productos.get(row);
        switch (col) {
            case 0: return p.getId();
            case 1: return p.getNombre();
            case 2: return p.getStockActual();
            case 3: return p.getStockMinimo();
            case 4: return p.necesitaReorden()? "REORDENAR" : "OK";
            default: return "";
        }
    }
}