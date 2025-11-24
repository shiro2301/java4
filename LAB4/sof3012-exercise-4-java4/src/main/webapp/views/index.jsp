<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Lab 4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #121212;
            color: #eaeaea;
        }

        .navbar {
            background-color: #1f1f1f;
            border-bottom: 1px solid #333;
        }

        .navbar a {
            color: #ff4b5c !important;
        }

        .welcome-box {
            background: #1c1c1c;
            border-left: 5px solid #ff4b5c;
            padding: 25px;
            border-radius: 8px;
        }

        .feature-item {
            background: #1a1a1a;
            border-left: 4px solid #ff4b5c;
            transition: .3s;
        }

        .feature-item:hover {
            background: #252525;
            transform: scale(1.02);
        }
    </style>
</head>

<body>

    <!-- 🔥 NAVBAR -->
    <nav class="navbar navbar-expand-lg px-4 py-3">
        <div class="container-fluid">

            <a class="navbar-brand fw-bold text-danger" href="#">OE Studio - LAB 4</a>

            <div class="d-flex">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span class="text-light me-3">
                            Xin chào, <b>${sessionScope.user.fullname}</b>
                        </span>

                        <a class="btn btn-outline-danger btn-sm" 
                           href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </c:when>

                    <c:otherwise>
                        <span class="text-secondary me-3">Bạn chưa đăng nhập.</span>
                        <a class="btn btn-outline-light btn-sm me-2"
                           href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </nav>


    <!-- 🔥 INTRO -->
    <div class="container mt-4">
        <div class="welcome-box">
            <h1 class="fw-bold">🔥 Chào mừng đến với Lab 4</h1>
            <p>Dự án thực hành JPQL, Servlet & JSP.</p>
        </div>

        <!-- 🔥 FEATURE LIST -->
        <h3 class="mt-4 mb-3">📌 Các Chức Năng Chính</h3>

        <ul class="list-group">

            <li class="list-group-item feature-item">
                <a href="${pageContext.request.contextPath}/video/search" class="text-decoration-none text-danger fw-bold">
                    🔍 Tìm kiếm Video theo Từ khóa
                </a>
                <p class="text-secondary small mb-0">Truy vấn bằng JPQL LIKE</p>
            </li>

            <li class="list-group-item feature-item">
                <a href="${pageContext.request.contextPath}/report/share" class="text-decoration-none text-danger fw-bold">
                    📊 Báo cáo Video được Chia sẻ
                </a>
                <p class="text-secondary small mb-0">Dùng COUNT, GROUP BY, DTO</p>
            </li>

            <li class="list-group-item feature-item">
                <a href="${pageContext.request.contextPath}/login" class="text-decoration-none text-danger fw-bold">
                    🔑 Đăng nhập bằng Email hoặc ID
                </a>
                <p class="text-secondary small mb-0">JPQL kết hợp điều kiện OR</p>
            </li>

        </ul>
    </div>


    <!-- BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>