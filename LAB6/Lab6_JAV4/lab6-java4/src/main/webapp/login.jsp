<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/type.css">
</head>
<body>
    <div class="container">
        <h2>Đăng nhập Hệ thống</h2>
        <c:url var="url" value="/login"/> 
        
        <c:if test="${requestScope.authError != null}">
            <i class="error-message">${requestScope.authError}</i>
        </c:if>
        
        <c:if test="${requestScope.message != null}">
            <i class="error-message">${requestScope.message}</i>
        </c:if>
        
        <form action="${url}" method="post">
            <label for="username">Username:</label>
            <input name="username" id="username" type="text"><br>
            <label for="password">Password:</label>
            <input name="password" id="password" type="password"><hr>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>