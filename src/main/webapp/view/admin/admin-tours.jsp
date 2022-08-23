<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tours</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap"/>
        </svg>
        <span class="fs-4">TravelAgency 4Admin</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/4admin-users" class="nav-link">Users</a></li>
        <li class="nav-item"><a href="/4admin-catalog" class="nav-link">Tours</a></li>
        <li class="nav-item"><a href="/logout" class="nav-link">Logout</a></li>
    </ul>
</header>
<main>
    <div class="container my-5">
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 py-5 mx-auto">
                <h1 class="display-5 fw-normal">Tour management</h1>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddTourServlet" role="button">Add new tour</a>
                </p>
            </div>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3 text-center">

        <c:forEach var="tour" items="${tours}">
        <form action="DeleteTourServlet?i=${tour.getId()}" method="post">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        <h4>${tour.getTitle()}</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                            Type: ${tour.getTourType()}&nbsp;&nbsp;&nbsp;
                            Person number: ${tour.getPersonNumber()}&nbsp;&nbsp;&nbsp;
                            Hotel stars: ${tour.getHotelStars()}&nbsp;&nbsp;&nbsp;
                            Start date: ${tour.getStartDate()}&nbsp;&nbsp;&nbsp;
                            Price: ${tour.getPrice()}&nbsp;&nbsp;&nbsp;
                            Hot: ${tour.getHot()}&nbsp;&nbsp;&nbsp;
                            Hidden: ${tour.getHidden()}&nbsp;&nbsp;&nbsp;
                            Country: ${tour.getCountry()}&nbsp;&nbsp;&nbsp;
                            City: ${tour.getCity()}&nbsp;&nbsp;&nbsp;
                        </p>
                        <a class="btn btn-primary" href="edit-tour?i=${tour.getId()}">Edit</a>
                        <button class="btn btn-block btn-primary" type="submit">Delete</button>
                    </div>
                </div>
            </div>
        </form>
        </c:forEach>
    </div>
</main>
</body>
</html>