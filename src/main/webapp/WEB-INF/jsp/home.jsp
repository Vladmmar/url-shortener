<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>URL shortener</h1>

<c:if test="${sessionScope.user == null}">
    <h3>Log in or Sign up to use the service</h3>
    <a href="${pageContext.request.contextPath}/login">Log in</a>
    <a href="${pageContext.request.contextPath}/register">Sign up</a>
</c:if>

<c:if test="${sessionScope.user != null}">
    <a href="${pageContext.request.contextPath}/create">Shorten your link</a>
</c:if>
</body>
</html>
