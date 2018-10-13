<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результат</title>
    <link rel="stylesheet" href="ret3.css">
    <!--    <script type="text/javascript" charset="utf-8" src="ajax.js" defer></script>  -->
</head>


<body>

    <table>
        <caption>Данные и результаты</caption>
        <thead>
        <tr>
            <th id="foo1" style="color:green">x</th>
            <th id="foo2" style="color:green">y</th>
            <th id="foo3" style="color:green">R</th>
            <th id="foo4" style="color:#A52A2A">Результат</th>
            <th id="foo5" style="color:blue">Текущее время</th>
            <th id="foo6" style="color:blue">Время работы (мкс)</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%=request.getAttribute("x")%></td>
            <td><%=request.getAttribute("y")%></td>
            <td><%=request.getAttribute("r")%></td>
            <td><%=request.getAttribute("find")%></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>


</body>
</html>


