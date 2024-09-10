<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <div id="branding">
                <h1>AppTIC</h1>
            </div>
        </div>
    </header>

    <div class="container">
        <h1>Login</h1>
        <form action="Controlador?action=login" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="Iniciar sesión">
        </form>

        <p>
            <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
        </p>
    </div>
</body>
</html>
