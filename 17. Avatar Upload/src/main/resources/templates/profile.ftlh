<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>
<h3>Email: ${account.email}</h3>
<h3>Name: ${account.firstName} ${account.lastName}</h3>
<#if account.avatar??>
    <image style="max-height:200px;" src="http://localhost:8888/files/${account.avatar.storageFileName}"/>
</#if>
</body>
</html>