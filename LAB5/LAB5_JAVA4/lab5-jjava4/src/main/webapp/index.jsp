<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ</title>
</head>
<body>
    <header style="background-color: #eee; padding: 10px; margin-bottom: 20px;">
        <c:if test="${!empty sessionScope.user}">
            Xin chào, <strong>${sessionScope.user}</strong>.
            | <a href="logout">Đăng xuất</a> 
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <a href="login.jsp">Đăng nhập</a>
        </c:if>

        <p>Số lượt khách viếng thăm: <strong>${applicationScope.visitors}</strong></p>
        
        <hr>
    </header>

    <h2>Nội dung chính của website</h2>
    <p style="color: green;">${requestScope.message}</p>
</body>
</html>