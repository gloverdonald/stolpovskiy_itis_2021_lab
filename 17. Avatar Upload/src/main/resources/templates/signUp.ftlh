<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
</head>
<body>
<form method="post" action="/signUp" enctype="multipart/form-data">
    <input type="text" name="email" placeholder="Email"/><br/>
    <input type="text" name="firstName" placeholder="First Name"/><br/>
    <input type="text" name="lastName" placeholder="Last Name"/><br/>
    <input type="password" name="password" placeholder="Password"/><br/><br/>
    <input type="file" name="multipartFile" placeholder="User pic"/><br/><br/>
    <input type="submit" value="File Upload"/>
</form>
</body>
</html>