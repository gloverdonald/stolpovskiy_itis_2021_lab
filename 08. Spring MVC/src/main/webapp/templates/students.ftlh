<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Students</title>
</head>
<body>
<h1>Students</h1>

<form name="student" action="students" method="POST">
    First Name: <input class="form-control" id="firstName" name="firstName"/> <br/>
    Last Name: <input class="form-control" id="lastName" name="lastName"/> <br/>
    Name of group:<input class="form-control" id="groupName" name="groupName"/> <br/>
    Age:<input class="form-control" id="age" name="age"/> <br/>
    <input type="submit" value="Save"/>
</form>
<table>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Group Name</th>
        <th>Age</th>
    </tr>
    <#list students as student>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.groupName}</td>
            <td>${student.age}</td>
        </tr>
    </#list>
</table>
</body>
</html>
