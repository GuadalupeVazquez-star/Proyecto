package dao;

import modelo.Producto; //importa la clase Producto para poder usarse
import java.sql.*; //importa todas las clases SQL
import java.util.ArrayList; //para usar listas dinamicas
import java.util.List; //interfaz List

public class ProductoDAO {
    private final String URL = "jdbc:sqlite:inventario.db"; //ruta de la BD. jdbc:sqlite:, final = variable que no cambia

    public ProductoDAO() { //M constructor
        try (Connection conn = DriverManager.getConnection(URL)) { //intenta conectar 
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS productos (" +
    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    "nombre TEXT NOT NULL," +
    "stock_actual INTEGER NOT NULL," +
    "stock_minimo INTEGER NOT NULL," +
    "precio_unitario REAL NOT NULL," +
    "tiempo_entrega_dias INTEGER NOT NULL)");
                   
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"), rs.getString("nombre"),
                    rs.getInt("stock_actual"), rs.getInt("stock_minimo"),
                    rs.getDouble("precio_unitario"), rs.getInt("tiempo_entrega_dias")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void actualizarStock(int id, int nuevoStock) {
        String sql = "UPDATE productos SET stock_actual = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}