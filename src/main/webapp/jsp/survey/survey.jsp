<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <title>Your survey</title>
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
            <a class="p-2 text-muted" href="UC#profileGet">Your surveys</a>
            <a class="p-2 text-muted" href="${s:mvcUrl('SC#createSurveyGet').build()}">Create a survey</a>
            <a class="p-2 text-muted" href="${s:mvcUrl("SC#enterSurveyCode").build()}">Enter a code</a>
        </nav>
    </div>


    <div class="user-surveys  p-4 p-md-5 text-dark rounded row">
        <c:if test="${not empty message}">
            <div class="h4 text-success">
                <p>${message}</p>
            </div>
        </c:if>

        <div class="col-10 form-style-6 mb-5">
            <c:set var="survey" value="${survey}"/>


            <h1 class="mb-5 text-center">${survey.name}</h1>
            <div class="col-4 mx-auto text-center mb-4">
                <span id="surveys-code" class="h4">Survey code is ${survey.id}</span>
            </div>

            <div class="survey-body p-3">

                <c:set var="showMode" value="${survey.showMode}"/>

                <c:choose>
                    <c:when test="${survey.showMode == 'ALL'}">
                        <p>Show mode - showing all questions </p>
                    </c:when>
<%--                    <c:when test="${survey.showMode == 'AUTHOR'}">--%>
<%--                        <p>Show mode - showing question on your initiative</p>--%>
<%--                    </c:when>--%>
                    <c:when test="${survey.showMode  == 'ONE'}">
                        <p>Show mode - showing question on the initiative of the respondent</p>
                    </c:when>
<%--                    <c:when test="${survey.showMode== 'TIME'}">--%>
<%--                        <p>Show mode - showing question after a determined time</p>--%>
<%--                    </c:when>--%>
                </c:choose>


                <c:choose>
                    <c:when test="${survey.resultsShow ==true}">
                        <p>Survey results will be available to all</p>
                    </c:when>
                    <c:otherwise>
                        <p>Survey results will be available only to you</p>
                    </c:otherwise>
                </c:choose>


                <hr class="mt-3 mb-3">

                <c:if test="${not empty survey.questions}">
                    <div>
                        <h4 class="mb-5 mt-5">Your questions:</h4>
                        <ul style="list-style-type: none;">

                            <c:set var="i" value="1" scope="page"/>
                            <c:forEach items="${survey.questions}" var="question">

                                <li class="mt-5">
                                    <p><span class="number">${i}</span> Question</p>

                                    <c:choose>
                                        <c:when test="${question.type =='single'}">
                                            <p>This is a one answer question</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>This is a multiple choice question</p>
                                        </c:otherwise>
                                    </c:choose>


                                    <div class="border p-3 mb-5 mt-3 rounded border-info">
                                        <p>${question.description}</p>
                                    </div>


                                    <ol>
                                        <c:if test="${ not empty question.questionAnswers}">
                                            <p>Answers:</p>
                                            <c:forEach items="${question.questionAnswers}" var="answer">
                                                <li class="mb-5 mt-2">

                                                    <div class="border rounded p-2 ">
                                                        <p>${answer.description}</p>
                                                    </div>
                                                    <c:if test="${answer.right==true}">
                                                        <p class="mt-1 text-success">Defined as right answer</p>
                                                    </c:if>

                                                </li>
                                            </c:forEach>
                                        </c:if>
                                        <a class="btn  col-2 mt-2 p-3"
                                           href="${s:mvcUrl("QC#questionResultsGet").arg(0, survey.id).arg(1, question.id).build()}">See
                                            results</a>

                                    </ol>

                                </li>
                                <c:set var="i" value='${i+1}' scope="page"/>
                            </c:forEach>
                        </ul>


                    </div>

                </c:if>


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
