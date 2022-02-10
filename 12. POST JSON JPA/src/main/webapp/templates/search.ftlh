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
    function searchUsers(name) {
        let request = new XMLHttpRequest();

        request.open('GET', '/search/students/byName?name=' + name, false);
        console.log(request)

        request.send();

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
<h1>Search page</h1>
<label>
    <input id="name" name="name" placeholder="name" onkeyup="searchUsers(document.getElementById('name').value)">
    Search by First or Last Name
</label>
<br>
<table id="accounts_table">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>groupName</th>
        <th>age</th>
    </tr>
</table>
</body>
</html>
