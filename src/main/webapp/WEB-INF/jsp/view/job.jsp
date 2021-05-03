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
                                <a class="nav-link" href="#">Applications<span class="sr-only"></span></a>
                            </li>
                            <li>
                                <a class="nav-link" href="<c:url value="/login?logout" />">Logout</a>
                            </li>
                        </c:if>
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
                    <c:forEach var="app" items="${applications}">
                        <c:if test="${app.jobId == selectedJob.id}">
                            <c:set var="displaySuccess" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${displaySuccess == true}">
                            <p style="color:green; font-weight:bold; text-align:center;">
                                Your application has been submitted successfully!
                            </p>
                            <c:set var="displaySuccess" value="false"/>
                        </c:when>
                        <c:otherwise>
                            <form method="post" action="applications" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="submitApp" />
                                <input type="hidden" name="job" value="${selectedJob.id}" />
                                <input type="hidden" name="active" value="${selectedJob.active}" />
                                <input type="hidden" name="jobTitle" value="${selectedJob.title}" />
                                <div class="form-group">
                                    <label for="firstName">First Name</label>
                                    <c:choose>
                                        <c:when test="${unsubmittedApp.firstNameError == true}" >
                                            <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Enter First Name"><span style="font-weight: bold;color:firebrick">Invalid First Name</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${unsubmittedApp != null}">
                                                    <input type="text" name="firstName" class="form-control" id="firstName" value="${unsubmittedApp.firstName}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Enter First Name">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="lastName">Last Name</label>
                                    <c:choose>
                                        <c:when test="${unsubmittedApp.lastNameError == true}" >
                                            <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Enter Last Name"><span style="font-weight:bold;color:firebrick">Invalid Last Name</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${unsubmittedApp != null}">
                                                    <input type="text" name="lastName" class="form-control" id="lastName" value="${unsubmittedApp.lastName}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Enter Last Name">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                  <label for="email">Email</label>
                                  <c:choose>
                                      <c:when test="${unsubmittedApp.emailError == true}" >
                                            <input type="text" name="email" class="form-control" id="email" placeholder="Enter email"><span style="font-weight:bold;color:firebrick">Invalid email</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${unsubmittedApp != null}">
                                                    <input type="text" name="email" class="form-control" id="email" value="${unsubmittedApp.email}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="text" name="email" class="form-control" id="email" placeholder="Enter email">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                  </c:choose>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone Number</label>
                                    <c:choose>
                                        <c:when test="${unsubmittedApp.phoneError == true}" >
                                              <input type="text" name="phone" class="form-control" id="phone" placeholder="Ex. 123-456-7890"><span style="font-weight:bold;color:firebrick">Invalid Phone Number</span>
                                          </c:when>
                                          <c:otherwise>
                                              <c:choose>
                                                  <c:when test="${unsubmittedApp != null}">
                                                      <input type="text" name="phone" class="form-control" id="phone" value="${unsubmittedApp.phone}">
                                                  </c:when>
                                                  <c:otherwise>
                                                      <input type="text" name="phone" class="form-control" id="phone" placeholder="Ex. 123-456-7890">
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:otherwise>
                                    </c:choose>
                                    <span style="font-weight: bold;">Required format: 123 456 7890 with or without (-," ",.) </span>
                                </div>
                                <div class="form-group">
                                    <label for="file">Upload Resume</label>
                                    <c:choose>
                                        <c:when test="${unsubmittedApp.resumeError == true}" >
                                              <input type="file" name="file1" class="form-control-file" id="file"><span style="font-weight:bold;color:firebrick">Resume Must Be Attached</span>
                                          </c:when>
                                          <c:otherwise>
                                              <input type="file" name="file1" class="form-control-file" id="file">
                                          </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                  <label for="desSal">Desired Salary</label>
                                  <c:choose>
                                        <c:when test="${unsubmittedApp.salaryError == true}" >
                                              <input type="text" name="desiredSalary" class="form-control" id="desSal" placeholder="Enter only numbers: Ex. 25000" pattern="[0-9]+"><span style="font-weight:bold;color:firebrick">Invalid Salary</span>
                                          </c:when>
                                          <c:otherwise>
                                              <c:choose>
                                                  <c:when test="${unsubmittedApp != null}">
                                                      <input type="text" name="desiredSalary" class="form-control" id="desSal" 
                                                             placeholder="Enter only numbers: Ex. 25000" pattern="[0-9]+" value="<fmt:formatNumber type="number" maxFractionDigits="0" groupingUsed="false" value="${unsubmittedApp.desiredSalary}" />">
                                                  </c:when>
                                                  <c:otherwise>
                                                      <input type="text" name="desiredSalary" class="form-control" id="desSal" placeholder="Enter only numbers: Ex. 25000" pattern="[0-9]+">
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:otherwise>
                                    </c:choose>
                                  <span style="font-weight: bold;">Required format: 25000 (digits only)</span>
                                </div>
                                <div class="form-group">
                                    <label for="earlStartDate">Earliest Start Date</label>
                                    <c:choose>
                                        <c:when test="${unsubmittedApp.startDateError == true}" >
                                              <input type="text" name="earlyStartDate" class="form-control" id="earlStartDate" placeholder="Required format: Ex. 01/29/2022" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"><span style="font-weight:bold;color:firebrick">Invalid Date, Must be in Future</span>
                                          </c:when>
                                          <c:otherwise>
                                              <c:choose>
                                                  <c:when test="${unsubmittedApp != null}">
                                                      <input type="text" name="earlyStartDate" class="form-control" id="earlStartDate" value="${unsubmittedApp.earliestStartDate}" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}">
                                                  </c:when>
                                                  <c:otherwise>
                                                      <input type="text" name="earlyStartDate" class="form-control" id="earlStartDate" placeholder="Required format: Ex. 01/29/2022" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}">
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:otherwise>
                                    </c:choose>
                                    <span style="font-weight: bold;">Required format: 01/29/2022</span>
                                </div>
                                <input type="submit" class="btn btn-primary" value="Submit"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </main>
    </body>
</html>
