<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="" class="btn btn--small btn--without-border"><spring:message code="login.message"/> </a></li>
            <li><a href="#" class="btn btn--small btn--highlighted"><spring:message code="create.account.message"/> </a></li>
        </ul>

        <ul>
            <li><a href="#" class="btn btn--without-border active"><spring:message code="start.message"/> </a></li>
            <li><a href="#" class="btn btn--without-border"><spring:message code="about.idea.message"/></a></li>
            <li><a href="#" class="btn btn--without-border"><spring:message code="about.us"/></a></li>
            <li><a href="#" class="btn btn--without-border"><spring:message code="institutions.message"/></a></li>
            <li><a href="#" class="btn btn--without-border"><spring:message code="contact.message"/></a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>
<script src="<c:url value="../../resources/js/app.js"/>"></script>
</body>
</html>
