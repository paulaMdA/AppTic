<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Equipo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ver Equipos</title>
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
        <h1>Equipos Disponibles</h1>

        <%
            List<Equipo> equiposDisponibles = (List<Equipo>) request.getAttribute("equiposDisponibles");
            if (equiposDisponibles != null && !equiposDisponibles.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>ID Equipo</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Descripción</th>
                    <th>Aula</th>
                </tr>
                <%
                    for (Equipo equipo : equiposDisponibles) {
                %>
                <tr>
                    <td><%= equipo.getIdEquipo() %></td>
                    <td><%= equipo.getMarca() %></td>
                    <td><%= equipo.getModelo() %></td>
                    <td><%= equipo.getDescripcion() %></td>
                    <td><%= equipo.getAula() %></td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
            <p>No hay equipos disponibles para mostrar.</p>
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
