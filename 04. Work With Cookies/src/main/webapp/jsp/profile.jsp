<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<h1 style="color: ${color}">Profile Page</h1>
<body>
<form>
    <input type="radio" id="redColor" name="color" value="red">
    <label for="redColor">Red</label>
    <input type="radio" id="greenColor" name="color" value="green">
    <label for="greenColor">Green</label>
    <input type="radio" id="blueColor" name="color" value="blue">
    <label for="blueColor">Blue</label>
    <input type="radio" id="yellowColor" name="color" value="yellow">
    <label for="yellowColor">Yellow</label>
    <input type="submit" value="Save Color">
</form>
</body>
</html>
