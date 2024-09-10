package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import modelo.Equipo;

public final class crearIncidencia_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Reportar Incidencia</title>\n");
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
      out.write("        <h1>Reportar Incidencia</h1>\n");
      out.write("        <form action=\"Controlador?action=reportarIncidencia\" method=\"post\">\n");
      out.write("            <label for=\"idEquipo\">ID del Equipo:</label>\n");
      out.write("            <select id=\"idEquipo\" name=\"idEquipo\" required>\n");
      out.write("                ");

                    List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
                    if (equipos != null) {
                        for (Equipo equipo : equipos) {
                
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( equipo.getIdEquipo() );
      out.write('"');
      out.write('>');
      out.print( equipo.getIdEquipo() );
      out.write(" - ");
      out.print( equipo.getMarca() );
      out.write(' ');
      out.print( equipo.getModelo() );
      out.write("</option>\n");
      out.write("                ");

                        }
                    }
                
      out.write("\n");
      out.write("            </select><br>\n");
      out.write("\n");
      out.write("            <label for=\"prioridad\">Prioridad:</label>\n");
      out.write("            <select id=\"prioridad\" name=\"prioridad\" required>\n");
      out.write("                <option value=\"baja\">Baja</option>\n");
      out.write("                <option value=\"media\">Media</option>\n");
      out.write("                <option value=\"alta\">Alta</option>\n");
      out.write("            </select><br>\n");
      out.write("\n");
      out.write("            <label for=\"descripcion\">Descripción:</label>\n");
      out.write("            <textarea id=\"descripcion\" name=\"descripcion\" required></textarea><br>\n");
      out.write("\n");
      out.write("            <input type=\"submit\" value=\"Reportar Incidencia\">\n");
      out.write("        </form>\n");
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
