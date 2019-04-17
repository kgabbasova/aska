<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <meta  http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../resources/css/styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">

    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">

            <div class="col-4">
                <img class="logo" src="../resources/images/logo.png" alt="logo" id="Aska-logo">
            </div>

            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark h1" href="home">Aska</a>
            </div>

            <div class="col-4 d-flex justify-content-end align-items-center">
                <div class="justify-content-end">
                    <a class="btn btn-sm btn-outline-secondary log-in-btn" href="login">Log in</a>
                </div>
            </div>

        </div>
    </header>


    <div class="sign-up form-style-5 mt-3 mb-4">

        <form:form action="/registration" method="post" modelAttribute="registrationForm">
            <fieldset>
                <legend> Registration</legend>

                <c:if test="${not empty message}">
                    <div class="message">
                        <p>${message}</p>
                    </div>
                </c:if>

                <div>
                    <form:errors path="username" cssClass="message"/>
                    <label for="username">Username
                        <form:input type="email" path="username" id="username" placeholder="E-mail address"
                                    autofocus="true" required="true"/>
                    </label>
                </div>
                <div>
                    <form:errors path="password" cssClass="message"/>
                    <label for="password">Password
                        <form:input type="password" path="password" id="password"
                                    placeholder="Must be at least 8 characters"
                                    required="true"/>

                    </label>
                </div>
                <div>
                    <form:errors  cssClass="message"/>
                    <label for="passwordRepeat">Confirm password
                        <form:input type="password" path="passwordRepeat" id="passwordRepeat" required="true"/>
                    </label>
                </div>
                <div>
                    <form:errors path="birthday" cssClass="message"/>
                    <label for="birthday"> Birthday
                        <form:input type="date" path="birthday" id="birthday" required="true" max="9999-12-31"/>
                    </label>
                </div>
                <div>
                    <label for="country"> Select your country</label>
                    <form:select path="country" id="country">
                        <option value="Russia" selected> Russia</option>
                        <option value="Ukraine"> Ukraine</option>
                        <option value="United States">USA</option>
                        <option value="Austria">Austria</option>
                        <option value="Indonesia">Indonesia</option>
                    </form:select>
                </div>
                <input type="submit" value="Submit">
            </fieldset>
        </form:form>

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
