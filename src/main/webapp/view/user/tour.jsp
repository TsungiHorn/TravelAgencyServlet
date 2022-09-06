<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>${tour.getTitle()}</title>
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
<main>
    <div class="container my-5">
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 py-5 mx-auto">
                <form action="${pageContext.request.contextPath}/booking?q=${tour.getId()}" method="post">
                    <h1 class="display-5 fw-normal">
                        ${tour.getTitle()}
                    </h1>
                    <br>
                    <p class="fs-5"><fmt:message key="label.Tour.ONE"/> <b>${tour.getCity()}</b>, <b>${tour.getCountry()}</b></p>
                    <p class="fs-5"><fmt:message key="label.Tour.TWO"/> <b>${tour.getPersonNumber()}
                        <fmt:message key="label.Tour.SEVEN"/></b>
                    </p>
                    <p class="fs-5"><fmt:message key="label.Tour.THREE"/> <b>
                        <c:if test="${tour.getTourType().toString().equals('REST')}">
                            <fmt:message key="label.Tour.EIGHT"/>
                        </c:if>
                        <c:if test="${tour.getTourType().toString().equals('SHOPPING')}">
                            <fmt:message key="label.Tour.NINE"/>
                        </c:if>
                        <c:if test="${tour.getTourType().toString().equals('EXCURSION')}">
                            <fmt:message key="label.Tour.TEN"/>
                        </c:if>
                    </b></p>
                    <p class="fs-5"><fmt:message key="label.Tour.FOUR"/> <b>${tour.getStartDate()}</b></p>
                    <p class="fs-5"><fmt:message key="label.Tour.FIVE"/> <b>${tour.getPrice()}$</b></p>
                    <p>
<%--                        ${tour.getTourType()}--%>
                    <div class="text-xs-center">
                        <button class="btn btn-block btn-primary" type="submit"><fmt:message key="label.Tour.SIX"/></button>
                    </div>
                    </p>
                </form>
            </div>
        </div>
    </div>
</main>
<footer class="text-center text-lg-start bg-light text-muted">
    <section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
    </section>
    <section class="">
        <div class="container text-center text-md-start mt-5">
            <div class="row mt-3">
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <!-- Content -->
                    <h3 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>Travel Agency
                    </h3>
                </div>
                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Links
                    </h6>
                    <p>
                        <a href="${pageContext.request.contextPath}/catalog" class="text-reset">Catalog</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Account</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Bucket</a>
                    </p>
                </div>
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                    <p><i class="fas fa-home me-3"></i> Atlantis, Mermaid 17, Atlantic Sea</p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        tagency@gmail.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i> +00 000 000 00</p>
                </div>
            </div>
        </div>
    </section>
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        © 2022
    </div>
</footer>
</body>
</html>