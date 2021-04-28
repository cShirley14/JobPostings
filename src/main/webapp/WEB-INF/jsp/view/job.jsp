<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viewing <c:out value="${selectedJob.title}" /></title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="styles/bootstrap-reboot.min.css" 
              rel="stylesheet" type="text/css"/>
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
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Applications<span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Login</a>
                    </li>
                </ul>
        </nav>
        <!-- Job Area -->
        <div class="card indvdl-card" id="card-area">
            <div class="card-body">
                <div class="card-text">
                    <p><c:out value="${selectedJob.title}" /></p><br>
                    <p>
                        <c:out value="${selectedJob.city}" />&#44;&nbsp;
                        <c:out value="${selectedJob.state}" />
                    </p><br>
                    <p>
                        <c:choose>
                            <c:when test="${selectedJob.fullTime == true}">
                                Full-Time
                            </c:when>
                            <c:otherwise>
                                Part-Time
                            </c:otherwise>
                        </c:choose>
                    </p><br>
                    <p><c:out value="${selectedJob.department}" /></p><br>
                    <p><c:out value="${selectedJob.experience}" /></p><br>
                    <p>
                        <fmt:formatNumber value ="${selectedJob.salary}" 
                                          type="currency" currencyCode="USD" />
                    </p><br>
                </div>
            </div>
        </div>
    </body>
</html>
