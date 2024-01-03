<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Mystery Books</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.contextPath == '/home'}">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/home">Home</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/home">Home</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.contextPath == '/friends'}">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/friends">Friends</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/friends">Friends</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.contextPath == '/messages'}">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/messages">Messages</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/messages">Messages</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.contextPath == '/notifications'}">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/notifications">Notifications</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/notifications">Notifications</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item dropdown">
                    <c:choose>
                        <c:when test="${pageContext.request.contextPath == '/profile'}">
                            <a class="nav-link dropdown-toggle active" href="${pageContext.request.contextPath}/user"
                               role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Profile
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/user"
                               role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Profile
                            </a>
                        </c:otherwise>
                    </c:choose>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/settings">Settings</a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex justify-content-center align-items-center"
                  action="${pageContext.request.contextPath}/searchController" role="search">
                <input class="form-control me-2 bg-dark text-white border-2 rounded-pill px-3 mt-4" type="search"
                       placeholder="Search" aria-label="Search" name="search">
                <button class="btn btn-outline-success bg-dark text-white border-2 rounded-pill px-3 mt-4"
                        type="submit">Search
                </button>
            </form>
        </div>
    </div>
</nav>