<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applications</title>
        <script src="scripts/jquery-3.6.0.min.js" type="text/javascript"></script>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap-reboot.min.css" 
              rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
        <script src="scripts/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Navigation bar Area (img courtesy of pixabay creative commons) -->
        <nav class="navbar navbar-expand-md navbar-dark bg-primary ">
            <a class="navbar-brand" 
               href="<c:url value="/jobs">
                    <c:param name="action" value="defaultList" />
               </c:url>"><img src="https://cdn.pixabay.com/photo/2016/09/16/19/15/gear-1674891_960_720.png" 
                   width="30" height="30" style="border-radius:25%;" 
                   class="d-inline-block align-top" 
                   alt="">&nbsp;Jobs</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <c:if test="${username == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/login"/>">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${username != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="/applications"><c:param name="action" value="adminLogin"></c:param></c:url>">Applications<span class="sr-only"></span></a>
                        </li>
                        <li>
                            <a class="nav-link" href="<c:url value="/login?logout" />">Logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
        <!-- Application List Area -->
        <!-- Jobs Area -->
        <div class="jumbotron jumbotron-fluid overflow-hidden">
            <div class="container">
              <h1 class="display-4">Current Entries<img src="https://cdn.pixabay.com/photo/2016/09/16/19/15/gear-1674891_960_720.png" style="width:100px; border-radius:25%; float: right; padding:5px;" /></h1>
              <p class="lead">Received Applications</p>
            </div>
          </div>
        <div class="main-content">
            <c:if test="${fn:length(apps) != 0}" >
                <c:forEach items="${apps}" var="application" begin="${beginApp}" end="${endApp}">
                    <div class="card" style="padding: 1%; margin: 10px 25% 10px 0; ">
                        <a
                            href="<c:url value="/applications">
                                <c:param name="action" value="viewApp" />
                                <c:param name="appId" value="${application.id}" /></c:url>">
                            <c:out value="${application.jobTitle}"></c:out>
                        </a>
                        <p>
                            <c:out value="${application.lastName}" />&#44;&nbsp;
                            <c:out value="${application.firstName}" />
                        </p>
                        <p><c:out value="${application.email}" /></p><br>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${fn:length(apps) == 0}" >
                <div class="card" style="width:50%;;
                     width:300px;">
                    <div class="card-body" style="text-align: center;
                         color:green;"> 
                        No Applications to View!
                    </div>
                </div>
            </c:if>
        </div><br>
        <!-- Pagination Area -->
        <div>
            <div class="pagination justify-content-center">
                <c:forEach var="pageNumApp" begin="1" end="${maxPagesApp}">
                    <a
                        href="<c:url value="/applications">
                            <c:param name="pageApp" value="${pageNumApp}" />
                        </c:url>">${pageNumApp}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
