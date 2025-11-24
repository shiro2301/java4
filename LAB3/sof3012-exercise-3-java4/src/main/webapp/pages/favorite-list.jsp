<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%--
  Sửa lỗi JSTL URI:
  URI "http://jakarta.apache.org/taglibs/standard-rt" là của JSTL 1.0 (rất cũ).
  Chúng ta đang dùng JSTL 3.0 (từ pom.xml), URI chuẩn phải là "jakarta.tags.core".
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favorite Videos</title>

    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">

    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>

<main class="container">
    <h2>Danh sách video đã được yêu thích</h2>

    <%--
      Kiểm tra xem list 'favorites' có rỗng hay không.
      Đây là servlet 'FavoriteListAllServlet' (Bài 4)
    --%>
    <c:if test="${not empty favorites}">
        <table class="table table-striped table-hover table-bordered shadow-sm">
            <caption class="caption-top">Tổng cộng ${favorites.size()} lượt yêu thích</caption>
            <thead class="table-dark">
            <tr>
                <th scope="col">Video Title</th>
                <th scope="col">Người thích</th>
                <th scope="col">Ngày thích</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="f" items="${favorites}">
                <tr>
                    <%-- Favorite.getVideo().getTitle() --%>
                    <td>${f.video.title}</td>

                    <%-- Favorite.getUser().getFullname() --%>
                    <td>${f.user.fullname}</td>

                    <%-- Favorite.getLikeDate() - Định dạng lại ngày cho đẹp --%>
                    <td>
                        <fmt:formatDate value="${f.likeDate}" pattern="dd/MM/yyyy"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <%--
      Kiểm tra xem có 'user' hay không.
      Đây là servlet 'FavoriteListServlet' (Bài 3)
    --%>
    <c:if test="${not empty user}">
        <h3 class="h4">Video yêu thích của: <strong>${user.fullname}</strong></h3>
        <ul class="list-group">
            <c:forEach var="fav" items="${user.favorites}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    ${fav.video.title}
                    <span class="badge bg-primary rounded-pill">
                        <fmt:formatDate value="${fav.likeDate}" pattern="dd/MM/yyyy"/>
                    </span>
                </li>
            </c:forEach>
            <c:if test="${empty user.favorites}">
                <li class="list-group-item">Người dùng này chưa thích video nào.</li>
            </c:if>
        </ul>
    </c:if>

    <%-- Xử lý trường hợp không tìm thấy user từ Bài 3 --%>
    <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </c:if>

</main>

<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>

</body>
</html>