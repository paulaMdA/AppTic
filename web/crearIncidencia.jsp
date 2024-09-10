<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Equipo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reportar Incidencia</title>
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
                    List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
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
        
        <form action="home.jsp" method="post">
            <input type="submit" value="Volver al Menú">
        </form>
    </div>
</body>
</html>
