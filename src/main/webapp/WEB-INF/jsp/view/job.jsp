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
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Applications<span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Login</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- Job Area -->
        <!-- Job Description -->
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
                     <p><c:out value="${selectedJob.jobDescription}" /></p>
                </div>
            </div>
        </div>
        <div class="list-group-flush">
            <div class="list-group-item">
                <form>
                    <div class="form-group" id="formBox"  >
                        <div class="list-group-item" style="min-width:450px;">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" id="firstName" placeholder="Enter First Name" >
                        </div>
                        <div class="list-group-item">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" id="firstName" placeholder="Enter First Name" >
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
