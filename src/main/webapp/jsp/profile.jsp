<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Your Profile</title>
    <meta charset="utf-8">
    <link href="../resources/css/styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">

    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">

            <div class="col-4">
                <img class="logo" src = "../resources/images/logo.png" alt="logo" id="Aska-logo">
            </div>

            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark h1" href="home">Aska</a>
            </div>

            <div class="col-4 d-flex justify-content-end align-items-center">
                <div class="justify-content-end">
                    <form id="logout" action="/logout" method="post" >
                        <button type="submit" class="btn btn-sm btn-outline-secondary log-out-btn">Logout</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </div>

        </div>
    </header>


    <div class="nav-scroller py-3 mb-4 mt-2">
        <nav class="nav d-flex justify-content-around align-items-center">
            <a class="p-2 text-muted" href="#">Edit profile</a>
            <a class="p-2 text-muted" href="#">Create a survey</a>
            <a class="p-2 text-muted" href="#survey-code-form">Enter a code</a>
        </nav>
    </div>


    <div class="jumbotron p-4 p-md-5 text-dark rounded bg-light row" id="survey-code">
        <div class="col-md-6 px-1">
            <label for="survey-code-form">
                <p class="lead my-3">Each survey has its own unique number. Just enter the appropriate code to go to its
                    questions. </p>
            </label>
        </div>


        <div class="col-md-6 px-2">
            <form action="/home" method="post">
                <div class="survey-code">
                    <input type="text" name="survey-code" id="survey-code-form">
                    <input type="submit" value="Submit!">
                </div>
            </form>

        </div>
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
