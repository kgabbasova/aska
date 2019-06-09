<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <title>Answering</title>
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


    <sec:authorize access="!isAuthenticated()">
        <div class="nav-scroller py-3 mb-4 mt-2">
            <nav class="nav d-flex justify-content-around align-items-center">
                <a class="p-2 text-muted" href="${s:mvcUrl("UC#homeGet").build()}">Home</a>
                <a class="p-2 text-muted" href="${s:mvcUrl("SC#enterSurveyCode").build()}">Enter a code</a>
                <a class="p-2 text-muted" href="#partners">Partners</a>
            </nav>
        </div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <div class="nav-scroller py-3 mb-4 mt-2">
            <nav class="nav d-flex justify-content-around align-items-center">
                <a class="p-2 text-muted" href="${s:mvcUrl('UC#profileGet').build()}">Your profile</a>
                <a class="p-2 text-muted" href="${s:mvcUrl('SC#createSurveyGet').build()}">Create a survey</a>
                <a class="p-2 text-muted" href="${s:mvcUrl("SC#enterSurveyCode").build()}">Enter a code</a>
            </nav>
        </div>
    </sec:authorize>


    <div class="user-surveys  p-4 p-md-5 text-dark rounded row">
        <c:if test="${not empty message}">
            <div class="h4 text-success">
                <p>${message}</p>
            </div>
        </c:if>

        <div class="col-10 form-style-6 mb-5">
            <c:set var="survey" value="${survey}"/>

            <h1 class="mb-5 text-center">${survey.name}</h1>


            <div class="survey-body p-3 inputGroup">
                <form action="${s:mvcUrl('SC#passSurvey').arg(1, survey.id).build()}" method="post">
                    <ol>
                        <c:forEach items="${survey.questions}" var="question">
                            <li>
                                <p>${question.description}</p>
                                <c:choose>
                                    <c:when test="${question.type == 'single'}">
                                        <c:forEach var="answer" items="${question.questionAnswers}">
                                            <label for="answer-${answer.id}">
                                                <input type="radio" name="${question.id}"
                                                       value="${answer.id}" id="answer-${answer.id}">
                                                    ${answer.description}
                                            </label>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="small">(Choose one or more answers)</span>
                                        <br>
                                        <c:forEach var="answer" items="${question.questionAnswers}">
                                            <label for="answer-${answer.id}">
                                                <input type="checkbox" value="${answer.id}" name="${question.id}"
                                                       id="answer-${answer.id}">
                                                    ${answer.description}
                                            </label>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                <hr>
                            </li>
                        </c:forEach>
                    </ol>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="col-4 ml-4 btn" value="Send">
                </form>


            </div>

        </div>

    </div>
</div>


<footer class="blog-footer" id="partners">
    <p>&copy; 2019 Aska, Inc.</p>
    <p>
        <a href="#Aska-logo">Back to top</a>
    </p>
</footer>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/base.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


</body>
</html>
