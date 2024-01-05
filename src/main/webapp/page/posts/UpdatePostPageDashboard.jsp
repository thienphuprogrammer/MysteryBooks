<%--
  Created by IntelliJ IDEA.
  User: ThienPhu
  Date: 12/27/2023
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Update Post</title>
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
<div class="container mt-5" style="max-width: 700px; min-height: 100vh">
    <div class="card bg-dark text-white">
        <div class="card-header">
            <h3>Update Post</h3>
        </div>
        <div class="card-body">
            <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.error}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.success}">
                <div class="alert alert-success" role="alert">
                    ${requestScope.success}
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/home/post?action=update&postId=${requestScope.post.id}"
                  method="post" enctype="multipart/form-data"
            >
                <input type="hidden" name="id" value="${requestScope.post.id}">
                <div class="mb-3">
                    <label class="col-form-label text-white">Visibility: ${requestScope.post.visibility}</label>
                    <select class="form-select" aria-label="Default select example" name="visibility">
                        <c:if test="${requestScope.post.visibility == 'public'}">
                            <option value="0" selected>Public</option>
                            <option value="1">Friend</option>
                            <option value="2">Only me</option>
                        </c:if>
                        <c:if test="${requestScope.post.visibility == 'friends'}">
                            <option value="0">Public</option>
                            <option value="1" selected>Friend</option>
                            <option value="2">Only me</option>
                        </c:if>
                        <c:if test="${requestScope.post.visibility == 'only_me'}">
                            <option value="0">Public</option>
                            <option value="1">Friend</option>
                            <option value="2" selected>Only me</option>
                        </c:if>
                    </select>

                </div>
                <div class="mb-3">
                    <label for="title" class="form-label">Title</label>
                    <input type="text" name="title" class="form-control" id="title" value="${requestScope.post.title}">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">Content</label>
                    <textarea name="content" class="form-control" id="content"
                              rows="3">${requestScope.post.content}</textarea>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Image</label>
                    <input type="file" multiple="multiple" accept="image/jpeg,image/png,image/gif, image/jpg"
                           name="urlImages"
                           class="w-100 form-control"
                           id="image"
                    >
                </div>

                <div class="mb-3" id="imageList">
                    <c:if test="${not empty requestScope.post.images}">
                        <c:forEach items="${requestScope.post.images}" var="image">
                            <div class="position-relative">
                                <img src="../${image.urlImg}" alt="" style="width: 100%;"
                                     class="img-thumbnail rounded my-2 d-block"
                                     id="image${image.id}"
                                >
                                <input type="hidden" name="imageId" value="${image.id}">
                                <a href="${pageContext.request.contextPath}/home/post?action=update&postId=${requestScope.post.id}&imageId=${image.id}&action=delete"
                                   class="btn btn-danger position-absolute top-0 end-0"
                                   >
                                    X
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                    <div id="listImage"></div>
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="${pageContext.request.contextPath}/home"
                   class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    const imageList = document.getElementById("listImage");
    const image = document.getElementById("image");
    const imageArr = [];

    image.addEventListener("change", function (e) {
        const files = image.files;
        for (let i = 0; i < files.length; i++) {
            imageArr.push(files[i]);
        }
        displayImage();
    })

    function displayImage() {
        //put content of imageArr to imageList
        imageList.innerHTML = "";
        imageArr.forEach((file, index) => {
            const div = document.createElement("div");
            div.classList.add("position-relative");
            const img = document.createElement("img");
            img.src = URL.createObjectURL(file);
            img.classList.add("img-thumbnail", "rounded", "my-2", "d-block");
            img.style.width = "100%";

            const btnDelete = document.createElement("span");
            btnDelete.classList.add("btn", "btn-danger", "position-absolute", "top-0", "end-0");
            btnDelete.id = "btnDelete" + index;
            btnDelete.innerText = "X";
            btnDelete.addEventListener("click", function () {
                imageArr.splice(index, 1);
                displayImage();
            })

            div.appendChild(img);
            div.appendChild(btnDelete);
            imageList.appendChild(div);
        })
    }

    function deleteImage(id) {
        imageArr.splice(id, 1);
        displayImage();
    }
</script>
</html>
