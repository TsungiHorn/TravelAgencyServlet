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
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><font color="#f0f8ff">Home</font></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/catalog">
                    <font color="#f0f8ff">Catalog</font></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile"><font color="#f0f8ff">Account</font></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <font color="#f0f8ff">Dropdown link</font>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<br>
<main>
    <div class="container my-5">
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 py-5 mx-auto">
                <h1 class="display-5 fw-normal">Piece|Shopping|Excursion</h1>
                <p class="fs-5">T</p>
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
