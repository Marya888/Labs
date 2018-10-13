<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Hello World Sample
    </title>
</head>

<body>
    <%
        String name = request.getAttribute("find").toString();
    %>

    Hello, world ! I'm <%= name%>


</body>
</html>
