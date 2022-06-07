<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <style>
        body {
            background-color: #3b3b3b;
            color: white;
        }

        .center {
            font-family: 'Times New Roman', Times, serif;
            font-size: 250%;
            margin: auto;
            width: 50%;
            border: 3px solid green;
            padding: 10px;
        }
    </style>
</head>

<body>
<div class="center">
    <#if firstName?? && confirmLink??>Уважаемый, ${firstName}! Пройдите для подтверждения регистрации по <a
            href=${confirmLink}>ссылке</a> <#else>
        error
    </#if>
</div>

</body>
</html>