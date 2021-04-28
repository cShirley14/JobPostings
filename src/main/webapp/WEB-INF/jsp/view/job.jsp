<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viewing <c:out value="${selectedJob.title}" /></title>
    </head>
    <body>
        <!-- Navigation bar Area -->
        
        <!-- Job Area -->
        <div class="container-fluid">
            <div class="">
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
    </body>
</html>
