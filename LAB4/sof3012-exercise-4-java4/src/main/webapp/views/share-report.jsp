<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Báo cáo Chia sẻ Video | OE Studio</title>

    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #121212;
            color: #ffffff;
        }
        .table-wrapper {
            background: #1d1d1d;
            padding: 25px;
            border-radius: 8px;
        }
        th {
            background-color: #242424 !important;
        }
        .badge-count {
            background-color: #ff1744;
        }
    </style>
</head>

<body class="p-5">

    <div class="container">

        <h2 class="fw-bold text-danger mb-4">📊 Báo cáo Chia sẻ Video</h2>

        <div class="table-wrapper">
            <table class="table table-dark table-hover table-striped">

                <thead>
                    <tr>
                        <th>Tiêu đề Video</th>
                        <th>Lượt Chia sẻ</th>
                        <th>Ngày chia sẻ đầu tiên</th>
                        <th>Ngày chia sẻ gần nhất</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="report" items="${reportList}">
                        <tr>
                            <td>${report.videoTitle}</td>

                            <td>
                                <span class="badge badge-count">
                                    ${report.shareCount}
                                </span>
                            </td>

                            <td>
                                <fmt:formatDate value="${report.firstShareDate}" pattern="dd/MM/yyyy" />
                            </td>

                            <td>
                                <fmt:formatDate value="${report.lastShareDate}" pattern="dd/MM/yyyy" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
