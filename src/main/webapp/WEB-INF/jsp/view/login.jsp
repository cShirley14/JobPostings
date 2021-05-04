<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
        <!-- Login Area -->
        <div class="container-fluid" style="text-align: center; max-width: 500px; 
             margin-top: 2%;">
            <c:if test="${loginFailed == true}">
             <p style="font-weight: bold;">The username or password you entered
                is not correct. Please try again</p>
            </c:if>
            <c:if  test="${loginFailed == false}">
                <p>Enter administrative credentials.</p>
            </c:if>
                                                                                                
            <h2>Login.</h2>
            <form style="border:2px solid lightgray; padding:20px;border-radius: 5px;
                  box-shadow: 2px 2px 3px lightblue;" method="POST" 
                  action="<c:url value="/login" />">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" /><br><br>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" /><br><br>
                <input type="submit" class="btn btn-primary" value="Log In" />
            </form>
        </div>
    </body>
</html>