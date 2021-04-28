<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jobs</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap-reboot.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Navigation bar Area -->
        <nav class="navbar navbar-light bg-light">
            <a class="navbar-brand" 
               href="<c:url value="/jobs">
                    <c:param name="action" value="defaultList" />
               </c:url>">Jobs</a>
        </nav>
        <!-- Jobs Area -->
        <div class="main-content">
            <c:forEach items="${jobs}" var="job" begin="${begin}" end="${end}">
                <div class="">
                    <a
                        href="<c:url value="/jobs">
                            <c:param name="action" value="viewJob" />
                            <c:param name="job" value="${job.id}" /></c:url>">
                        <c:out value="${job.title}"></c:out>
                    </a>
                    <p>
                        <c:out value="${job.city}" />&#44;&nbsp;
                        <c:out value="${job.state}" />
                    </p>
                    <p><c:out value="${job.department}" /></p><br>
            </c:forEach>
            </div>
        </div>
        <!-- Pagination Area -->
        <div style="margin-left:auto; margin-right:auto;">
            <div class="pagination justify-content-center">
                <c:forEach var="pageNum" begin="1" end="${maxPages}">
                    <a
                        <c:if test="${currentPage == i}">
                            class=""
                        </c:if> 
                        href="<c:url value="/jobs">
                            <c:param name="page" value="${pageNum}" />
                        </c:url>">${pageNum}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
