<%@ page isELIgnored="false" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>User CRUD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
    <i>${message}</i>
    <c:url var="url" value="/user/crud"/>
  <form method="post">
    Id: <input name="id" value="${user.id}">
    Password: <input name="password" type="password" value="${user.password}"><br>
    Fullname: <input name="fullname" value="${user.fullname}">
    Email Address: <input name="email" value="${user.email}"><br>
    Role:
    <input name="admin" type="radio" value="true" ${user.admin?'checked':''}> Admin
    <input name="admin" type="radio" value="false" ${user.admin?'':'checked'}> User
    <hr>
    <button formaction="${url}/create">Create</button>
    <button formaction="${url}/update">Update</button>
    <button formaction="${url}/delete">Delete</button>
    <button formaction="${url}/reset">Reset</button>
  </form>

  <table>
    <thead>
      <tr>
        <th>Id</th><th>Password</th><th>Fullname</th>
        <th>Email</th><th>Role</th><th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="u" items="${users}">
        <tr>
          <td>${u.id}</td>
          <td>${u.password}</td>
          <td>${u.fullname}</td>
          <td>${u.email}</td>
          <td>${u.admin?'Admin':'User'}</td>
          <td><a href="${url}/edit/${u.id}">Edit</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
