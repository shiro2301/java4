<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập | OE Studio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #121212;
            color: white;
        }

        .login-box {
            background: #1d1d1d;
            border-radius: 10px;
            padding: 35px;
            width: 380px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.1);
        }

        .btn-login {
            background-color: #ff4b5c;
            border: none;
        }

        .btn-login:hover {
            background-color: #ff2e41;
        }

        .text-link {
            color: #ff4b5c;
            text-decoration: none;
        }

        .text-link:hover {
            text-decoration: underline;
        }
    </style>

</head>
<body class="d-flex justify-content-center align-items-center vh-100">

    <div class="login-box">

        <h2 class="text-center fw-bold mb-4">Đăng nhập OE Studio</h2>

        <!-- Hiện lỗi nếu có -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">
                ${error}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="mb-3">
                <label class="form-label">Tài khoản (ID hoặc Email)</label>
                <input type="text" name="idOrEmail" class="form-control bg-dark text-light border-secondary"
                       required value="${idOrEmail != null ? idOrEmail : ''}">
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" name="password" class="form-control bg-dark text-light border-secondary" required>
            </div>

            <button type="submit" class="btn btn-login w-100 py-2 mt-2 text-white fw-bold">
                Đăng nhập
            </button>
        </form>

        <p class="text-center mt-3">
            Chưa có tài khoản? 
            <a class="text-link fw-bold" href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
        </p>

    </div>

    <!-- BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
