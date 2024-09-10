package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import modelo.Usuario;

public final class gestionarUsuarios_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Gestionar Usuarios</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div id=\"branding\">\n");
      out.write("                <h1>AppTIC</h1>\n");
      out.write("            </div>\n");
      out.write("            <nav>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("                    <li><a href=\"Controlador?action=logout\">Cerrar sesión</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("        </div>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <h1>Registro de Usuario</h1>\n");
      out.write("        <form action=\"Controlador?action=registro\" method=\"post\">\n");
      out.write("            <label for=\"nombre\">Nombre:</label>\n");
      out.write("            <input type=\"text\" id=\"nombre\" name=\"nombre\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"apellido\">Apellido:</label>\n");
      out.write("            <input type=\"text\" id=\"apellido\" name=\"apellido\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"password\">Contraseña:</label>\n");
      out.write("            <input type=\"password\" id=\"password\" name=\"password\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"email\">Email:</label>\n");
      out.write("            <input type=\"email\" id=\"email\" name=\"email\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"rol\">Rol:</label>\n");
      out.write("            <select id=\"rol\" name=\"rol\" required>\n");
      out.write("                <option value=\"alumno\">Alumno</option>\n");
      out.write("                <option value=\"profesor\">Profesor</option>\n");
      out.write("                <option value=\"mantenimiento\">Mantenimiento</option>\n");
      out.write("                <option value=\"profesorMantenimiento\">Profesor/Mantenimiento</option>\n");
      out.write("            </select><br>\n");
      out.write("\n");
      out.write("            <input type=\"submit\" value=\"Registrarse\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <p>\n");
      out.write("            ");
      out.print( request.getAttribute("mensajeRegistro") != null ? request.getAttribute("mensajeRegistro") : "" );
      out.write("\n");
      out.write("        </p>\n");
      out.write("\n");
      out.write("        <form action=\"home.jsp\" method=\"post\">\n");
      out.write("            <input type=\"submit\" value=\"Volver al Menú\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <h1>Gestionar Usuarios</h1>\n");
      out.write("        <form action=\"Controlador?action=gestionarUsuarios\" method=\"post\">\n");
      out.write("            <label for=\"rol\">Filtrar por Rol:</label>\n");
      out.write("            <select id=\"rol\" name=\"rol\">\n");
      out.write("                <option value=\"\">Todos</option>\n");
      out.write("                <option value=\"alumno\">Alumno</option>\n");
      out.write("                <option value=\"profesor\">Profesor</option>\n");
      out.write("                <option value=\"mantenimiento\">Mantenimiento</option>\n");
      out.write("                <option value=\"profesorMantenimiento\">Profesor/Mantenimiento</option>\n");
      out.write("            </select>\n");
      out.write("            <input type=\"submit\" value=\"Filtrar\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        ");

            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            if (usuarios != null && !usuarios.isEmpty()) {
        
      out.write("\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th>ID Usuario</th>\n");
      out.write("                    <th>Nombre</th>\n");
      out.write("                    <th>Apellido</th>\n");
      out.write("                    <th>Email</th>\n");
      out.write("                    <th>Rol</th>\n");
      out.write("                    <th>Estado</th>\n");
      out.write("                    <th>Acción</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    for (Usuario usuario : usuarios) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <form action=\"Controlador?action=actualizarUsuario\" method=\"post\">\n");
      out.write("                        <td><input type=\"hidden\" name=\"idUsuario\" value=\"");
      out.print( usuario.getIdUsuario() );
      out.write('"');
      out.write('>');
      out.print( usuario.getIdUsuario() );
      out.write("</td>\n");
      out.write("                        <td><input type=\"text\" name=\"nombre\" value=\"");
      out.print( usuario.getNombre() );
      out.write("\"></td>\n");
      out.write("                        <td><input type=\"text\" name=\"apellido\" value=\"");
      out.print( usuario.getApellido() );
      out.write("\"></td>\n");
      out.write("                        <td><input type=\"email\" name=\"email\" value=\"");
      out.print( usuario.getEmail() );
      out.write("\"></td>\n");
      out.write("                        <td>\n");
      out.write("                            <select name=\"rol\">\n");
      out.write("                                <option value=\"alumno\" ");
      out.print( "alumno".equals(usuario.getRol()) ? "selected" : "" );
      out.write(">Alumno</option>\n");
      out.write("                                <option value=\"profesor\" ");
      out.print( "profesor".equals(usuario.getRol()) ? "selected" : "" );
      out.write(">Profesor</option>\n");
      out.write("                                <option value=\"mantenimiento\" ");
      out.print( "mantenimiento".equals(usuario.getRol()) ? "selected" : "" );
      out.write(">Mantenimiento</option>\n");
      out.write("                                <option value=\"profesorMantenimiento\" ");
      out.print( "profesorMantenimiento".equals(usuario.getRol()) ? "selected" : "" );
      out.write(">Profesor/Mantenimiento</option>\n");
      out.write("                            </select>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <p>Estado actual: ");
      out.print( usuario.getEstado() );
      out.write("</p>\n");
      out.write("                            <select name=\"estado\">\n");
      out.write("                                <option value=\"activo\" ");
      out.print( "activo".equals(usuario.getEstado()) ? "selected" : "" );
      out.write(">Activo</option>\n");
      out.write("                                <option value=\"inactivo\" ");
      out.print( "inactivo".equals(usuario.getEstado()) ? "selected" : "" );
      out.write(">Inactivo</option>\n");
      out.write("                            </select>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <input type=\"submit\" value=\"Actualizar\">\n");
      out.write("                        </td>\n");
      out.write("                    </form>\n");
      out.write("                    <td>\n");
      out.write("                        <form action=\"Controlador?action=eliminarUsuario\" method=\"post\" style=\"display:inline;\">\n");
      out.write("                            <input type=\"hidden\" name=\"idUsuario\" value=\"");
      out.print( usuario.getIdUsuario() );
      out.write("\">\n");
      out.write("                            <input type=\"submit\" value=\"Desactivar\" onclick=\"return confirm('¿Estás seguro de que deseas desactivar este usuario?');\">\n");
      out.write("                        </form>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("        ");

            } else {
        
      out.write("\n");
      out.write("            <p>No hay usuarios para mostrar.</p>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <p>\n");
      out.write("            ");
      out.print( request.getAttribute("mensajeGestion") != null ? request.getAttribute("mensajeGestion") : "" );
      out.write("\n");
      out.write("        </p>\n");
      out.write("\n");
      out.write("        <form action=\"home.jsp\" method=\"post\">\n");
      out.write("            <input type=\"submit\" value=\"Volver al Menú\">\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
