<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${color}">AccountsPage</h1>
<table>
    <tr>
        <th>Id</th>
        <th>username</th>
    </tr>
<c:forEach items="${accounts}" var="account">
    <tr>
        <td>${account.id}</td>
        <td>${account.username}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
