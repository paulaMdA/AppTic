<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
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
        <form action="Controlador" method="post">
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
            <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
        </p>
        
        <form action="home.jsp" method="post">
            <input type="submit" value="Volver al Menú">
        </form>
    </div>
</body>
</html>
