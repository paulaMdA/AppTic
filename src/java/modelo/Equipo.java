package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String idEquipo;
    private String marca;
    private String modelo;
    private String descripcion;
    private String aula;
    private String estado;

    // Constructor vacío
    public Equipo() {
    }

    // Constructor con todos los campos
    public Equipo(String idEquipo, String marca, String modelo, String descripcion, String aula) {
        this.idEquipo = idEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.aula = aula;
    }

      // Constructor con todos los campos
    public Equipo(String idEquipo, String marca, String modelo, String descripcion, String aula, String estado) {
        this.idEquipo = idEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.aula = aula;
        this.estado = estado;
    }

    // Getters y setters
    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para generar el idEquipo automáticamente
    public static String generarIdEquipo(Connection conn, String aula) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Equipos WHERE aula = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, aula);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1) + 1;
                    return aula + count;
                } else {
                    throw new SQLException("Error al generar el id del equipo");
                }
            }
        }
    }

    // Método para insertar un equipo en la base de datos
    public void insertarEquipo(Connection conn) throws SQLException {
        String sql = "INSERT INTO Equipos (idEquipo, marca, modelo, descripcion, aula, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, this.idEquipo);
            statement.setString(2, this.marca);
            statement.setString(3, this.modelo);
            statement.setString(4, this.descripcion);
            statement.setString(5, this.aula);
            statement.setString(6, this.estado);
            statement.executeUpdate();
        }
    }

    public static void cambiarEstadoEquipo(Connection conn, String idEquipo, String estado) throws SQLException {
        String sql = "UPDATE Equipos SET estado = ? WHERE idEquipo = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado);
            statement.setString(2, idEquipo);
            statement.executeUpdate();
        }
    }

    public static List<Equipo> obtenerTodosLosEquipos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Equipos WHERE estado = 'activo'";
        List<Equipo> equipos = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getString("idEquipo"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setAula(rs.getString("aula"));
                equipo.setEstado(rs.getString("estado"));
                equipos.add(equipo);
            }
        }

        return equipos;
    }

    // Método para obtener equipos sin incidencias pendientes
    public static List<Equipo> obtenerEquiposDisponibles(Connection conn) throws SQLException {
        String sql = "SELECT e.* FROM Equipos e LEFT JOIN Incidencias i ON e.idEquipo = i.idEquipo AND i.estado = 'pendiente' WHERE i.idEquipo IS NULL AND e.estado = 'activo'";
        List<Equipo> equiposDisponibles = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getString("idEquipo"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setAula(rs.getString("aula"));
                equiposDisponibles.add(equipo);
            }
        }

        return equiposDisponibles;
    }

    // Método para actualizar un equipo en la base de datos
    public static void actualizarEquipo(Connection conn, String idEquipo, String marca, String modelo, String descripcion, String aula) throws SQLException {
        String sql = "UPDATE Equipos SET marca = ?, modelo = ?, descripcion = ?, aula = ? WHERE idEquipo = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, descripcion);
            statement.setString(4, aula);
            statement.setString(5, idEquipo);
            statement.executeUpdate();
        }
    }

    // Método para eliminar un equipo en la base de datos (no necesario si se cambia el estado)
    public static void eliminarEquipo(Connection conn, String idEquipo) throws SQLException {
        cambiarEstadoEquipo(conn, idEquipo, "inactivo");
    }
}