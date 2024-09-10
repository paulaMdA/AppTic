package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import modelo.Incidencias;

public final class verMisIncidencias_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Mis Incidencias</title>\n");
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
      out.write("        <h1>Mis Incidencias</h1>\n");
      out.write("\n");
      out.write("        ");

            List<Incidencias> incidencias = (List<Incidencias>) request.getAttribute("incidencias");
            if (incidencias != null && !incidencias.isEmpty()) {
        
      out.write("\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th>ID Incidencia</th>\n");
      out.write("                    <th>ID Usuario</th>\n");
      out.write("                    <th>ID Equipo</th>\n");
      out.write("                    <th>Estado</th>\n");
      out.write("                    <th>Prioridad</th>\n");
      out.write("                    <th>Descripción</th>\n");
      out.write("                    <th>Fecha Inicio</th>\n");
      out.write("                    <th>Fecha Fin</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    for (Incidencias incidencia : incidencias) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( incidencia.getIdIncidencia() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getIdUsuario() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getIdEquipo() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getEstado() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getPrioridad() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getDescripcion() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getFechaInicio() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( incidencia.getFechaFin() );
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("        ");

            } else {
        
      out.write("\n");
      out.write("            <p>No hay incidencias para mostrar.</p>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <p>\n");
      out.write("            ");
      out.print( request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" );
      out.write("\n");
      out.write("        </p>\n");
      out.write("        \n");
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
