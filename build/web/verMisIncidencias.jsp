<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Incidencias" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Incidencias</title>
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
        <h1>Mis Incidencias</h1>

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
