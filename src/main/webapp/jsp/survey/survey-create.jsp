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
            <a class="p-2 text-muted" href="${s:mvcUrl('UC#profileGet').build()}">Edit profile</a>
            <a class="p-2 text-muted" href="${s:mvcUrl('UC#profileGet').build()}">Your surveys</a>
            <a class="p-2 text-muted" href="#">Enter a code</a>
        </nav>
    </div>


    <div class="poll mt-3 mb-4 jumbotron p-4 p-md-5 text-dark rounded bg-light">
        <div class="form-style-6">

            <form:form action="${s:mvcUrl('SC#createSurveyPost').build()}" method="post" modelAttribute="surveyForm">
                <fieldset>
                    <legend> Create your poll!</legend>

                    <c:if test="${not empty message}">
                        <div class="message">
                            <p>${message}</p>
                        </div>
                    </c:if>

                    <div class="survey-info mb-5">
                        <div>
                            <label for="survey-name">Survey name
                                <form:input path="name" type="text" name="name" id="survey-name"
                                            required="true" maxlength="64"/>
                            </label>
                        </div>


                        <div>
                            <label for="survey-show-mode"> Please, select the questions display mode </label>
                            <form:select path="showMode" name="showMode" id="survey-show-mode">
                                <option value="ALL" selected>Show all questions</option>
                                <option value="AUTHOR"> On your initiative</option>
                                <option value="RESPONDENT"> On the initiative of the respondent</option>
                                <option value="TIME">Show after a determined time</option>
                            </form:select>
                        </div>

                        <div>
                            <label for="survey-results-show">
                                <form:checkbox path="resultsShow" id="survey-results-show"/>
                                Do you want to show results to respondents?
                            </label>
                        </div>
                    </div>
                    <hr>
                    <div id="survey-question-info">
                        <div class="survey-question-info mb-4">

                            <c:set var="countQ" value="0" scope="page"/>
                            <p><span class="number">${countQ+1}</span>Question</p>

                            <div>
                                <label for="survey-question-body">Type a question
                                    <form:textarea path="questions[${countQ}].description" id="survey-question-body"
                                                   name="question.description" cssClass="mb-3"
                                                   required="true" maxlength="512" rows="4"/>
                                </label>
                            </div>


                            <p>Choose a type of the question:</p>

                            <div class="row justify-content-around mt-3 mb-4">
                                <div>
                                    <label for="question-single">
                                        <form:radiobutton path="questions[${countQ}].type" value="single"
                                                          id="question-single"/>
                                        One answer question</label>
                                </div>
                                <div>
                                    <label for="question-multiple">
                                        <form:radiobutton path="questions[${countQ}].type" value="multi"
                                                          id="question-multiple"/>
                                        Multiple choice question</label>
                                </div>
                            </div>


                            <ol>
                                    <c:set var="countA" value="0" scope="page"/>
                                    <div class="mb-4">
                                        <li>
                                            <label for="survey-answer-body">Answer
                                                <form:textarea
                                                        path="questions[${countQ}].questionAnswers[${countA}].description"
                                                        id="survey-answer-body" cssClass="mb-1"
                                                        required="true" maxlength="256" rows="2"/>
                                            </label>

                                            <label for="survey-answer-is-right">
                                                <form:checkbox
                                                        path="questions[${countQ}].questionAnswers[${countA}].right"
                                                        id="survey-answer-is-right"/>
                                                Is it a right answer?
                                            </label>
                                        </li>
                                    </div>
                                    <div class="mb-4">
                                        <li>
                                            <c:set var="countA" value="${countA + 1}" scope="page"/>
                                            <label for="survey-answer-body-2">Answer
                                                <form:textarea
                                                        path="questions[${countQ}].questionAnswers[${countA}].description"
                                                        name="answer.description" cssClass="mb-1"
                                                        id="survey-answer-body-2"
                                                        required="true" maxlength="256" rows="2"/>
                                            </label>
                                            <label for="survey-answer-is-right-2">
                                                <form:checkbox
                                                        path="questions[${countQ}].questionAnswers[${countA}].right"
                                                        id="survey-answer-is-right-2"/>
                                                Is it a right answer?
                                            </label>
                                        </li>
                                    </div>
                                <button class="btn col-3 add-answer" id="add-answer-1" type="button">Add answer</button>
                            </ol>
                        </div>
                        <hr>
                    </div>


                    <button class="btn col-3" id="add-question" type="button">Add question</button>


                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Submit" class="btn col-6">
                </fieldset>
            </form:form>

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
<script src="/resources/js/base.js"></script>
</body>
</html>
