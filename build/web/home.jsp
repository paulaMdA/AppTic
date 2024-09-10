<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
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
                    <li class="current"><a href="home.jsp">Home</a></li>
                    <li><a href="Controlador?action=logout">Cerrar sesión</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="container">
        <h1>Bienvenido</h1>

        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null) {
                String rol = usuario.getRol();
        %>
                <p>Hola, <%= usuario.getNombre() %> <%= usuario.getApellido() %>. Rol: <%= rol %></p>

                <%
                    if ("profesorMantenimiento".equals(rol)) {
                %>
                    <h2>Gestión de Equipos</h2>
                    <form action="Controlador?action=gestionarEquipos" method="post">
                        <input type="submit" value="Gestionar Equipos">
                    </form>
                    <h2>Ver Equipos Disponibles</h2>
                    <form action="Controlador?action=verEquipos" method="post">
                        <input type="submit" value="Ver Equipos Disponibles">
                    </form>
                    <h2>Gestión de Usuarios</h2>
                    <form action="Controlador?action=gestionarUsuarios" method="post">
                        <input type="submit" value="Gestionar Usuarios">
                    </form>
                    <h2>Gestión de Incidencias</h2>
                    <form action="Controlador?action=gestionarIncidencias" method="post">
                        <input type="submit" value="Gestionar Incidencias">
                    </form>
                    <h2>Reportar Incidencia</h2>
                    <form action="Controlador?action=crearIncidencia" method="post">
                        <input type="submit" value="Reportar Incidencia">
                    </form>
                    <h2>Mis Incidencias</h2>
                    <form action="Controlador?action=verMisIncidencias" method="post">
                        <input type="submit" value="Ver Mis Incidencias">
                    </form>
                <%
                   } else if ("mantenimiento".equals(rol)) {
                %>
                    <h2>Gestión de Equipos</h2>
                    <form action="Controlador?action=gestionarEquipos" method="post">
                        <input type="submit" value="Gestionar Equipos">
                    </form>
                    <h2>Gestión de Usuarios</h2>
                    <form action="Controlador?action=gestionarUsuarios" method="post">
                        <input type="submit" value="Gestionar Usuarios">
                    </form>
                    <h2>Gestión de Incidencias</h2>
                    <form action="Controlador?action=gestionarIncidencias" method="post">
                        <input type="submit" value="Gestionar Incidencias">
                    </form>
                <%
                    } else if ("profesor".equals(rol)) {
                %>
                    <h2>Reportar Incidencia</h2>
                    <form action="Controlador?action=crearIncidencia" method="post">
                        <input type="submit" value="Reportar Incidencia">
                    </form>
                    <h2>Ver Equipos Disponibles</h2>
                    <form action="Controlador?action=verEquipos" method="post">
                        <input type="submit" value="Ver Equipos Disponibles">
                    </form>
                    <h2>Mis Incidencias</h2>
                    <form action="Controlador?action=verMisIncidencias" method="post">
                        <input type="submit" value="Ver Mis Incidencias">
                    </form>
                <%
                    } else if ("alumno".equals(rol)) {
                %>
                    <h2>Reportar Incidencia</h2>
                    <form action="Controlador?action=crearIncidencia" method="post">
                        <input type="submit" value="Reportar Incidencia">
                    </form>
                    <h2>Ver Equipos Disponibles</h2>
                    <form action="Controlador?action=verEquipos" method="post">
                        <input type="submit" value="Ver Equipos Disponibles">
                    </form>
        <%
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </div>
</body>
</html>
