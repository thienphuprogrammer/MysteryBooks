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
    <body style="background-color: #ffffff">
        <%@include file="../../component/header/Header.jsp" %>
        <section>
            <div class="container py-5">
                <c:if test="${requestScope.notifications != null && !empty(requestScope.notifications)}">
                    <c:forEach items="${requestScope.notifications}" var="noti" varStatus="status">
                        <li class="p-2 border-bottom" style="background-color: #eee; list-style-type: none">
                            <div class="card">
                                <c:choose>
                                    <c:when test="${noti.type == 'message'}">
                                        <a href="${pageContext.request.contextPath}/messages?friendId=${noti.senderId}" class="text-decoration-none text-dark">
                                            <div class="d-flex justify-content-between align-items-center" style="padding: 10px">
                                                <div class="d-flex align-items-center">
                                                    <span class="fw-bold">
                                                            New message
                                                    </span>
                                                    <span class="float-end" style="margin-left: 20px">
                                                    <i class="fas fa-clock"></i>
                                                    ${noti.creationDate}
                                                </span>
                                                </div>
                                                <p class="card-text">${noti.content}</p>
                                            </div>
                                        </a>
                                    </c:when>
                                    <c:when test="${noti.type == 'comment'}">
                                        <a href="#" class="text-decoration-none text-dark">
                                            <div class="d-flex justify-content-between align-items-center" style="padding: 10px">
                                                <div class="d-flex align-items-center">
                                                    <span class="fw-bold">
                                                            New comment
                                                    </span>
                                                    <span class="float-end" style="margin-left: 20px">
                                                    <i class="fas fa-clock"></i>
                                                    ${noti.creationDate}
                                                </span>
                                                </div>
                                                <p class="card-text">${noti.content}</p>
                                            </div>
                                        </a>
                                    </c:when>
                                    <c:when test="${noti.type == 'friend_request'}">
                                        <div class="d-flex justify-content-between align-items-center" style="padding: 10px">
                                            <div class="d-flex align-items-center">
                                                    <span class="fw-bold">
                                                            New friend request
                                                    </span>
                                                <span class="float-end" style="margin-left: 20px">
                                                    <i class="fas fa-clock"></i>
                                                    ${noti.creationDate}
                                                </span>
                                            </div>
                                            <p class="card-text">${noti.content}</p>
                                        </div>
                                    </c:when>
                                    <c:when test="${noti.type == 'emotion'}">
                                        <div class="d-flex justify-content-between align-items-center" style="padding: 10px">
                                            <div class="d-flex align-items-center">
                                                    <span class="fw-bold">
                                                            New emotion
                                                    </span>
                                                <span class="float-end" style="margin-left: 20px">
                                                    <i class="fas fa-clock"></i>
                                                    ${noti.creationDate}
                                                </span>
                                            </div>
                                            <p class="card-text">${noti.content}</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </div>
        </section>
    </body>
</html>
