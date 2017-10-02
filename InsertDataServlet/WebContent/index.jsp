<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>輸入店家至資料庫</title>
    <script>
        function show(){
            var result = document.getElementById("show");
            result.innerHTML = "";
            result.innerHTML += "<form action = 'InsertServlet' method = 'post'><p>輸入店名:<input type = text name = 'name'/></p><p>輸入地址:<input type = text name = 'address'/></p><p>輸入敘述:<input type = text name = 'des' style = 'width:80%;'/></p><p>輸入類別(01.異國料理、02.火烤料理、03.中式美食、04.夜市小吃、05.甜點冰品、06.伴手禮、07.地方特產、08.素食、09.其他):<input type = text name = 'kind'/></p><p>輸入營業時間:<input type = text name = 'opt'/></p><p>輸入經度(小數點六位):<input type = text name = 'px'/></p><p>輸入緯度(小數點六位):<input type = text name = 'py'/></p><p>輸入店家電話:<input type = text name = 'tel'/></p><p>輸入店家網站或Facebook:<input type = text name = 'web'/></p><input type = submit value = '輸入'/></form>"
        }
    </script>
</head>
<body onload="show()">
    <div id = "show"></div>
</body>
</html>