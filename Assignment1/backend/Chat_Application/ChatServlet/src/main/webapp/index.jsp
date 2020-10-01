<%--
  Created by IntelliJ IDEA.
  User: ZaynM
  Date: 2020-09-28
  Time: 6:39 p.m.
  To change this template use File | Settings | File Templates.


  This is just a simple jsp file to test functionality of ChatServlet and ChatManaget
  Delete this when we know code works.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Chat</title>
</head>
<body>
    <h1>Chat Test</h1>
    <form action="ChatServlet" method="post">
        <textarea name="chatBox" rows="25" cols="25" value="<%=request.getAttribute("chatBox")%>" >
        </textarea>
        <br/>
        Username:
        <input type="text" name="user" value="<%=request.getAttribute("user")%>">
        <br/>
        Message:
        <input type="text" name="msg">
        <button>Send</button>
    </form>
</body>
</html>
