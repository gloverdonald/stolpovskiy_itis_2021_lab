
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<form method="post" action="/sign-in">
    <label for="email">Введите вашу почту:</label>
    <input id="email" type="email" name="email" placeholder="Почта">
    <br>
    <label for="password">Введите ваш пароль:</label>
    <input id="password" type="password" name="password" placeholder="Пароль">
    <br>
    <br>
    <input type="submit" value="Sign In">
</form>
</body>
</html>
