<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/11/2023
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="../../css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="register-form">
        <h2 class="text-center mt-5">Register</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" placeholder="Enter username" class="form-control" id="username" name="username" value="${requestScope.info.username}">
                <c:if test="${not empty requestScope.errorUsername}">
                    <span class="text-danger">${requestScope.errorUsername}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" placeholder="Enter full name" class="form-control" id="fullName" name="fullName" value="${requestScope.info.fullName}">
                <c:if test="${not empty requestScope.errorFullName}">
                    <span class="text-danger">${requestScope.errorFullName}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" placeholder="Enter email" class="form-control" id="email" name="email" value="${requestScope.info.email}">
                <c:if test="${not empty requestScope.errorEmail}">
                    <span class="text-danger">${requestScope.errorEmail}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" placeholder="Enter password" class="form-control" id="password" name="password" value="${requestScope.info.password}">
                <c:if test="${not empty requestScope.errorPassword}">
                    <span class="text-danger">${requestScope.errorPassword}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" placeholder="Enter confirm password" class="form-control" id="confirmPassword"
                       name="confirmPassword" value="${requestScope.confirmPassword}">
                <c:if test="${not empty requestScope.errorConfirmPassword}">
                    <span class="text-danger">${requestScope.errorConfirmPassword}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" placeholder="Enter phone" class="form-control" id="phone" name="phone" value="${requestScope.info.phoneNumber}">
                <c:if test="${not empty requestScope.errorPhone}">
                    <span class="text-danger">${requestScope.errorPhone}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" placeholder="Enter address" class="form-control" id="address" name="address" value="${requestScope.info.address}">
                <c:if test="${not empty requestScope.errorAddress}">
                    <span class="text-danger">${requestScope.errorAddress}</span>
                </c:if>
            </div>
            <div class="d-gird justify-content-center">
                <button type="submit" class="btn btn-primary btn-block"
                >Register
                </button>
            </div>
            <div class="d-gird justify-content-center mt-2 d-flex flex-column align-items-center">
                <a href="${pageContext.request.contextPath}/login">Already have an account? Login here</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>
