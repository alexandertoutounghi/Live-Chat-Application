<%--
  Created by IntelliJ IDEA.
  User: ZaynM
  Date: 2020-09-28
  Time: 6:39 p.m.
  To change this template use File | Settings | File Templates.


  This is just a simple jsp file to test functionality of ChatServlet and ChatManaget
  Delete this when we know code works.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="chatmanager.ChatManager" %>

<html>
<head>
    <title>Chat</title>
</head>
<body>
    <h1>Chat Test</h1>

    <%--This is used to test the postMessages method--%>
    <form action="ChatServlet" method="post">
        <textarea name="chat" rows="25" cols="50" value="${chat}">
            <c:forEach items="${chat}" var="item">
               ${item}
            </c:forEach>
        </textarea>
        <br/>
        Username:
        <input type="text" name="user" value="user">
        <br/>
        Message:
        <input type="text" name="msg" value="msg">
        <button>Send</button>
    </form>

    <%--This is used to test the listMessages method--%>
    <br/>
    <form action="ChatServlet" method="get">
        From: <br/>
        <input type="text" name="from">
        <br/>

        To: <br/>
        <input type="text" name="to">
        <br/>

        Format: <br/>
        <select name="format">
            <option>plain</option>
            <option>xml</option>
        </select>
        <br/>

        <br/>
        <button>List Messages</button>
    </form>
</body>
</html>
