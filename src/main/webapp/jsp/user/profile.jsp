<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Your Profile</title>
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
            <a class="p-2 text-muted" href="#">Edit profile</a>
            <a class="p-2 text-muted" href="${s:mvcUrl('SC#createSurveyGet').build()}">Create a survey</a>
            <a class="p-2 text-muted" href="#survey-code-form">Enter a code</a>
        </nav>
    </div>


    <div class="user-surveys jumbotron p-4 p-md-5 text-dark rounded bg-light row">
        <c:if test="${not empty message}">
            <div class="h4 text-success">
                <p>${message}</p>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty surveys}">
                <div class="col-12 mb-4">
                    <h2 class="text-center">Your surveys!</h2>
                </div>
                <div class="table-responsive">
                    <table class="col-12 css-serial table table-bordered table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col" class="text-center" width="5%">#</th>
                            <th scope="col" width="50%" class="text-center">Survey name</th>
                            <th scope="col" width="25%" class="text-center">Survey code</th>
                            <th id="actions" scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveys}" var="tr">
                            <tr>
                                <td scope="row" class="text-center"></td>
                                <td>${tr.name}</td>
                                <td>${tr.id}</td>
                                <td>
                                    <div class="row justify-content-around p-2 m-0">
                                        <div class="p-0">
                                            <a href="" class="text-dark"><img
                                                    src="https://img.icons8.com/ultraviolet/40/000000/pencil.png" width="25px"
                                            >Edit</a>
                                        </div>
                                        <div class="text-dark survey-delete p-0" data-toggle="modal" data-target="#deleteModal" style="cursor: pointer;">
                                            <form action="${s:mvcUrl("SC#deleteSurvey").build()}${tr.id}" method="post" class="survey-delete" id="survey-delete-${tr.id}">

                                                <img
                                                        src="https://img.icons8.com/color/48/000000/waste.png" width="30px"
                                                >Delete
                                                <input type="hidden"
                                                       name="${_csrf.parameterName}"
                                                       value="${_csrf.token}"/>
                                            </form>
                                        </div>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-12 mb-4">
                    <h2>You have not got any surveys! </h2>
                </div>
            </c:otherwise>
        </c:choose>
        <%--TODO add btn add survey--%>
    </div>

    <%--TODO add select by clicking in row--%>


    <div class="modal" id="deleteModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Confirm delete</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    Do you really want to delete this survey?
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <button type="submit" id="btn-delete" class="btn btn-primary">Yes</button>
                </div>

            </div>
        </div>
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
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/base.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


</body>
</html>
