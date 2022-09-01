<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>


<html>
<head>
    <title>Catalog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"></script>
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
                <h1 class="display-5 fw-normal"><fmt:message key="label.Catalog_ONE"/></h1>
                <p class="fs-5"><fmt:message key="label.Catalog_TWO"/></p>
                <p><fmt:message key="label.Catalog_THREE"/></p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/catalog?filterName=price"
                   role="button"><fmt:message key="label.Catalog_FOUR"/></a>


                <a class="btn btn-primary" href="${pageContext.request.contextPath}/catalog?filterName=stars"
                   role="button"><fmt:message key="label.Catalog_FIVE"/></a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/catalog?filterName=people"
                   role="button"><fmt:message key="label.Catalog_SIX"/></a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/catalog" role="button"><fmt:message
                        key="label.Catalog_SEVEN"/></a>
            </div>
        </div>
    </div>
</main>
<div class="row row-cols-1 row-cols-md-3 text-center">
    <c:forEach var="tour" items="${tours}">
        <form action="/tour" method="get">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        <h4>${tour.getTitle()} <c:if test="${tour.getHot()}"><font color="red"> <fmt:message
                                key="label.Catalog.EIGHT"/></font> </c:if></h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                                <fmt:message key="label.Catalog.NINE"/>
                        <td> ${tour.getCity()} </td>
                        <fmt:message key="label.Catalog.TEN"/>
                        <td> ${tour.getCountry()} </td>
                        <fmt:message key="label.Catalog.ELEVEN"/>
                        <td> ${tour.getHotelStars()} </td>
                            <fmt:message key="label.Catalog.TWELVE"/></p>
                        <a class="btn btn-primary" href="tour?i=${tour.getId()}"><fmt:message
                                key="label.Catalog.THIRTEEN"/></a>
                    </div>
                </div>
            </div>
        </form>
    </c:forEach>
</div>
<br>
<div class="row row-cols-1 row-cols-md-3 text-center">
    <nav aria-label="Page navigation example">
        <ul class="pagination">

            <li class="page-item"><a class="page-link"
                                     <c:if test="${currentPage != 1}">href="/catalog?page=${currentPage-1}</c:if>">Previous</a>
            </li>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a class="page-link" href="/catalog?page=${i}">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/catalog?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <li class="page-item"><a class="page-link"
                                     <c:if test="${currentPage lt noOfPages}">href="/catalog?page=${currentPage + 1}</c:if>">Next</a>
            </li>

        </ul>
    </nav>
</div>
</section>

<br>
<br>
<footer class="bg-light text-center text-white">
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2022
        <a class="text-white">TravelAgency</a>
    </div>
</footer>
</body>
</html>
