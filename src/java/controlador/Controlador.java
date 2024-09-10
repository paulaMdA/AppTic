package controlador;

import modelo.ConexionBD;
import modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Equipo;
import modelo.Incidencias;
import util.HashUtil;


public class Controlador extends HttpServlet {
    
    
    private ConexionBD conexionBD;

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa la conexión a la base de datos cuando el servlet se carga
        conexionBD = new ConexionBD("localhost", "AppTIC", "pau", "kk");
    }

    @Override
    public void destroy() {
        if (conexionBD != null) {
            conexionBD.close();
        }
        super.destroy();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String rol = usuario != null ? usuario.getRol() : null;
        String action = request.getParameter("action");
       
        if (action == null) {
            action = "registro"; 
        }


       try (PrintWriter out = response.getWriter()) {
             ConexionBD conexionBD = new ConexionBD("localhost", "AppTIC", "pau", "kk");
            Connection conn = conexionBD.getConnection();

            try {
                switch (action) {
                    case "registro":
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String password = request.getParameter("password");
                    String email = request.getParameter("email");
                    rol = request.getParameter("rol");

                    
                    usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setPassword(HashUtil.hashPassword(password)); // Hashear la contraseña
                    usuario.setEmail(email);
                    usuario.setRol(rol);
                    usuario.setEstado("activo"); // Por defecto, el estado es activo

                    // Insertar el usuario en la base de datos
                    try {
                        usuario.insertarUsuario(conn);
                        request.setAttribute("mensajeRegistro", "¡Registro exitoso para el usuario " + nombre + " " + apellido + "!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("mensajeRegistro", "Error al registrar el usuario: " + e.getMessage());
                    }

                    // Obtener lista de usuarios para mostrar en la gestión
                    try {
                        List<Usuario> usuarios = Usuario.obtenerTodosLosUsuarios(conn);
                        request.setAttribute("usuarios", usuarios);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("mensajeGestion", "Error al obtener la lista de usuarios: " + e.getMessage());
                    }

                    // Redirigir de nuevo al formulario con el mensaje de éxito o error
                    request.getRequestDispatcher("gestionarUsuarios.jsp").forward(request, response);
                    break;

                    case "login":
                        String emailLogin = request.getParameter("email");
                        String passwordLogin = request.getParameter("password");

                        try {
                            usuario = Usuario.autenticarUsuario(conn, emailLogin, passwordLogin);
                            if (usuario != null) {
                                session = request.getSession();
                                session.setAttribute("usuario", usuario);
                                response.sendRedirect("home.jsp");
                            } else {
                                request.setAttribute("mensaje", "Credenciales inválidas. Por favor, intente de nuevo.");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al iniciar sesión: " + e.getMessage());
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                        break;

                    case "logout":
                        session = request.getSession(false);
                        if (session != null) {
                            session.invalidate();
                        }
                        response.sendRedirect("index.jsp");
                        break;

                    case "gestionarEquipos":
                        try {
                            List<Equipo> equipos = Equipo.obtenerTodosLosEquipos(conn);
                            request.setAttribute("equipos", equipos);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al obtener la lista de equipos: " + e.getMessage());
                        }

                        request.getRequestDispatcher("gestionarEquipos.jsp").forward(request, response);
                        break;

                    case "agregarEquipo":
                        String marca = request.getParameter("marca");
                        String modelo = request.getParameter("modelo");
                        String descripcion = request.getParameter("descripcion");
                        String aula = request.getParameter("aula");

                        try {
                            String idEquipo = Equipo.generarIdEquipo(conn, aula);
                            Equipo equipo = new Equipo(idEquipo, marca, modelo, descripcion, aula, "activo");
                            equipo.insertarEquipo(conn);
                            request.setAttribute("mensaje", "¡Equipo agregado exitosamente!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al agregar el equipo: " + e.getMessage());
                        }

                        try {
                            List<Equipo> equipos = Equipo.obtenerTodosLosEquipos(conn);
                            request.setAttribute("equipos", equipos);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al obtener la lista de equipos: " + e.getMessage());
                        }
                        request.getRequestDispatcher("gestionarEquipos.jsp").forward(request, response);
                        break;

                    case "actualizarEquipo":
                        String idEquipoActualizar = request.getParameter("idEquipo");
                        String marcaActualizar = request.getParameter("marca");
                        String modeloActualizar = request.getParameter("modelo");
                        String descripcionActualizar = request.getParameter("descripcion");
                        String aulaActualizar = request.getParameter("aula");

                        try {
                            Equipo.actualizarEquipo(conn, idEquipoActualizar, marcaActualizar, modeloActualizar, descripcionActualizar, aulaActualizar);
                            request.setAttribute("mensaje", "¡Equipo actualizado exitosamente!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al actualizar el equipo: " + e.getMessage());
                        }

                        try {
                            List<Equipo> equipos = Equipo.obtenerTodosLosEquipos(conn);
                            request.setAttribute("equipos", equipos);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al obtener la lista de equipos: " + e.getMessage());
                        }
                        request.getRequestDispatcher("gestionarEquipos.jsp").forward(request, response);
                        break;

                    case "eliminarEquipo":
                        String idEquipoEliminar = request.getParameter("idEquipo");

                        try {
                            Equipo.cambiarEstadoEquipo(conn, idEquipoEliminar, "inactivo");
                            request.setAttribute("mensaje", "¡Equipo desactivado exitosamente!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al desactivar el equipo: " + e.getMessage());
                        }

                        try {
                            List<Equipo> equipos = Equipo.obtenerTodosLosEquipos(conn);
                            request.setAttribute("equipos", equipos);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensaje", "Error al obtener la lista de equipos: " + e.getMessage());
                        }
                        request.getRequestDispatcher("gestionarEquipos.jsp").forward(request, response);
                        break;


                        
                        
                        case "gestionarIncidencias":
                            try {
                                List<Equipo> equiposDisponibles = Equipo.obtenerEquiposDisponibles(conn);
                                request.setAttribute("equiposDisponibles", equiposDisponibles);

                                List<Incidencias> incidencias = Incidencias.obtenerTodasLasIncidencias(conn);
                                request.setAttribute("incidencias", incidencias);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("mensaje", "Error al obtener la lista de equipos o incidencias: " + e.getMessage());
                            }

                            // Redirigir a la página de gestión de incidencias
                            request.getRequestDispatcher("gestionarIncidencias.jsp").forward(request, response);
                            break;

                        case "actualizarIncidencia":
                            int idIncidencia = Integer.parseInt(request.getParameter("idIncidencia"));

                            try {
                                Incidencias.actualizarEstadoIncidencia(conn, idIncidencia, "reparado");
                                request.setAttribute("mensaje", "¡Incidencia actualizada exitosamente!");
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("mensaje", "Error al actualizar la incidencia: " + e.getMessage());
                            }

                            // Redirigir a la página de gestión de incidencias
                            request.getRequestDispatcher("Controlador?action=gestionarIncidencias").forward(request, response);
                            break;

                        case "crearIncidencia":
                            try {
                                List<Equipo> equipos = Equipo.obtenerEquiposDisponibles(conn);
                                request.setAttribute("equipos", equipos);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("mensaje", "Error al obtener la lista de equipos: " + e.getMessage());
                            }

                            // Redirigir a la página de creación de incidencias
                            request.getRequestDispatcher("crearIncidencia.jsp").forward(request, response);
                            break;


                        case "reportarIncidencia":
                            HttpSession currentSession = request.getSession(false);
                            Usuario currentUser = (Usuario) currentSession.getAttribute("usuario");

                            int idUsuario = currentUser.getIdUsuario();
                            String idEquipo = request.getParameter("idEquipo");
                            String prioridad = request.getParameter("prioridad");
                            String descripcionIncidencia = request.getParameter("descripcion");
                            String estado = "pendiente";
                            Timestamp fechaInicio = new Timestamp(System.currentTimeMillis());

                            Incidencias incidencia = new Incidencias(idUsuario, idEquipo, estado, prioridad, descripcionIncidencia, fechaInicio, null);

                            try {
                                incidencia.insertarIncidencia(conn);
                                request.setAttribute("mensaje", "¡Incidencia reportada exitosamente!");
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("mensaje", "Error al reportar la incidencia: " + e.getMessage());
                            }

                            // Redirigir de nuevo al formulario con el mensaje de éxito o error
                            request.getRequestDispatcher("crearIncidencia.jsp").forward(request, response);
                            break;


                        case "verMisIncidencias":
                            session = request.getSession(false);
                            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

                            if (usuarioSesion != null && ("profesor".equals(usuarioSesion.getRol()) || "profesorMantenimiento".equals(usuarioSesion.getRol()))) {
                                try {
                                    List<Incidencias> incidencias = Incidencias.obtenerIncidenciasPorUsuario(conn, usuarioSesion.getIdUsuario());
                                    request.setAttribute("incidencias", incidencias);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    request.setAttribute("mensaje", "Error al obtener las incidencias: " + e.getMessage());
                                }
                                request.getRequestDispatcher("verMisIncidencias.jsp").forward(request, response);
                            } else {
                                response.sendRedirect("home.jsp");
                            }
                            break;

                        case "verEquipos":
                            try {
                                List<Equipo> equiposDisponibles = Equipo.obtenerEquiposDisponibles(conn);
                                request.setAttribute("equiposDisponibles", equiposDisponibles);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("mensaje", "Error al obtener la lista de equipos disponibles: " + e.getMessage());
                            }

                            // Redirigir a la página de ver equipos
                            request.getRequestDispatcher("verEquipos.jsp").forward(request, response);
                            break;


                    case "gestionarUsuarios":
                        String rolFiltro = request.getParameter("rol");

                        try {
                            List<Usuario> usuarios;
                            if (rolFiltro == null || rolFiltro.isEmpty()) {
                                usuarios = Usuario.obtenerTodosLosUsuarios(conn);
                            } else {
                                usuarios = Usuario.obtenerUsuariosPorRol(conn, rolFiltro);
                            }
                            request.setAttribute("usuarios", usuarios);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensajeGestion", "Error al obtener la lista de usuarios: " + e.getMessage());
                        }

                        // Redirigir a la página de gestión de usuarios
                        request.getRequestDispatcher("gestionarUsuarios.jsp").forward(request, response);
                        break;


                    case "actualizarUsuario":
                        int idUsuarioActualizar = Integer.parseInt(request.getParameter("idUsuario"));
                        String nombreActualizar = request.getParameter("nombre");
                        String apellidoActualizar = request.getParameter("apellido");
                        String emailActualizar = request.getParameter("email");
                        String rolActualizar = request.getParameter("rol");
                        String estadoActualizar = request.getParameter("estado");

                        try {
                            Usuario.actualizarUsuario(conn, idUsuarioActualizar, nombreActualizar, apellidoActualizar, emailActualizar, rolActualizar, estadoActualizar);
                            request.setAttribute("mensajeGestion", "¡Usuario actualizado exitosamente!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensajeGestion", "Error al actualizar el usuario: " + e.getMessage());
                        }

                        // Obtener lista de usuarios y redirigir a la página de gestión de usuarios
                        try {
                            List<Usuario> usuarios = Usuario.obtenerTodosLosUsuarios(conn);
                            request.setAttribute("usuarios", usuarios);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensajeGestion", "Error al obtener la lista de usuarios: " + e.getMessage());
                        }
                        request.getRequestDispatcher("gestionarUsuarios.jsp").forward(request, response);
                        break;

                    case "eliminarUsuario":
                        int idUsuarioEliminar = Integer.parseInt(request.getParameter("idUsuario"));

                        try {
                            Usuario.cambiarEstadoUsuario(conn, idUsuarioEliminar, "inactivo");
                            request.setAttribute("mensajeGestion", "¡Usuario desactivado exitosamente!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensajeGestion", "Error al desactivar el usuario: " + e.getMessage());
                        }

                        // Obtener lista de usuarios y redirigir a la página de gestión de usuarios
                        try {
                            List<Usuario> usuarios = Usuario.obtenerTodosLosUsuarios(conn);
                            request.setAttribute("usuarios", usuarios);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.setAttribute("mensajeGestion", "Error al obtener la lista de usuarios: " + e.getMessage());
                        }
                        request.getRequestDispatcher("gestionarUsuarios.jsp").forward(request, response);
                        break;



               
                
                }
            } finally {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            throw new ServletException("Error al conectar con la base de datos", ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
