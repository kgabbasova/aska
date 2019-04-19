<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <title>Create a survey</title>
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
                    <form id="logout" action="/logout" method="post">
                        <button type="submit" class="btn btn-sm btn-outline-secondary log-out-btn">Logout</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>

        </div>
    </header>


    <div class="nav-scroller py-3 mb-4 mt-2">
        <nav class="nav d-flex justify-content-around align-items-center">
            <a class="p-2 text-muted" href="${s:mvcUrl('UC#profileGet').build()}">Your profile</a>
            <a class="p-2 text-muted" href="#">Your surveys</a>
            <a class="p-2 text-muted" href="#">Enter a code</a>
        </nav>
    </div>


    <div class="poll form-style-5 mt-3 mb-4">

        <form:form action="${s:mvcUrl('SC#createSurveyPost').build()}" method="post" modelAttribute="surveyForm">
            <fieldset>
                <legend> Create your poll!</legend>

                <c:if test="${not empty message}">
                    <div class="message">
                        <p>${message}</p>
                    </div>
                </c:if>


                <div>
                    <label for="survey-name">Survey name
                        <form:input type="text" path="name" id="survey-name"
                                    autofocus="true" required="true" maxlength="64"/>
                    </label>
                </div>

                <div>
                    <label for="survey-show-mode"> Please, select the questions display mode </label>
                    <form:select path="showMode" id="survey-show-mode">
                        <option value="All" selected>Show all questions</option>
                        <option value="Author"> On your initiative</option>
                        <option value="Respondent"> On the initiative of the respondent</option>
                        <option value="Time">Show after a determined time</option>
                    </form:select>
                </div>

                <div>
                    <label for="survey-results-show">
                        <form:checkbox path="resultsShow" id="survey-results-show"/>
                        Do you want to show results to respondents?
                    </label>
                </div>


                <div>
                    <ol>
                            <li>
                                <label for="survey-question-body">Type a question
                                    <form:textarea id="survey-question-body"
                                                   path="question.description"
                                                   required="true" maxlength="512" rows="4"/>
                                </label>

                                <ol>
                                        <li>
                                            <label for="survey-answer-body">Answer
                                                <form:textarea path="answer.description"
                                                               id="survey-answer-body"
                                                               required="true" maxlength="256" rows="2"/>
                                            </label>

                                            <%--<label for="survey-answer-is-right">--%>
                                                <%--<form:checkbox path="answer.isRight"--%>
                                                               <%--id="survey-answer-is-right"/>--%>
                                                <%--Is it a right answer?--%>
                                            <%--</label>--%>
                                        </li>
                                        <li>
                                            <label for="survey-answer-name-2">Answer
                                                <form:textarea path="answer.description"
                                                               id="survey-answer-name-2"
                                                               required="true" maxlength="256" rows="2"/>
                                            </label>
                                            <%--<label for="survey-answer-is-right-2">--%>
                                                <%--<form:checkbox path="answer.isRight" id="survey-answer-is-right-2"/>--%>
                                                <%--Is it a right answer?--%>
                                            <%--</label>--%>
                                        </li>

                                </ol>

                            </li>

                    </ol>
                </div>


                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
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
