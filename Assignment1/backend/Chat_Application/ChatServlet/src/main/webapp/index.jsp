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

<c:forEach items="${chat}" var="item">
   ${item}
</c:forEach>
