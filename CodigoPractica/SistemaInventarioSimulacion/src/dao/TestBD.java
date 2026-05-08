package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestBD {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:inventario.db";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            // 1. Crear la tabla si no existe
            String sqlTabla = "CREATE TABLE IF NOT EXISTS productos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "stock_actual INTEGER NOT NULL," +
                "stock_minimo INTEGER NOT NULL," +
                "precio_unitario REAL NOT NULL," +
                "tiempo_entrega_dias INTEGER NOT NULL)";
            stmt.execute(sqlTabla);
            System.out.println("Tabla creada.");
            
            // 2. Borrar datos viejos por si se corre 2 veces
            stmt.execute("DELETE FROM productos");
            
            // 3. Insertar los 3 productos
            stmt.execute("INSERT INTO productos (nombre, stock_actual, stock_minimo, precio_unitario, tiempo_entrega_dias) " +
                         "VALUES ('Mouse Logitech', 50, 10, 299.99, 3)");
            
            stmt.execute("INSERT INTO productos (nombre, stock_actual, stock_minimo, precio_unitario, tiempo_entrega_dias) " +
                         "VALUES ('Teclado Mecánico', 30, 8, 899.00, 5)");
            
            stmt.execute("INSERT INTO productos (nombre, stock_actual, stock_minimo, precio_unitario, tiempo_entrega_dias) " +
                         "VALUES ('Monitor 24\"', 15, 5, 3200.00, 7)");
            
            System.out.println("3 productos insertados correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}