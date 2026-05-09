package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDriver { //para checar si carga el SQLite
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC"); //intenta cargar la clase del driver
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db"); //intenta crear BD
            System.out.println("Driver SQLite 3.53.1.0 OK. Conexión creada.");
            conn.close(); //cierra conexion
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
