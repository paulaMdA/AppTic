package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.HashUtil;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private String rol;
    private String estado;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(int idUsuario, String nombre, String apellido, String password, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }
    
     public Usuario(int idUsuario, String nombre, String apellido, String password, String email, String rol, String estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;  // Inicializar nuevo campo
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
       public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void insertarUsuario(Connection conn) throws SQLException {
        String sql = "INSERT INTO Usuarios (nombre, apellido, password, email, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, this.nombre);
            statement.setString(2, this.apellido);
            statement.setString(3, this.password); // La contraseña ya está hasheada
            statement.setString(4, this.email);
            statement.setString(5, this.rol);
            statement.executeUpdate();
        }
    }

    public static Usuario autenticarUsuario(Connection conn, String email, String password) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (HashUtil.verifyPassword(password, hashedPassword)) {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getInt("idUsuario"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setApellido(rs.getString("apellido"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setRol(rs.getString("rol"));
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    // Método para obtener todos los usuarios
    public static List<Usuario> obtenerTodosLosUsuarios(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setEstado(resultSet.getString("estado"));
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    // Método para obtener usuarios por rol
    public static List<Usuario> obtenerUsuariosPorRol(Connection conn, String rol) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE rol = ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, rol);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setRol(rs.getString("rol"));
                    usuarios.add(usuario);
                }
            }
        }

        return usuarios;
    }

    // Método para eliminar un usuario
    public static void eliminarUsuario(Connection conn, int idUsuario) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        }
    }

    public static void actualizarUsuario(Connection conn, int idUsuario, String nombre, String apellido, String email, String rol, String estado) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre = ?, apellido = ?, email = ?, rol = ?, estado = ? WHERE idUsuario = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, rol);
            statement.setString(5, estado);
            statement.setInt(6, idUsuario);
            statement.executeUpdate();
        }
    }

    public static void cambiarEstadoUsuario(Connection conn, int idUsuario, String estado) throws SQLException {
        String sql = "UPDATE Usuarios SET estado = ? WHERE idUsuario = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado);
            statement.setInt(2, idUsuario);
            statement.executeUpdate();
        }
    }


}