<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <link href="/resources/css/styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">

    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">

            <div class="col-4">
                <a href="/home"><img class="logo" src="/resources/images/logo.png" alt="logo" id="Aska-logo"> </a>
            </div>

            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark h1" href="/home">Aska</a>
            </div>

            <div class="col-4 d-flex justify-content-end align-items-center">
                <div class="justify-content-end">
                    <sec:authorize access="!isAuthenticated()">
                        <a class="btn btn-sm btn-outline-secondary log-in-btn"
                           href="${s:mvcUrl('UC#loginGet').build()}">Log in</a>
                        <a class="btn btn-sm btn-outline-secondary sign-up-btn"
                           href="${s:mvcUrl('UC#registrationGet').build()}">Sign up</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <form id="logout" action="/logout" method="post">
                            <button type="submit" class="btn btn-sm btn-outline-secondary log-out-btn">Logout</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </sec:authorize>
                </div>
            </div>

        </div>
    </header>


    <div class="nav-scroller py-3 mb-4 mt-2">
        <nav class="nav d-flex justify-content-around align-items-center">
            <a class="p-2 text-muted" href="${s:mvcUrl("UC#homeGet").build()}">Home</a>
            <a class="p-2 text-muted" href="${s:mvcUrl("SC#enterSurveyCode").build()}">Enter a code</a>
            <a class="p-2 text-muted" href="#partners">Partners</a>
        </nav>
    </div>

    <sec:authorize access="isAuthenticated()">
        <div class="nav-scroller py-3 mb-4 mt-2">
            <nav class="nav d-flex justify-content-around align-items-center">
                <a class="p-2 text-muted" href="${s:mvcUrl('UC#profileGet').build()}">Your profile</a>
                <a class="p-2 text-muted" href="${s:mvcUrl('SC#createSurveyGet').build()}">Create a survey</a>
            </nav>
        </div>
    </sec:authorize>




    <div class="jumbotron p-4 p-md-5 text-dark rounded bg-light row" id="survey-code">


            <h2 class="text-center">${errorMsg}</h2>


    </div>

</div>

<footer class="blog-footer" id="partners">
    <p>&copy; 2019 Aska, Inc.</p>
    <p>
        <a href="#Aska-logo">Back to top</a>
    </p>
</footer>

</body>
</html>