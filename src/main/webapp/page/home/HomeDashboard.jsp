<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/15/2023
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.+
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<html>
<body style="font-family: 'Lobster', cursive; background-color: #f0f2f5">
<%@include file="../../component/header/Header.jsp" %>
<div class="container-fluid d-flex flex-row justify-content-center">
    <div class="col-3 mt-5 p-5 rounded-3 d-flex flex-column align-items-center"
         style="border-radius: 10px 10px 0 0; min-height: 100vh; max-width: 1000px">
        <div class="title fw-bold fs-1 text-center mb-5">Friend Request</div>
        <div class="d-flex flex-wrap align-items-start justify-content-center">
            <c:if test="${requestScope.listFriendRequest != null && !empty(requestScope.listFriendRequest)}">
                <c:forEach items="${requestScope.listFriendRequest}" var="request" varStatus="status">
                    <div class="cart p-3 m-3 d-flex flex-row align-items-center justify-content-between border rounded-3"
                         style="border-radius: 10px; width: 100%; max-width: 500px">
                        <img src="${request.avatar}" alt="" class="card-img-top rounded-circle"
                             style="width: 70px; height: 70px; object-fit: cover; margin-right: 10px">
                        <div class="card-body">
                            <div class="card-title fw-bold fs-5 text-truncate">
                                <a href="/user/${request.id}" class="text-decoration-none text-dark"
                                >${request.fullName}</a>
                            </div>
                        </div>

                        <form action="${pageContext.request.contextPath}/friends"
                              method="post"
                              class="d-flex flex-column align-items-center mt-3" style="margin-right: 10px; margin-left: 10px">
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="senderId" value="${request.senderId}">
                            <input type="hidden" name="receiverId" value="${request.receiverId}">
                            <input type="hidden" name="action" value="accept">
                            <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                            >Accept</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/friends"
                              method="post"
                              class="d-flex flex-column align-items-center mt-3">
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="senderId" value="${request.senderId}">
                            <input type="hidden" name="receiverId" value="${request.receiverId}">
                            <input type="hidden" name="action" value="decline">
                            <button type="submit" class="btn btn-danger w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                            >Decline</button>
                        </form>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <div class="title fw-bold fs-1 text-center mb-5">List User</div>
        <div class="d-flex flex-wrap align-items-start justify-content-center flex-column">
            <c:if test="${requestScope.listUser != null && !empty(requestScope.listUser)}">
                <c:forEach items="${requestScope.listUser}" var="user" varStatus="status">
                    <div class="p-1 m-1 d-flex flex-row align-items-center justify-content-between border rounded-3"
                         style="border-radius: 10px; width: 100%; max-width: 500px">
                        <img src="${user.profilePicture}" alt="" class="card-img-top rounded-circle"
                             style="max-width: 70px; max-height: 70px; object-fit: cover; margin-right: 10px">
                        <div class="">
                            <div class="fw-bold fs-5 text-truncate">
                                <a href="${pageContext.request.contextPath}/profile?id=${user.id}"
                                   class="text-decoration-none text-dark"
                                >${user.fullName}</a>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/friends"
                              method="post"
                              class="d-flex flex-column align-items-center mt-3">
                            <input type="hidden" name="friendId" value="${user.id}">
                            <c:if test="${user.statusRequestFriend == 0}">
                                <input type="hidden" name="action" value="add">
                                <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                >Add Friend</button>
                            </c:if>
                            <c:if test="${user.statusRequestFriend == 1}">
                                <button type="submit" class="btn btn-primary w-100 d-flex align-items-center justify-content-center" style="border-radius: 10px"
                                        disabled
                                >Waiting for accept</button>
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger w-100 d-flex align-items-center justify-content-center mt-2" style="border-radius: 10px"
                                >Cancel</button>
                            </c:if>
                        </form>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="col mt-5" style="max-width: 700px; min-height: 100vh">
        <div class="row">
            <div class="col-12 d-flex justify-content-center">
                <button class="btn w-100 mb-3 fs-5 fw-bold border-2 border bg-primary border-primary text-white"
                        data-bs-toggle="modal" data-bs-target="#createPost" data-bs-whatever="@mdo">
                    Create Post
                </button>
                <div class="modal fade" id="createPost" actionindex="-1" aria-labelledby="createPostLabel" aria-hidden="true" style="font-family: 'Lobster', cursive;">
                    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
                        <div class="modal-content">
                            <form action="${pageContext.request.contextPath}/home/post?action=create"
                                  method="post"
                                  enctype="multipart/form-data"
                            >
                                <div class="modal-header border-primary border-2 border bg-primary text-white">
                                    <h5 class="modal-title text-white" id="createPostLabel">Create Post</h5>
                                    <button type="button" class="btn-close border-2 border bg-light text-dark"
                                            data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body bg-light text-dark">
                                    <div class="mb-3">
                                        <label for="recipient-name" class="col-form-label">Visibility:</label>
                                        <select class="form-select" aria-label="Default select example" name="visibility">
                                            <option value="0">Public</option>
                                            <option value="1">Friend</option>
                                            <option value="2">Only me</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="recipient-name" class="col-form-label">Content:</label>
                                        <textarea class="form-control" id="recipient-name" name="content"
                                                  style="height: 100px"
                                                  required cols="30" rows="10"
                                        ></textarea>
                                    </div>
                                    <div class="mb-3" style="width: 100%;">
                                        <%@include file="../../component/uploadimage/UploadImage.jsp" %>
                                    </div>
                                </div>
                                <div class="modal-footer bg-light text-dark">
                                    <button type="submit"
                                            class="btn btn-primary w-100"
                                    >Post
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
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
                                            <a class="text-decoration-none text-white" href="#">
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
<%--                                    <div class="share ms-3">--%>
<%--                                        <i class="fas fa-share"></i>--%>
<%--                                        <span class="ms-1">Share</span>--%>
<%--                                    </div>--%>
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