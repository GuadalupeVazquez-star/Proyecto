package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestDriver { //para checar si carga el SQLite
    public static void main(String[] args) {
        
        File db =new File("inventario.db");
        System.out.println("DB existe: " + db.exists());
        System.out.println("Ruta: " + db.getAbsolutePath());
        
        java.awt.EventQueue.invokeLater(new runnable(){
            public void run(){
                new TestDriver().setVisible(true);
                        
            }
        });
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

    public void setVisible(boolean b) {
        
    }

    private static class runnable implements Runnable {

        public runnable() {
        }

        @Override
        public void run() {
        }
    }
}
