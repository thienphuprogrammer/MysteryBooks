<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/29/2023
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Friend Dashboard</title>
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
        <div class="container mt-5 bg-dark p-5 text-white rounded-3 d-flex flex-column align-items-center"
             style="border-radius: 10px 10px 0 0; min-height: 100vh; max-width: 1000px">
            <div class="title fw-bold fs-1 text-center mb-5">Friend Request</div>
            <div class="d-flex flex-wrap align-items-start justify-content-center">
                <c:if test="${requestScope.listFriendRequest != null && !empty(requestScope.listFriendRequest)}">
                    <c:forEach items="${requestScope.listFriendRequest}" var="request" varStatus="status">
                        <div class="cart p-3 m-3"
                             style="border-radius: 10px; background-color: #2b2c2d; max-width: 200px">
                            <img src="https://picsum.photos/200/300" alt="" class="card-img-top" style="max-width: 200px; max-height: 200px; object-fit: cover">
                            <div class="card-body">
                                <div class="card-title fw-bold fs-5 text-truncate">
                                    <a href="/user/${request.id}" class="text-decoration-none text-white"
                                    >${request.fullName}</a>
                                </div>
                                <form action="${pageContext.request.contextPath}/friends"
                                      method="post"
                                      class="d-flex flex-column align-items-center mt-3">
                                    <input type="hidden" name="id" value="${request.id}">
                                    <input type="hidden" name="senderId" value="${request.senderId}">
                                    <input type="hidden" name="receiverId" value="${request.receiverId}">
                                    <input type="hidden" name="tab" value="accept">
                                    <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                    >Accept</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/friends"
                                      method="post"
                                      class="d-flex flex-column align-items-center mt-3">
                                    <input type="hidden" name="id" value="${request.id}">
                                    <input type="hidden" name="senderId" value="${request.senderId}">
                                    <input type="hidden" name="receiverId" value="${request.receiverId}">
                                    <input type="hidden" name="tab" value="decline">
                                    <button type="submit" class="btn btn-danger w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                    >Decline</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="title fw-bold fs-1 text-center mb-5">List User</div>
            <div class="d-flex flex-wrap align-items-start justify-content-center">
                <c:if test="${requestScope.listUser != null && !empty(requestScope.listUser)}">
                    <c:forEach items="${requestScope.listUser}" var="user" varStatus="status">
                        <div class="cart p-3 m-3"
                             style="border-radius: 10px; background-color: #2b2c2d; max-width: 200px">
                            <img src="https://picsum.photos/200/300" alt="" class="card-img-top" style="max-width: 200px; max-height: 200px; object-fit: cover">
                            <div class="card-body">
                                <div class="card-title fw-bold fs-5 text-truncate">
                                    <a href="${pageContext.request.contextPath}/profile?id=${user.id}"
                                       class="text-decoration-none text-white"
                                    >${user.fullName}</a>
                                </div>
                                <form action="${pageContext.request.contextPath}/friends"
                                      method="post"
                                      class="d-flex flex-column align-items-center mt-3">
                                    <input type="hidden" name="friendId" value="${user.id}">
                                    <c:if test="${user.statusRequestFriend == 0}">
                                        <input type="hidden" name="tab" value="add">
                                        <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                        >Add Friend</button>
                                    </c:if>
                                    <c:if test="${user.statusRequestFriend == 1}">
                                        <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                                disabled
                                        >Waiting for accept</button>
                                        <input type="hidden" name="tab" value="cancel">
                                        <button type="submit" class="btn btn-danger w-100 d-flex align-items-center justify-content-center mt-2" style="border-radius: 10px"
                                        >Cancel</button>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </body>
</html>
