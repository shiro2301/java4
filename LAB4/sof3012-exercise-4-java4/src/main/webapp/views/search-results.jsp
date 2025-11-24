<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bài 3: Tìm kiếm Video</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>LAB 4: LẬP TRÌNH JAVA #4 - Bài 3</h1>
    
    <h2>🔍 Trang Tìm kiếm Video theo Từ khóa</h2>

    <form action="video/search" method="get">
        <label for="keyword">Từ khóa:</label>
        <input type="text" id="keyword" name="keyword" 
               value="${keyword != null ? keyword : ''}" 
               placeholder="Nhập tiêu đề video..." required>
        <button type="submit">Tìm</button>
    </form>
    
    <hr>
    
    <c:if test="${keyword != null}">
        <h3>Kết quả: ${videoList.size()} video được tìm thấy</h3>
    </c:if>

    <c:choose>
        <c:when test="${not empty videoList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Tiêu đề video </th>
                        <th>Số lượt thích </th>
                        <th>Còn hiệu lực </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="video" items="${videoList}">
                        <tr>
                            <td>${video.title}</td>
                            <td>${video.favorites.size()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${video.isActive}">Có</c:when>
                                    <c:otherwise>Không</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        
        <c:when test="${keyword != null}">
             <p style="color: red;">Không tìm thấy video nào cho từ khóa "${keyword}".</p>
        </c:when>
        
        <c:otherwise>
             <p>Vui lòng nhập từ khóa để bắt đầu tìm kiếm.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>