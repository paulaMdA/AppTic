package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private String servidor;
    private String database;
    private String usuario;
    private String password;
    private String url;
    private Connection conexion;

    public ConexionBD(String servidor, String database, String usuario, String password) {
        try {
            this.servidor = servidor;
            this.database = database;
            this.usuario = usuario;
            this.password = password;
            this.url = "jdbc:mysql://" + servidor + "/" + database;
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión con la base de datos " + url + "...Ok");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al registrar el controlador de MySQL", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al conectar con la base de datos", ex);
        }
    }

    public Connection getConnection() {
        return conexion;
    }

    public void close() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
