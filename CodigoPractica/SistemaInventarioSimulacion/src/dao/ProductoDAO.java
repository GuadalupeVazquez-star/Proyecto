package dao;
//ProductoDAO = el que habla con la base de datos DAO Data Access Object (objetos de datos de acceso)

import modelo.Producto; //importa la clase Producto para poder usarse
import java.sql.*; //importa todas las clases SQL
import java.util.ArrayList; //para usar listas dinamicas
import java.util.List; //interfaz List

public class ProductoDAO { //clase publica
    private final String URL = "jdbc:sqlite:inventario.db"; //ruta de la BD. jdbc:sqlite:, inventario.db = nombre del archivo, final = variable que no cambia
    private Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL);
    }

    public ProductoDAO() { //M constructor
        try (Connection conn = DriverManager.getConnection(URL)) { //intenta conectarse a la BD. si no existe inventario.db, lo crea
            //try = cierra la conexion solo al terminar
            Statement stmt = conn.createStatement(); //crea objeto para mandar SQL
            stmt.execute("CREATE TABLE IF NOT EXISTS productos (" +
    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    "nombre TEXT NOT NULL," +
    "stock_actual INTEGER NOT NULL," +
    "stock_minimo INTEGER NOT NULL," +
    "precio_unitario REAL NOT NULL," +
    "tiempo_entrega_dias INTEGER NOT NULL)");
                   
        } catch (SQLException e) { e.printStackTrace(); } //si falla la BD, imprime el error completo
    }

    public List<Producto> listarTodos() { //metodo que regresa lista de todos los products
        List<Producto> lista = new ArrayList<>(); //crea lista vacia
        String sql = "SELECT * FROM productos"; // query =dame todas las columnas de products
        try (Connection conn = DriverManager.getConnection(URL); //abre conexion
             Statement stmt = conn.createStatement(); //crea statement
             ResultSet rs = stmt.executeQuery(sql)) { //ejecuta query y guarda resultden rs
            
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"), rs.getString("nombre"),
                    rs.getInt("stock_actual"), rs.getInt("stock_minimo"),
                    rs.getDouble("precio_unitario"), rs.getInt("tiempo_entrega_dias")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); } //si falla imprime error
        return lista; //regresa la lista llena
    }

    public void actualizarStock(int id, int nuevoStock) { //metodo para cambiar stock en BD
        String sql = "UPDATE productos SET stock_actual = ? WHERE id = ?"; //parametros que se llenan despues
        try (Connection conn = DriverManager.getConnection(URL); //conexion
             PreparedStatement ps = conn.prepareStatement(sql)) { //PreparedStatement para usar los...
            ps.setInt(1, nuevoStock); //el primer = nuevoStock
            ps.setInt(2, id); //el segundo = id 
            ps.executeUpdate(); //ejecuta el UPDATE
        } catch (SQLException e) { e.printStackTrace(); } //el error
    }
}