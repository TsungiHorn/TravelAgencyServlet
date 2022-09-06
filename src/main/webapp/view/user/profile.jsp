<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
    <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap"/>
        </svg>
        <span class="fs-4">TravelAgency</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/home" class="nav-link active" aria-current="page"><fmt:message key="label.General_Home" /></a></li>
        <li class="nav-item"><a href="/catalog" class="nav-link"><fmt:message key="label.General_Catalog" /></a></li>
        <li class="nav-item"><a href="/profile" class="nav-link"><fmt:message key="label.General_Profile" /></a></li>
        <li class="nav-item"><a href="/changing-lang?lang=ua" class="nav-link"><fmt:message key="label.General_UA" /></a></li>
        <li class="nav-item"><a href="/changing-lang?lang=en" class="nav-link"><fmt:message key="label.General_ENG" /></a></li>
    </ul>
</header>
<div class="row">
    <div class="col-lg-4">
        <div class="card mb-4">
            <div class="card-body text-center">
                <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.ltSIBM-hmrYmSVoNipRazQHaHa%26pid%3DApi&f=1"
                     class="rounded-circle img-fluid" style="width: 150px;">
                <br>
                <h5 class="my-3">${user.getName()}</h5>
                <p class="text-muted mb-0">${user.getEmail()}</p>
                <br>
                <a class="btn btn-primary" href="/logout"><fmt:message key="label.Profile.ONE"/></a>
            </div>
        </div>
    </div>
    <div class="col-lg-8">
        <div class="card mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-3">
                        <h2><fmt:message key="label.Profile.TWO"/></h2>
                    </div>
                    <br>
                </div>
                <div class="row">

                    <c:forEach items="${userTours}" var="tour">
                        <div class="col-sm-10">
                            <p class="mb-0"><strong>${tour.getTour().getTitle()}</strong>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
                                        key="label.Profile.THREE"/>
                                <c:if test="${tour.getStatus().toString().equals('REGISTERED')}">
                                    <fmt:message key="label.Profile.EIGHT"/>
                                </c:if>
                                <c:if test="${tour.getStatus().toString().equals('PAID')}">
                                    <fmt:message key="label.Profile.NINE"/>
                                </c:if>
                                <c:if test="${tour.getStatus().toString().equals('CANCELED')}">
                                    <fmt:message key="label.Profile.TEN"/>
                                </c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
                                        key="label.Profile.FOUR"/> ${tour.getTour().getStartDate()}
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
                                        key="label.Profile.FIVE"/> ${tour.getFinalPrice()}$
                            </p>
                        </div>
                        <br>
                        <br>
                        <div class="col-sm-20">
                            <div class="btn-group">


                                <form action="${pageContext.request.contextPath}/deleteTour?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}"
                                      method="post">
                                    <button class="btn btn-block btn-primary" type="submit"
                                            <c:if test="${tour.getStatus().toString().equals('CANCELED')}">
                                                <c:out value="disabled"/>
                                            </c:if>><fmt:message
                                            key="label.Profile.SIX"/></button>
                                </form>
                                <form action="${pageContext.request.contextPath}/buy?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}"
                                      method="post">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-block btn-primary" type="submit"
                                            <c:if test="${tour.getStatus().toString().equals('PAID') or tour.getStatus().toString().equals('CANCELED')}">
                                                <c:out value="disabled"/>
                                            </c:if>><fmt:message
                                            key="label.Profile.SEVEN"/> <c:out value="${tour.getTour().getPrice()}"/>$</button>
                                </form>
                            </div>
                        </div>

                        <br>
                        <br>
                        <br>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
</body>
<html/>
