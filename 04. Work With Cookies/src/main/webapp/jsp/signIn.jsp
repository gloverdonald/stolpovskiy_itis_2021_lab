<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
<form action="/sign-in" method="post">
    <input name="username" type="text" placeholder="NAME"/>
    <input name="password" type="password" placeholder="PASSWORD"/>
    <input type="submit">
</form>
</body>
</html>