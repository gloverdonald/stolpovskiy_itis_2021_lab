<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        let x = 0;
        function addInput() {
            document.getElementById('input' + x).innerHTML = '<input type="file" name="file"> <div id="input' + (x + 1) + '"></div>';
            x++;
        }
    </script>
</head>
<body>
<button class="add" onclick="addInput()">Добавить файл</button>
<form enctype="multipart/form-data" method="post" action="/files/upload" class="files">

    <label for="description">Description</label>

    <input type="text" id="description" name="description" placeholder="Enter description...">
    <br>

    <input type="file" name="file">
    <br>
    <div id="input0"></div>

    <input type="submit" value="Загрузить">
</form>

</body>
</html>