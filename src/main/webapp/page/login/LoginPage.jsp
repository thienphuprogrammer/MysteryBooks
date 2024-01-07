<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/8/2023
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/login.css">
</head>
<body>
<div class="container">
    <div class="login-form">
        <h2 class="text-center mb-4">Login</h2>
        <c:if test="${requestScope.error != null && !empty(requestScope.error)}">
            <div class="alert alert-danger" role="alert">
                    ${requestScope.error}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Username or Email</label>
                <input type="text" class="form-control" id="username" name="username"
                       placeholder="Enter username or email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
            </div>
            <div class="form-group">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="remember_me" name="remember_me">
                    <label for="remember_me" class="form-check-label">Remember me</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
            <div class="mt-3 flex-row d-flex justify-content-around">
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/register">Register</a>
                </div>
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/forgot-password">Forgot password</a>
                </div>
            </div>

        </form>
    </div>
</div>
</body>
</html>
