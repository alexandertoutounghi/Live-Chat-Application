<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="download" scope="request" type="java.util.List"/>--%>
<h1>hello</h1>
<c:forEach items="${download}" var="item">
    ${item}
</c:forEach>