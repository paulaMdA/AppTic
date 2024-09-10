<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Incidencias" %>
<%@ page import="modelo.Equipo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestionar Incidencias</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <div id="branding">
                <h1>AppTIC</h1>
            </div>
            <nav>
                <ul>
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="Controlador?action=logout">Cerrar sesión</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="container">
        <h1>Reportar Incidencia</h1>
        <form action="Controlador?action=reportarIncidencia" method="post">
            <label for="idEquipo">ID del Equipo:</label>
            <select id="idEquipo" name="idEquipo" required>
                <%
                    List<Equipo> equipos = (List<Equipo>) request.getAttribute("equiposDisponibles");
                    if (equipos != null) {
                        for (Equipo equipo : equipos) {
                %>
                            <option value="<%= equipo.getIdEquipo() %>"><%= equipo.getIdEquipo() %> - <%= equipo.getMarca() %> <%= equipo.getModelo() %></option>
                <%
                        }
                    }
                %>
            </select><br>

            <label for="prioridad">Prioridad:</label>
            <select id="prioridad" name="prioridad" required>
                <option value="baja">Baja</option>
                <option value="media">Media</option>
                <option value="alta">Alta</option>
            </select><br>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required></textarea><br>

            <input type="submit" value="Reportar Incidencia">
        </form>

        <p>
            <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
        </p>
        
        <h1>Gestionar Incidencias</h1>

        <%
            List<Incidencias> incidencias = (List<Incidencias>) request.getAttribute("incidencias");
            if (incidencias != null && !incidencias.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>ID Incidencia</th>
                    <th>ID Usuario</th>
                    <th>ID Equipo</th>
                    <th>Estado</th>
                    <th>Prioridad</th>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Acción</th>
                </tr>
                <%
                    for (Incidencias incidencia : incidencias) {
                %>
                <tr>
                    <td><%= incidencia.getIdIncidencia() %></td>
                    <td><%= incidencia.getIdUsuario() %></td>
                    <td><%= incidencia.getIdEquipo() %></td>
                    <td><%= incidencia.getEstado() %></td>
                    <td><%= incidencia.getPrioridad() %></td>
                    <td><%= incidencia.getDescripcion() %></td>
                    <td><%= incidencia.getFechaInicio() %></td>
                    <td><%= incidencia.getFechaFin() %></td>
                    <td>
                        <% if ("pendiente".equals(incidencia.getEstado())) { %>
                            <form action="Controlador?action=actualizarIncidencia" method="post">
                                <input type="hidden" name="idIncidencia" value="<%= incidencia.getIdIncidencia() %>">
                                <input type="submit" value="Marcar como Reparado">
                            </form>
                        <% } else { %>
                            Reparado
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
            <p>No hay incidencias para mostrar.</p>
        <%
            }
        %>

        <p>
            <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
        </p>
        
        <form action="home.jsp" method="post">
            <input type="submit" value="Volver al Menú">
        </form>
    </div>
</body>
</html>
