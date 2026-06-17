package dao;

import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    // CAMBIO 1: URL, usuario y password de MySQL
    private final String url = "jdbc:mysql://localhost:3306/inventario_simulador";
    private final String user = "root"; // usuario de MySQL
    private final String password = "98765432"; // password de MySQL

    private Connection conectar() throws SQLException {
        // CAMBIO 2: Ya no se necesita Class.forName en Java 8+
        return DriverManager.getConnection(url, user, password);
    }
    
    public void crearTabla() {
        // CAMBIO 3: MySQL usa AUTO_INCREMENT en lugar de AUTOINCREMENT
        String sql = "CREATE TABLE IF NOT EXISTS productos (" +
                     "id INT PRIMARY KEY AUTO_INCREMENT," +
                     "nombre VARCHAR(100) NOT NULL," +
                     "stock_actual INT," +
                     "stock_minimo INT," +
                     "precio_unitario Double," +
                     "tiempo_entrega INT);";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear tabla: " + e.getMessage());
        }
    }

    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = conectar(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("stock_actual"),
                    rs.getInt("stock_minimo"),
                    rs.getDouble("precio"),
                    rs.getInt("tiempo_entrega")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarStock(int id, int nuevoStock) {
        String sql = "UPDATE productos SET stock_actual = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    ProductoDAO dao = new ProductoDAO();
    List<Producto> productos = dao.listarTodos();
    for(Producto p : productos) {
        System.out.println(p.getNombre() + " - Stock: " + p.getStockActual());
    }
}
}