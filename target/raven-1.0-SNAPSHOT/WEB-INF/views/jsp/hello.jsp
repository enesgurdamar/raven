<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>THE RAVEN</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="/resources/core/css/hello.css">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">The Raven - Home Page</a>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <p class="div1"></p>
        <h1>${title}</h1>
        <p>
            <c:if test="${not empty name}">
                Hello ${name}
            </c:if>

            <c:if test="${empty name}">
                Welcome to the Raven!
            </c:if>
        </p>
        <p>
            <a class="btn btn-primary btn-lg" role="button" id="list">List Users</a>
            <a class="btn btn-primary btn-lg" onclick="getElementById('add').style.display='block'">Add User</a>
        </p>

        <div id="add" style="display:none" class="container">

            <form method="post" action="signup">
                <input type="text" placeholder="Username" name="username" maxlength="20"/><br />
                <input type="password" placeholder="Password" name="password" maxlength="20"/><br />
                <input type="email" name="email" placeholder="E-Mail" maxlength="60"/><br />
                <input type="submit" value="Sign Up" />
            </form>

        </div>
    </div>

</div>

        <table id="excelDataTable"></table>

    <br>
    <footer>
        <p>&#169 Enderun 2016</p>
    </footer>

</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/core/js/hello.js"></script>

</body>

</html>