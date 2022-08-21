<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
            <span class="fs-4">TravelAgency</span>
        </a>

        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/home" class="nav-link active" aria-current="page">Home</a></li>
            <li class="nav-item"><a href="/catalog" class="nav-link">Catalog</a></li>
            <li class="nav-item"><a href="/profile" class="nav-link">Profile</a></li>
        </ul>
    </header>
<br>
<main>
    <div class="container my-5">
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 py-5 mx-auto">
                <h1 class="display-5 fw-normal">Piece|Shopping|Excursion</h1>
                <p class="fs-5">Choose your tour!</p>
                <p>We are operators of tours in more than 30 countries. We will be happy to help you choose a tour! But
                    for this you need to log in.</p>
                <p>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login" role="button">Login
                        here</a>
                </p>
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
                    <h4>${tour.getTitle()}</h4>
                </div>
                <div class="card-body">
                    <p class="card-text"> Trip to the
                    <td> ${tour.getCity()} </td>
                    of
                    <td> ${tour.getCountry()} </td>
                    with accommodation in a hotel with
                    <td> ${tour.getHotelStars()} </td>
                    stars.</p>
                        <a class="btn btn-primary" href="tour?i=${tour.getId()}">View</a>
                </div>
            </div>
        </div>
        </form>
    </c:forEach>

</div>
</section>
<br>
<br>
<footer class="bg-light text-center text-white">
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2022
        <a class="text-white" href="https://mdbootstrap.com/">TravelAgency</a>
    </div>
</footer>
</body>
</html>
