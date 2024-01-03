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
        <section style="background-color: #eee;">
            <div class="container py-5">

                <div class="row">

                    <div class="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0">

                        <h5 class="font-weight-bold mb-3 text-center text-lg-start">Member</h5>

                        <div class="card">
                            <div class="card-body">
                                <ul class="list-unstyled mb-0" style="overflow-y: scroll; height: 500px;">
                                    <c:if test="${requestScope.friends != null && !empty(requestScope.friends)}">
                                        <c:forEach items="${requestScope.friends}" var="friend" varStatus="status">
                                            <li class="p-2 border-bottom" style="background-color: #eee;">
                                                <a href="${pageContext.request.contextPath}/messages?friendId=${friend.friendId}"
                                                   class="d-flex justify-content-between">
                                                    <div class="d-flex flex-row">
                                                        <img src="${friend.avatar}" alt="avatar"
                                                             class="rounded-circle d-flex align-self-center me-3 shadow-1-strong" width="60">
                                                        <div class="pt-1">
                                                            <p class="fw-bold mb-0">${friend.fullName}</p>
                                                            <p class="small text-muted">${friend.content}</p>
                                                        </div>
                                                    </div>
                                                    <div class="pt-1">
                                                        <p class="small text-muted mb-1">${friend.sentDate}</p>
<%--                                                        <span class="badge bg-danger float-end">1</span>--%>
                                                    </div>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-7 col-xl-8">
                        <ul class="list-unstyled" style="background-color: #eee; overflow-y: scroll; height: 500px;" id="message">
                            <c:choose>
                                <c:when test="${requestScope.messages != null && !empty(requestScope.messages)}">
                                    <c:forEach items="${requestScope.messages}" var="message" varStatus="status">
                                        <c:if test="${message.isFriend == 1}">
                                            <li class="d-flex justify-content-end mb-4">
                                                <img src="${message.avatar}" alt="avatar"
                                                     class="rounded-circle d-flex align-self-start me-3 shadow-1-strong" width="60">
                                                <div class="card">
                                                    <div class="card-header d-flex justify-content-between p-3">
                                                        <p class="fw-bold mb-0">${message.fullName}</p>
                                                        <p class="text-muted small mb-0 text-end" style="margin-left: 50px;">
                                                            <i class="far fa-clock"></i>${message.sentDate}</p>
                                                    </div>
                                                    <div class="card-body">
                                                        <p class="mb-0" style="max-width: 80%;">
                                                                ${message.content}
                                                        </p>
                                                        <form action="${pageContext.request.contextPath}/messages?action=delete"
                                                              method="post">
                                                            <input type="hidden" name="id" value="${message.id}">
                                                            <input type="hidden" name="friendId" value="${requestScope.friendId}">
                                                            <button type="submit" class="btn btn-danger btn-rounded float-end"
                                                            style="margin-top: 10px;">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </button>
                                                        </form>

                                                    </div>
                                                </div>
                                            </li>
                                        </c:if>
                                        <c:if test="${message.isFriend == 0}">
                                            <li class="d-flex justify-content-end mb-4 flex-row-reverse">
                                                <img src="${message.avatar}" alt="avatar"
                                                     class="rounded-circle d-flex align-self-start me-3 shadow-1-strong" width="60">
                                                <div class="card">
                                                    <div class="card-header d-flex justify-content-between p-3">
                                                        <p class="fw-bold mb-0">${message.fullName}</p>
                                                        <p class="text-muted small mb-0 text-end" style="margin-left: 50px;">
                                                            <i class="far fa-clock"></i>${message.sentDate}</p>
                                                    </div>
                                                    <div class="card-body">
                                                        <p class="mb-0" style="max-width: 80%;">
                                                                ${message.content}
                                                        </p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li class="d-flex justify-content-end mb-4 flex-row-reverse">
                                        <span class="badge bg-danger float-end mt-2 me-3 mb-3" style="font-size: 20px;">
                                            No message</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                        <ul class="list-unstyled">
                            <form action="${pageContext.request.contextPath}/messages?action=create"
                                  method="post">
                                <input type="hidden" name="friendId" value="${requestScope.friendId}">
                                <li class="bg-white mb-3">
                                    <div class="form-outline">
                                        <textarea class="form-control" id="textAreaExample2" rows="4" name="content"
                                        ></textarea>
                                        <label class="form-label" for="textAreaExample2">Message</label>
                                    </div>
                                </li>
                                <button type="submit" class="btn btn-info btn-rounded float-end"
                                >Send</button>
                            </form>
                        </ul>
                    </div>

                </div>

            </div>
        </section>
    <script>
        function loadMessage() {
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200){
                    document.getElementById("message").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", "message.jsp", true);
            xhttp.send();
        }
        setInterval(function () {
            loadMessage();
        }, 1000);

        function autoScroll() {
            const objDiv = document.getElementById("message");
            objDiv.scrollTop = objDiv.scrollHeight;
        }
        autoScroll();
    </script>
    </body>
</html>
