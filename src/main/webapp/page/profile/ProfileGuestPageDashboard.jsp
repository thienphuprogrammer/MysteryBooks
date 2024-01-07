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
  <div class="container mb-3">
    <div class="row">
      <div class="col-12">
        <div class="card mt-3 mb-3 border-2 border rounded-3 p-3 border-secondary shadow-lg"
             >
          <div class="card-body">
            <div class="d-flex flex-row align-items-center">
              <div class="avatar">
                <img src="${requestScope.user.profilePicture}" alt="" class="rounded-circle"
                     style="width: 300px; height: 300px; object-fit: cover">
              </div>
              <div class="info d-flex flex-column ms-3">
                 <span class="text-center ms-3 align-self-center">
                     <h3 class="text-center ms-3 align-self-center">
                         <a class="text-decoration-none text-primary text-center" style="font-size: 60px"
                            href="${pageContext.request.contextPath}/profile?id=${requestScope.user.id}">
                             ${requestScope.user.fullName}
                         </a>
                     </h3>
                 </span>
                <div class="d-flex mt-3 justify--center flex-column">
                  <div class="d-flex flex-row align-items-center mt-3">
                    <i class="fas fa-phone-alt"></i>
                    <span class="ms-1">${requestScope.user.phoneNumber}</span>
                  </div>
                  <div class="d-flex flex-row align-items-center mt-3">
                    <i class="fas fa-birthday-cake"></i>
                    <span class="ms-1">${requestScope.user.dateOfBirth}</span>
                  </div>
                  <div class="d-flex flex-row align-items-center mt-3">
                    <i class="fas fa-map-marker-alt"></i>
                      <span class="ms-1">${requestScope.user.address}</span>
                  </div>
                  <div class="d-flex flex-row align-items-center mt-3">
                      <i class="fas fa-info-circle"></i>
                      <span class="ms-1">Bio:</span>
                      <span class="ms-1">${requestScope.user.bio}</span>
                  </div>
                    <div class="d-flex flex-row align-items-center mt-3">
                        <i class="fas fa-info-circle"></i>
                        <span class="ms-1">Interests:</span>
                        <span class="ms-1">${requestScope.user.interests}</span>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container  mt-5 d-flex flex-row">
    <div class="col col-4 d-flex justify-content-center">
      <div class="row mt-3 w-100 d-flex flex-row justify-content-center border border-2 border-secondary rounded-3 p-3 shadow-lg"
           style="min-height: 100vh; background-color: #ffffff" >
        <div class="col-12">
          <c:if test="${requestScope.friends == null || empty(requestScope.friends)}">
            <span class="">No friend</span>
          </c:if>
          <c:if test="${requestScope.friends != null && !empty(requestScope.friends)}">
            <c:forEach items="${requestScope.friends}" var="friend" varStatus="status">
              <div class="card mt-3 mb-3 d-flex border-2 border rounded-3 p-3 border-secondary shadow-lg"
                   style="background-color: #ffffff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5);">
                <div class="card-body">
                  <div class="d-flex flex-row align-items-center">
                    <div class="avatar">
                      <img src="${friend.friendAvatar}" alt="" class="rounded-circle"
                           style="width: 70px; height: 70px; object-fit: cover">
                    </div>
                    <div class="info d-flex flex-column ms-3">
                      <h3 class="text-center ms-3 align-self-center">
                        <a class="text-decoration-none text-dark"
                           href="${pageContext.request.contextPath}/profile?id=${friend.friendId}">
                          ${friend.fullName}
                        </a>
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </c:if>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="row" style="max-width: 700px; min-height: 100vh; margin: auto">
        <div class="col-12">
          <c:if test="${requestScope.posts != null && !empty(requestScope.posts)}">
            <c:forEach items="${requestScope.posts}" var="post" varStatus="status">
              <div class="card mt-3 mb-3 d-flex flex-column text-dark"
                   style="background-color: #ffffff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5);">
                <div class="card-header d-flex flex-row align-items-center">
                  <div class="avatar">
                    <img src="${post.urlAvatar}" alt="" class="rounded-circle" width="50px"
                         height="50px">
                  </div>
                  <div class="info d-flex flex-column">
                    <h3 class="text-center ms-3 align-self-center">
                      <a class="text-decoration-none text-primary"
                         href="${pageContext.request.contextPath}/profile?id=${post.userId}">
                          ${post.fullName}
                      </a>
                    </h3>
                    <div class="date ms-3 align-self-center">
                                    <span>
                                        ${post.creationDate}
                                    </span>
                      <c:if test="${post.visibility == 'public'}">
                        <i class="fas fa-globe-americas"></i>
                      </c:if>
                      <c:if test="${post.visibility == 'friends'}">
                        <i class="fas fa-user-friends"></i>
                      </c:if>
                      <c:if test="${post.visibility == 'only_me'}">
                        <i class="fas fa-lock"></i>
                      </c:if>
                    </div>
                  </div>
                </div>
                <div class="card-body">
                  <c:if test="${post.title != null && !empty(post.title)}">
                    <h5 class="card-title">${post.title}</h5>
                  </c:if>
                  <c:if test="${post.content != null && !empty(post.content)}">
                    <p>${post.content}</p>
                  </c:if>
                  <c:if test="${post.images != null && !empty(post.images)}">
                    <div id="listImageContent${post.id}" class="carousel slide d-flex"
                         style="max-width: 90% ; margin: auto;"
                         data-bs-ride="carousel">
                      <div class="carousel-indicators">
                        <c:forEach begin="0" end="${fn:length(post.images) - 1}" var="index">
                          <c:if test="${index == 0}">
                            <button type="button" data-bs-target="#listImageContent${post.id}"
                                    data-bs-slide-to="${index}" class="active"
                                    aria-current="true" aria-label="Slide ${index + 1}"></button>
                          </c:if>
                          <c:if test="${index != 0}">
                            <button type="button" data-bs-target="#listImageContent${post.id}"
                                    data-bs-slide-to="${index}"
                                    aria-label="Slide ${index + 1}"></button>
                          </c:if>
                        </c:forEach>
                      </div>

                      <div class="carousel-inner mt-3 align-self-center d-flex"
                           style="width: 80%; height: 400px; margin: auto;">
                        <c:forEach items="${post.images}"
                                   var="img" varStatus="status">
                          <c:if test="${status.index == 0}">
                            <div class="carousel-item active align-self-center">
                              <img src="../${img.urlImg}"
                                   class="d-block d-flex justify-content-center"
                                   alt="..."
                                   style="max-height: 100%; max-width: 100%; margin: auto">
                            </div>
                          </c:if>
                          <c:if test="${status.index != 0}">
                            <div class="carousel-item align-self-center">
                              <img src="../${img.urlImg}"
                                   class="d-block d-flex justify-content-center"
                                   alt="..."
                                   style="max-height: 100%; max-width: 100%; margin: auto">
                            </div>
                          </c:if>
                        </c:forEach>
                      </div>
                      <button class="carousel-control-prev" type="button"
                              data-bs-target="#listImageContent${post.id}"
                              data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                      </button>
                      <button class="carousel-control-next" type="button"
                              data-bs-target="#listImageContent${post.id}"
                              data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                      </button>
                    </div>
                  </c:if>
                </div>
                <div class="card-footer mb-3">
                                <span class="ms-3">
                                    <span class="ms-1">${post.numberOfLikes}</span>
                                    Likes
                                </span>
                  <div>
                    <hr style="border: 1px solid white; background-color: white">
                  </div>
                  <div class="d-flex flex-row justify-content-around">
                    <div class="like">
                      <c:if test="${post.isLiked == 0}">
                        <a class="text-decoration-none text-dark"
                           href="#">
                          <form action="${pageContext.request.contextPath}/home/like?action=create"
                                method="post"
                          >
                            <input type="hidden" name="postId" value="${post.id}">
                            <input type="hidden" name="userId" value="${post.userId}">
                            <button class="btn border-0"
                                    type="submit"
                                    id="button-addon4">
                              <i class="fas fa-thumbs-up"></i>
                            </button>
                          </form>
                        </a>
                      </c:if>
                      <c:if test="${post.isLiked == 1}">
                        <a class="text-decoration-none " href="#">
                          <form action="${pageContext.request.contextPath}/home/like?action=create"
                                method="post"
                          >
                            <input type="hidden" name="postId" value="${post.id}">
                            <input type="hidden" name="userId" value="${post.userId}">
                            <button class="btn color-primary"
                                    type="submit"
                                    id="button-addon3">
                              <i class="fas fa-thumbs-up" style="color: #0d6efd">

                              </i>
                            </button>
                          </form>
                        </a>
                      </c:if>
                    </div>
                    <div class="comment ms-3 dropdown">
                      <p class="d-inline-flex gap-2 d-flex align-items-center">
                        <a class="text-decoration-none text-dark"
                           data-bs-toggle="collapse" href="#Comment${post.id}"
                           role="button" aria-expanded="false"
                           aria-controls="Comment">
                          <i class="fas fa-comment-alt"></i>
                          <c:if test="${fn:length(requestScope.comments) > 0}">
                            <span class="ms-1">${fn:length(requestScope.comments)}</span>
                          </c:if>
                          <span class="ms-1">Comment</span>
                        </a>
                      </p>
                    </div>
