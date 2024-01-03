<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/11/2023
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <input type="text" placeholder="Enter username" class="form-control" id="username" name="username">
                <span class="text-danger">${errorUsername}</span>
            </div>
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" placeholder="Enter full name" class="form-control" id="fullName" name="fullName">
                <span class="text-danger">${errorFullName}</span>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" placeholder="Enter email" class="form-control" id="email" name="email">
                <span class="text-danger">${errorEmail}</span>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" placeholder="Enter password" class="form-control" id="password" name="password">
                <span class="text-danger">${errorPassword}</span>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" placeholder="Enter confirm password" class="form-control" id="confirmPassword"
                       name="confirmPassword">
                <span class="text-danger">${errorConfirmPassword}</span>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" placeholder="Enter phone" class="form-control" id="phone" name="phone">
                <span class="text-danger">${errorPhone}</span>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" placeholder="Enter address" class="form-control" id="address" name="address">
                <span class="text-danger">${errorAddress}</span>
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
