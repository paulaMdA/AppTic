package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Incidencias {
    private int idIncidencia;
    private int idUsuario;
    private String idEquipo;
    private String estado;
    private String prioridad;
    private String descripcion;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;

    // Constructor vacío
    public Incidencias() {
    }

    // Constructor con todos los campos
    public Incidencias(int idUsuario, String idEquipo, String estado, String prioridad, String descripcion, Timestamp fechaInicio, Timestamp fechaFin) {
        this.idUsuario = idUsuario;
        this.idEquipo = idEquipo;
        this.estado = estado;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y setters
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Método para insertar una incidencia en la base de datos
    public void insertarIncidencia(Connection conn) throws SQLException {
        String sql = "INSERT INTO Incidencias (idUsuario, idEquipo, estado, prioridad, descripcion, fechaInicio) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, this.idUsuario);
            statement.setString(2, this.idEquipo);
            statement.setString(3, this.estado);
            statement.setString(4, this.prioridad);
            statement.setString(5, this.descripcion);
            statement.setTimestamp(6, this.fechaInicio);

            statement.executeUpdate();
        }
    }

    // Método para obtener todas las incidencias de la base de datos
    public static List<Incidencias> obtenerTodasLasIncidencias(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Incidencias";
        List<Incidencias> incidencias = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Incidencias incidencia = new Incidencias();
                incidencia.setIdIncidencia(rs.getInt("idIncidencia"));
                incidencia.setIdUsuario(rs.getInt("idUsuario"));
                incidencia.setIdEquipo(rs.getString("idEquipo"));
                incidencia.setEstado(rs.getString("estado"));
                incidencia.setPrioridad(rs.getString("prioridad"));
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setFechaInicio(rs.getTimestamp("fechaInicio"));
                incidencia.setFechaFin(rs.getTimestamp("fechaFin"));
                incidencias.add(incidencia);
            }
        }

        return incidencias;
    }

    // Método para actualizar el estado de una incidencia en la base de datos
    public static void actualizarEstadoIncidencia(Connection conn, int idIncidencia, String nuevoEstado) throws SQLException {
        String sql = "UPDATE Incidencias SET estado = ?, fechaFin = ? WHERE idIncidencia = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nuevoEstado);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, idIncidencia);

            statement.executeUpdate();
        }
    }
    
        public static List<Incidencias> obtenerIncidenciasPorUsuario(Connection conn, int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Incidencias WHERE idUsuario = ?";
        List<Incidencias> incidencias = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Incidencias incidencia = new Incidencias();
                    incidencia.setIdIncidencia(rs.getInt("idIncidencia"));
                    incidencia.setIdUsuario(rs.getInt("idUsuario"));
                    incidencia.setIdEquipo(rs.getString("idEquipo"));
                    incidencia.setEstado(rs.getString("estado"));
                    incidencia.setPrioridad(rs.getString("prioridad"));
                    incidencia.setDescripcion(rs.getString("descripcion"));
                    incidencia.setFechaInicio(rs.getTimestamp("fechaInicio"));
                    incidencia.setFechaFin(rs.getTimestamp("fechaFin"));
                    incidencias.add(incidencia);
                }
            }
        }

        return incidencias;
    }
}
