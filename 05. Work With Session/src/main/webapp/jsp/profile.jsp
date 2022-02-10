<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        .name {
            font-size: 20px;
        }

        .outer:before {
            content: '';
            display: inline-block;
            height: 100%;
            vertical-align: middle;
        }

        .inner {
            display: inline-block;
            vertical-align: middle;
        }

        .outer {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="outer" style="height:100px; width: 100%;">
    <h1 style="color: ${color}">Profile Page</h1>
    <span class="inner" style="border:1px solid ${color}">
        <div class="name">Email: ${account.email}</div>
        <div class="name">Firstname: ${account.firstName}</div>
        <div class="name">Lastname: ${account.lastName}</div>
    </span>
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
</div>

</body>
</html>
