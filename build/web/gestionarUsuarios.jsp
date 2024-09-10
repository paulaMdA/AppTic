<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestionar Usuarios</title>
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
        <h1>Registro de Usuario</h1>
        <form action="Controlador?action=registro" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br>

            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required><br>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="rol">Rol:</label>
            <select id="rol" name="rol" required>
                <option value="alumno">Alumno</option>
                <option value="profesor">Profesor</option>
                <option value="mantenimiento">Mantenimiento</option>
                <option value="profesorMantenimiento">Profesor/Mantenimiento</option>
            </select><br>

            <input type="submit" value="Registrarse">
        </form>

        <p>
            <%= request.getAttribute("mensajeRegistro") != null ? request.getAttribute("mensajeRegistro") : "" %>
        </p>

        <form action="home.jsp" method="post">
            <input type="submit" value="Volver al Menú">
        </form>

        <h1>Gestionar Usuarios</h1>
        <form action="Controlador?action=gestionarUsuarios" method="post">
            <label for="rol">Filtrar por Rol:</label>
            <select id="rol" name="rol">
                <option value="">Todos</option>
                <option value="alumno">Alumno</option>
                <option value="profesor">Profesor</option>
                <option value="mantenimiento">Mantenimiento</option>
                <option value="profesorMantenimiento">Profesor/Mantenimiento</option>
            </select>
            <input type="submit" value="Filtrar">
        </form>

        <%
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            if (usuarios != null && !usuarios.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>ID Usuario</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
                <%
                    for (Usuario usuario : usuarios) {
                %>
                <tr>
                    <form action="Controlador?action=actualizarUsuario" method="post">
                        <td><input type="hidden" name="idUsuario" value="<%= usuario.getIdUsuario() %>"><%= usuario.getIdUsuario() %></td>
                        <td><input type="text" name="nombre" value="<%= usuario.getNombre() %>"></td>
                        <td><input type="text" name="apellido" value="<%= usuario.getApellido() %>"></td>
                        <td><input type="email" name="email" value="<%= usuario.getEmail() %>"></td>
                        <td>
                            <select name="rol">
                                <option value="alumno" <%= "alumno".equals(usuario.getRol()) ? "selected" : "" %>>Alumno</option>
                                <option value="profesor" <%= "profesor".equals(usuario.getRol()) ? "selected" : "" %>>Profesor</option>
                                <option value="mantenimiento" <%= "mantenimiento".equals(usuario.getRol()) ? "selected" : "" %>>Mantenimiento</option>
                                <option value="profesorMantenimiento" <%= "profesorMantenimiento".equals(usuario.getRol()) ? "selected" : "" %>>Profesor/Mantenimiento</option>
                            </select>
                        </td>
                        <td>
                            <p>Estado actual: <%= usuario.getEstado() %></p>
                            <select name="estado">
                                <option value="activo" <%= "activo".equals(usuario.getEstado()) ? "selected" : "" %>>Activo</option>
                                <option value="inactivo" <%= "inactivo".equals(usuario.getEstado()) ? "selected" : "" %>>Inactivo</option>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Actualizar">
                        </td>
                    </form>
                    <td>
                        <form action="Controlador?action=eliminarUsuario" method="post" style="display:inline;">
                            <input type="hidden" name="idUsuario" value="<%= usuario.getIdUsuario() %>">
                            <input type="submit" value="Desactivar" onclick="return confirm('¿Estás seguro de que deseas desactivar este usuario?');">
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
            <p>No hay usuarios para mostrar.</p>
        <%
            }
        %>

        <p>
            <%= request.getAttribute("mensajeGestion") != null ? request.getAttribute("mensajeGestion") : "" %>
        </p>

        <form action="home.jsp" method="post">
            <input type="submit" value="Volver al Menú">
        </form>
    </div>
</body>
</html>
