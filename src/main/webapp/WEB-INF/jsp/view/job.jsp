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
            <div style="margin-top: 3%; margin-left: 15%;" >
                <!-- Job Description -->
                <div class="card" style="max-width: 500px; margin-right: 5%; float:left; margin-top: 2%;">
                    <div class="card-body">
                        <div class="card-text">
                            <p><label><strong>Job Title:&nbsp;</strong></label><c:out value="${selectedJob.title}" /></p>
                            <p>
                                <label><strong>Location:&nbsp;</strong></label>
                                <c:out value="${selectedJob.city}" />&#44;&nbsp;
                                <c:out value="${selectedJob.state}" />
                            </p>
                            <p>
                                <label><strong>Job Type:&nbsp;</strong></label>
                                <c:choose>
                                    <c:when test="${selectedJob.fullTime == true}">
                                        Full-Time
                                    </c:when>
                                    <c:otherwise>
                                        Part-Time
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <p><label><strong>Job Sector:&nbsp;</strong></label><c:out value="${selectedJob.department}" /></p>
                            <p><label><strong>Experience Required:&nbsp;</strong></label><c:out value="${selectedJob.experience}" /></p>
                            <p>
                                <label><strong>Salary:&nbsp;</strong></label>
                                <fmt:formatNumber value ="${selectedJob.salary}" 
                                                  type="currency" currencyCode="USD" />
                            </p>
                                <label><strong>Description:</strong></label>
                             <p><c:out value="${selectedJob.jobDescription}" /></p>
                        </div>
                    </div>
                </div>
                <div class="container-fluid" style="float: left; max-width: 500px; margin-top: 2%;">
                    <form>
                        <div class="form-group">
                          <label for="firstName">Email address</label>
                          <input type="text" class="form-control" id="firstName" placeholder="Enter First Name">
                        </div>
                        <div class="form-group">
                          <label for="lastName">Last Name</label>
                          <input type="text" class="form-control" id="lastName" placeholder="Enter Last Name">
                        </div>
                        <div class="form-group">
                          <label for="email">Email</label>
                          <input type="email" class="form-control" id="email" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone Number:</label>
                            <input type="tel" id="phone" class="form-control" name="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" placeholder="Ex. 123-456-7890"> 
                        </div>
                        <div class="form-group">
                            <label for="resumeAttach">Upload Resume</label>
                            <input type="file" class="form-control-file" id="resumeAttach">
                        </div>
                        <div class="form-group">
                          <label for="desSal">Desired Salary</label>
                          <input type="text" class="form-control" id="desSal" placeholder="Enter only numbers: Ex. 25000" pattern="[0-9]+">
                        </div>
                        <div class="form-group">
                            <label for="earlStartDate">Earliest Start Date</label>
                            <input type="text" class="form-control" id="earlStartDate" placeholder="Required format: Ex. 01/29/2022" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