<%--                    <div class="share ms-3">--%>
<%--                      <i class="fas fa-share"></i>--%>
<%--                      <span class="ms-1">Share</span>--%>
<%--                    </div>--%>
                  </div>
                </div>
                <div class="collapse" id="Comment${post.id}">
                  <div class="d-flex flex-column" style="max-height: 300px; overflow-y: auto">
                    <c:if test="${post.comments == null || empty(post.comments)}">
                      <div class="card card-body text-center align-self-center" style="height: 100%; width: 100%; border: none">
                        <div class="d-flex flex-row justify-content-center align-items-center">
                          <div class="modal-title">
                            No comment
                            <i class="fas fa-sad-tear ms-1" style="color: #0d6efd"
                            ></i>
                          </div>
                        </div>
                      </div>
                    </c:if>
                    <c:forEach items="${post.comments}" var="comment" varStatus="status">
                      <div class="col-12">
                        <div class="card card-body">
                          <div class="d-flex flex-row align-items-center justify-content-start">
                            <div class="avatar">
                              <img src="${post.urlAvatar}" alt=""
                                   class="rounded-circle"
                                   width="50px" height="50px">
                            </div>
                            <div class="info d-flex flex-column">
                              <div class="content ms-3 align-self-center d-flex flex-column">
                                <div class="d-flex flex-row align-items-center">
                                                            <span class="me-1">
                                                                <a class="text-decoration-none text-primary"
                                                                   href="#">
                                                                    ${comment.fullName}
                                                                </a>
                                                            </span>
                                  <span class="ms-1"
                                        style="font-size: 12px !important;">
                                      ${comment.creationDate}
                                  </span>
                                </div>
                                <span>
                                    ${comment.content}
                                </span>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </c:forEach>
                  </div>
                  <div class="card card-body" style="border: none">
                    <form action="${pageContext.request.contextPath}/home/comment?action=create"
                          method="post"
                    >
                      <div class="d-flex flex-row">
                        <div class="avatar">
                          <img src="https://picsum.photos/200/300" alt="" class="rounded-circle"
                               width="50px" height="50px">
                        </div>
                        <div class="input-group ms-3">
                          <input type="text" class="form-control"
                                 name="comment"
                                 placeholder="Write a comment...">
                          <input type="hidden" name="postId" value="${post.id}">
                          <button class="btn btn-outline-secondary"
                                  type="submit"
                                  id="button-addon2">
                            Button
                          </button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </c:forEach>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
