<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head><title>Tìm kiếm Video</title></head>
<body>
    <h2>🔍 Tìm kiếm Video</h2>
    <form action="search" method="get">
        <label for="keyword">Từ khóa:</label>
        <input type="text" id="keyword" name="keyword" value="${keyword}">
        <button type="submit">Tìm</button>
    </form>
    
    <c:if test="${not empty videoList}">
        <h3>Kết quả tìm kiếm cho "${keyword}"</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Tiêu đề video</th>
                    <th>Số lượt thích</th>
                    <th>Còn hiệu lực</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="video" items="${videoList}">
                    <tr>
                        <td>${video.title}</td>
                        <td>N/A (Cần truy vấn thêm)</td> 
                        <td>${video.isActive ? 'Có' : 'Không'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>