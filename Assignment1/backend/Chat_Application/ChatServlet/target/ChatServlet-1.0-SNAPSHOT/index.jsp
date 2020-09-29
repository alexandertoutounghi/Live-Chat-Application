<%--
  Created by IntelliJ IDEA.
  User: ZaynM
  Date: 2020-09-28
  Time: 6:39 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
    <h1>Chat Test</h1>
    <textarea readonly id="chatwindow" name="cat" rows="50" cols="50">
    </textarea>

    <form action="./index.jsp" method="POST">
        Username:
        <input type="text" name="user">
        <br/>
        Message:
        <input type="text" name="msg">
        <button>Send</button>
    </form>
</body>
</html>
