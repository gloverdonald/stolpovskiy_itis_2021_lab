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
<script>
    function addUser(firstName, lastName, groupName, age) {
        let request = new XMLHttpRequest();
        let body = {
            "firstName": firstName,
            "lastName": lastName,
            "groupName": groupName,
            "age": age
        };

        request.open('POST', '/students', false);
        request.setRequestHeader('Content-Type', 'application/json');

        request.send(
            JSON.stringify(body)
        );

        if (request.status !== 200) {
            alert('Ошибка!');
        } else {
            let html =
                '<tr>' +
                '<th>ID</th>' +
                '<th>First Name</th>' +
                '<th>Last Name</th>' +
                '<th>groupName</th>' +
                '<th>age</th>' +
                '</tr>'

            let response = JSON.parse(request.response);

            for (let i = 0; i < response.length; i++) {
                html += '<tr>';
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['firstName'] + '</td>';
                html += '<td>' + response[i]['lastName'] + '</td>';
                html += '<td>' + response[i]['groupName'] + '</td>';
                html += '<td>' + response[i]['age'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('accounts_table').innerHTML = html;
        }
    }
</script>
<h1>Students</h1>

<div>
    First Name: <input class="form-control" id="firstName" name="firstName"/> <br/>
    Last Name: <input class="form-control" id="lastName" name="lastName"/> <br/>
    Name of group:<input class="form-control" id="groupName" name="groupName"/> <br/>
    Age:<input class="form-control" id="age" name="age" type="number"/> <br/>
    <button onclick="addUser(
        document.getElementById('firstName').value,
        document.getElementById('lastName').value,
        document.getElementById('groupName').value,
        document.getElementById('age').value
    )">Add
    </button>

</div>
<table id="accounts_table">
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
