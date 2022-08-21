<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 17.08.2022
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
        <span class="fs-4">TravelAgency</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/home" class="nav-link active" aria-current="page">Home</a></li>
        <li class="nav-item"><a href="/catalog" class="nav-link">Catalog</a></li>
        <li class="nav-item"><a href="/profile" class="nav-link">Profile</a></li>
        <li class="nav-item"><a href="/logout" class="nav-link">Logout</a></li>
    </ul>
</header>
<br>
<section style="background-color: #eee;">
        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                             class="rounded-circle img-fluid" style="width: 150px;">
                        <br>
                        <h5 class="my-3">${user.getName()}</h5>
                        <p class="text-muted mb-0">${user.getEmail()}</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Tours history:</h2>
                            </div>
                            <br>
                        </div>
                        <div class="row">

                            <c:forEach items="${userTours}" var="tour">
                                <div class="col-sm-10">
                                    <p class="mb-0"><strong>${tour.getTour().getTitle()}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status: ${tour.getStatus()}
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start date: ${tour.getTour().getStartDate()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price: ${tour.getFinalPrice()}$</p>
                                </div>
                                <br>
                                <br>
                                <div class="col-sm-20">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/deleteTour?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}" role="button">Remove</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/buy?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}" role="button">Buy for ${tour.getFinalPrice()}$</a>

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
</section>
<br>
<br>
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
                        <a href="/profile" class="text-reset">Account</a>
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
