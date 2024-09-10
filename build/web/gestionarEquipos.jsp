<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Equipo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestionar Equipos</title>
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
        <h1>Gestionar Equipos</h1>

        <h2>Agregar Nuevo Equipo</h2>
        <form action="Controlador?action=agregarEquipo" method="post">
            <label for="marca">Marca:</label>
            <input type="text" id="marca" name="marca" required><br>

            <label for="modelo">Modelo:</label>
            <input type="text" id="modelo" name="modelo" required><br>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required></textarea><br>

            <label for="aula">Aula:</label>
            <select id="aula" name="aula" required>
                <option value="A1">A1</option>
                <option value="A2">A2</option>
                <option value="A3">A3</option>
                <option value="B1">B1</option>
                <option value="B2">B2</option>
                <option value="B3">B3</option>
                <option value="C1">C1</option>
                <option value="C2">C2</option>
                <option value="C3">C3</option>
            </select><br>

            <input type="submit" value="Agregar Equipo">
        </form>

        <h2>Lista de Equipos</h2>
        <%
            List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
            if (equipos != null && !equipos.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>ID Equipo</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Descripción</th>
                    <th>Aula</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
                <%
                    for (Equipo equipo : equipos) {
                %>
                <tr>
                    <form action="Controlador?action=actualizarEquipo" method="post">
                        <td><input type="hidden" name="idEquipo" value="<%= equipo.getIdEquipo() %>"><%= equipo.getIdEquipo() %></td>
                        <td><input type="text" name="marca" value="<%= equipo.getMarca() %>"></td>
                        <td><input type="text" name="modelo" value="<%= equipo.getModelo() %>"></td>
                        <td><input type="text" name="descripcion" value="<%= equipo.getDescripcion() %>"></td>
                        <td>
                            <select name="aula">
                                <option value="A1" <%= "A1".equals(equipo.getAula()) ? "selected" : "" %>>A1</option>
                                <option value="A2" <%= "A2".equals(equipo.getAula()) ? "selected" : "" %>>A2</option>
                                <option value="A3" <%= "A3".equals(equipo.getAula()) ? "selected" : "" %>>A3</option>
                                <option value="B1" <%= "B1".equals(equipo.getAula()) ? "selected" : "" %>>B1</option>
                                <option value="B2" <%= "B2".equals(equipo.getAula()) ? "selected" : "" %>>B2</option>
                                <option value="B3" <%= "B3".equals(equipo.getAula()) ? "selected" : "" %>>B3</option>
                                <option value="C1" <%= "C1".equals(equipo.getAula()) ? "selected" : "" %>>C1</option>
                                <option value="C2" <%= "C2".equals(equipo.getAula()) ? "selected" : "" %>>C2</option>
                                <option value="C3" <%= "C3".equals(equipo.getAula()) ? "selected" : "" %>>C3</option>
                            </select>
                        </td>
                        <td><%= equipo.getEstado() %></td>
                        <td>
                            <input type="submit" value="Actualizar">
                        </td>
                    </form>
                    <td>
                        <form action="Controlador?action=eliminarEquipo" method="post" style="display:inline;">
                            <input type="hidden" name="idEquipo" value="<%= equipo.getIdEquipo() %>">
                            <input type="submit" value="Desactivar" onclick="return confirm('¿Estás seguro de que deseas desactivar este equipo?');">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
            <p>No hay equipos para mostrar.</p>
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
