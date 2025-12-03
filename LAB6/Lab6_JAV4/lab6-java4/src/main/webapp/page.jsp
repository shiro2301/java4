<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Trang Chung</title></head>
<body>
    <h1>Xin chào</h1>
    
    <c:if test="${!empty sessionScope.user}">
        <p>User đang đăng nhập: 
            <strong>${sessionScope.user.hoVaTen}</strong>
            | 
            <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </p>
    </c:if>
    
    <hr>
    <h3>Menu Kiểm tra Phân quyền (Bài 4)</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/account/edit-profile">Edit Profile (Cần Login)</a></li>
        <li><a href="${pageContext.request.contextPath}/video/share/123">Share Video (Cần Login)</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/user">Quản trị User (Cần Admin)</a></li>
        <li><a href="${pageContext.request.contextPath}/account/sign-up">Sign Up (Không cần Login)</a></li>
    </ul>
    
</body>
</html>