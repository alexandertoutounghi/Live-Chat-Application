<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" %>

<c:choose>
    <c:when test="${isXml}">
        <%response.setContentType("text/xml");%>
    </c:when>

    <c:otherwise>
        <%response.setContentType("text/plain");%>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${isXml}">
        <?xml version="1.0" encoding="UTF-8"?>
        <MessageBox>
            <c:forEach items="${download}" var="item">
                <Message>
                ${item}
                </Message>
            </c:forEach>
        </MessageBox>

    </c:when>
    <c:otherwise>
        <c:forEach items="${download}" var="item">
            ${item}
        </c:forEach>
    </c:otherwise>
</c:choose>