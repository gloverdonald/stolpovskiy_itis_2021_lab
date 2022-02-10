<%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 20.10.2021
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files Upload</title>
</head>
<body>
<form action="/filesUpload" method="post" enctype="multipart/form-data">
    <label for="description">Description</label>
    <input id="description" name="description" placeholder="Enter description...">
    <br>
    <br>
    <input type="file" name="file">
    <input type="submit" value="File Upload">
</form>
</body>
</html>
