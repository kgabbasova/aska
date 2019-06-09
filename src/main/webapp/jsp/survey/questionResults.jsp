<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <title>Question Results</title>
    <meta charset="utf-8">
    <link href="/resources/css/styles.css" rel="stylesheet" type="text/css">
</head>

<script>
    var surveyId = ${question.survey.id};
    var surveyTitle = "${question.survey.name}";
    var surveyComment = "${question.description}";
    var surveyFooter = "Respondents: ${question.votes}";
    var questionId = ${question.id};
    var surveyData = [];
</script>


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

    <div class="row mb-5" style="min-height:200px">
        <div class="col"></div>
        <div class="col col-centered">
            <div id="chart"></div>
        </div>
        <div class="col"></div>
    </div>


    <div class="jumbotron-mini text-dark rounded bg-light row">
        <div class="col-4 mx-auto text-center">
            <span id="surveys-code" class="h4"></span>
        </div>
    </div>

    <div class="row">
        <div class="col-6">
        <c:if test="${not empty previousQuestion}">
            <a class="btn btn-block btn-info rounded col-2 mt-2 mb-4 p-3 mr-auto"
               href="${s:mvcUrl("QC#questionResultsGet").arg(0, question.survey.id).arg(1, previousQuestion.id).build()}">Previous</a>
        </c:if>
        </div>
        <div class="col-6">
        <c:if test="${not empty nextQuestion}">
            <a class="btn btn-block btn-info rounded col-2 mt-2 mb-4 p-3 ml-auto"
               href="${s:mvcUrl("QC#questionResultsGet").arg(0, question.survey.id).arg(1, nextQuestion.id).build()}">Next</a>
        </c:if>
        </div>
    </div>


</div>

<footer class="blog-footer" id="partners">
    <p>&copy; 2019 Aska, Inc.</p>
    <p>
        <a href="#Aska-logo">Back to top</a>
    </p>
</footer>


</div>

<script src="/resources/js/jquery-3.4.0.min.js"></script>
<script src="/resources/js/d3.min.js"></script>
<script src="/resources/js/app.js"></script>
<script>


    var pie = new d3pie("chart", {
        "header": {
            "title": {
                "text": surveyTitle,
                "fontSize": 30,
                "font": "verdana"
            },
            "subtitle": {
                "text": surveyComment,
                "color": "#3b3b3b",
                "fontSize": 20,
                "font": "verdana",
            },
            "titleSubtitlePadding": 24
        },
        "footer": {
            "text": surveyFooter,
            "color": "#999999",
            "fontSize": 11,
            "font": "open sans",
            "location": "bottom-center"
        },
        "size": {
            "canvasHeight": 400,
            "canvasWidth": 590,
            "pieOuterRadius": "88%"
        },
        "data": {
            "content": surveyData
        },
        "labels": {
            "outer": {
                "pieDistance": 32
            },
            "inner": {
                "format": "value"
            },
            "mainLabel": {
                "font": "verdana"
            },
            "percentage": {
                "color": "#e1e1e1",
                "font": "verdana",
                "decimalPlaces": 0
            },
            "value": {
                "color": "#e1e1e1",
                "font": "verdana"
            },
            "lines": {
                "enabled": true,
                "color": "#cccccc"
            },
            "truncation": {
                "enabled": true
            }
        },
        "effects": {
            "pullOutSegmentOnClick": {
                "effect": "linear",
                "speed": 400,
                "size": 8
            }
        }
    });

    setSurveysCode();
    $(document).ready(function () {
        updateChart();
        startPolling();
    })
</script>


</body>
</html>
