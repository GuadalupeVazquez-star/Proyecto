package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Driver SQLite 3.53.1.0 OK. Conexión creada.");
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
