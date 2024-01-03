<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 1/3/2024
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../component/header/header.module.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/9136a03bcd.js" crossorigin="anonymous"></script>
</head>
<body style="background-color: #18191a">
<%@include file="../../component/header/Header.jsp" %>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-6">
            <h1 class="text-center text-white">Settings</h1>
            <div class="col-12 p-1 rounded mt-5 mb-5 bg-secondary">
                <form action="${pageContext.request.contextPath}/settings?action=updateAvatar"
                        method="post"
                        enctype="multipart/form-data"
                >
                    <div class="form-group">
                        <label for="avatar" class="form-label text-white">
                            Avatar</label>
                        <input type="file" class="form-control" id="avatar" name="avatar">
                    </div>
                    <div class="form-group text-white">
                        <button type="submit" class="btn btn-primary mt-2">Update</button>
                    </div>
                </form>
            </div>
            <div class="col-12 bg-secondary p-1 rounded mt-5 mb-5">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.error}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/settings?action=updatePassword">
                    <div class="form-group text-white">
                        <label for="oldPassword" class="form-label text-white">
                            Old Password</label>
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword">
                    </div>
                    <div class="form-group text-white">
                        <label for="newPassword" class="form-label text-white">
                            New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword">
                    </div>
                    <div class="form-group text-white">
                        <label for="confirmPassword" class="form-label text-white">
                            Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                        <button type="submit" class="btn btn-primary mt-2">Update</button>
                    </div>
                </form>
            </div>
            <div class="col-12 bg-secondary p-1 rounded mt-5 mb-5">
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.error}
                    </div>
                </c:if>
                <c:if test="${requestScope.success != null}">
                    <div class="alert alert-success" role="alert">
                        ${requestScope.success}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/settings?action=updateName"
                      method="post">
                    <div class="form-group text-white">
                        <label for="name" class="form-label text-white">
                            New Name</label>
                        <input type="text" class="form-control" id="name" name="fullName" value="${requestScope.user.fullName}">
                    </div>
                    <div class="form-group text-white">
                        <button type="submit" class="btn btn-primary mt-2"
                        >Update</button>
                    </div>
                </form>
            </div>
            <div class="col-12 bg-secondary p-1 rounded mt-5 mb-5">
                <form action="${pageContext.request.contextPath}/settings?action=updateInfo"
                      method="post">
                    <div class="form-group text-white">
                        <label for="phoneNumber" class="form-label text-white">
                            New Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${requestScope.user.phoneNumber}">
                    </div>
                    <div class="form-group text-white">
                        <label for="address" class="form-label text-white">
                            New Address</label>
                        <input type="text" class="form-control" id="address" name="address" value="${requestScope.user.address}">
                    </div>
                    <div class="form-group text-white">
                        <label for="dateOfBirth" class="form-label text-white">
                            New Date Of Birth</label>
                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${requestScope.user.dateOfBirth}">
                    </div>
                    <div class="form-group text-white">
                        <label for="bio" class="form-label text-white">
                            New Bio</label>
                        <input type="text" class="form-control" id="bio" name="bio" value="${requestScope.user.bio}">
                    </div>
                    <div class="form-group text-white">
                        <label for="interest" class="form-label text-white">
                            New Interest</label>
                        <input type="text" class="form-control" id="interest" name="interest" value="${requestScope.user.interests}">
                    </div>
                    <div class="form-group text-white">
                        <button type="submit" class="btn btn-primary mt-2"
                        >Update</button>
                    </div>
                </form>
            </div>

        </div>
    </div>

</div>
</body>
</html>