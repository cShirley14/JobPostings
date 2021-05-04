<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viewing <c:out value="${selectedJob.title}" /></title>
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
        <main>
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
                                <a class="nav-link" href="<c:url value="/applications"/>">Applications<span class="sr-only"></span></a>
                            </li>
                            <li>
                                <a class="nav-link" href="<c:url value="/login?logout" />">Logout</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </nav>
            <!-- Application Review Area -->
            <div style="margin-top: 3%; margin-left: 15%;" >
                <!-- Applicant Description -->
                <div class="card" style="max-width: 500px; margin-right: 5%; float:left; margin-top: 2%;">
                    <div class="card-body">
                        <div class="card-text">
                            <p><label><strong>Job Title:&nbsp;</strong></label><c:out value="${selectedApplicant.jobTitle}" /></p>
                            <p>
                                <label><strong>Applicant Name:&nbsp;</strong></label>
                                <c:out value="${selectedApplicant.lastName}" />&#44;&nbsp;
                                <c:out value="${selectedApplicant.firstName}" />
                            </p>
                            <label><strong>Applicant Email:&nbsp;</strong></label>
                            <p><c:out value="${selectedApplicant.email}" /></p>
                            <label><strong>Applicant Number:&nbsp;</strong></label>
                            <p><c:out value="${selectedApplicant.phone}" /></p>
                            <label><strong>Applicant Resume:&nbsp;</strong></label><br>
                            <a href="
                               <c:url value="/applications">
                                    <c:param name="action" value="download" />
                                    <c:param name="appId" value="${selectedApplicant.id}" />
                                </c:url>">${selectedApplicant.attachment.getName()}
                            </a><br>
                            <label><strong>Desired Salary:&nbsp;</strong></label>
                            <p><fmt:formatNumber currencyCode="USD" type="currency" value="${selectedApplicant.desiredSalary}" /></p>
                            <label><strong>Earliest Start Date:&nbsp;</strong></label>
                            <p><fmt:formatDate pattern="MM/dd/yyyy" value="${selectedApplicant.convEarliestDate}" /></p>
                        </div>
                    </div>
                </div>
<!--                <div class="container-fluid" style="float: left; max-width: 500px; margin-top: 2%;">
                     Test whether the application has already been marked as inactive. 
                    <c:forEach var="app" items="">
                        <c:if test="">
                            <c:set var="displaySuccess" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${displaySuccess == true}">
                            <p style="color:green; font-weight:bold; text-align:center;">
                                The application has been marked as inactive!
                            </p>
                            <c:set var="displaySuccess" value="false"/>
                        </c:when>
                        <c:otherwise>
                            <form method="post" action="applications" enctype="multipart/form-data">
                                
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>-->
            </div>
        </main>
    </body>
</html>
