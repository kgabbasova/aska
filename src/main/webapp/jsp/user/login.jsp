<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
                    <a class="btn btn-sm btn-outline-secondary sign-up-btn" href="registration">Sign up</a>
                </div>
            </div>

        </div>
    </header>


    <div class="login form-style-5 mt-3 mb-4">
        <form action="/login" method="post" >

            <fieldset>
                <legend> Login </legend>

                <c:if test="${param.error != null}">
                    <p class="message">
                        Invalid username or password!
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p class="message">
                        You have been logged out.
                    </p>
                </c:if>

                <c:if test="${not empty message}">
                    <div class="message">
                        <p>${message}</p>
                    </div>
                </c:if>

                <div>
                    <label for="username">Username
                        <input type="email" name="username" id="username" placeholder="E-mail address" autofocus required>
                    </label>
                </div>
                <div>
                    <label for="password">Password
                        <input type="password" name="password" id="password" required>
                    </label>
                </div>
                <div>
                    <label for ="remember-me">
                        <input type="checkbox" name="remember-me" id="remember-me">
                        Remember me!
                    </label>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" value="Submit">
            </fieldset>

        </form>
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