<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
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
    </ul>
</header>
<main>
    <div class="container my-5">
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 py-5 mx-auto">
                <h1 class="display-5 fw-normal">Travel Agency greets You!</h1>
                <p class="fs-5">To start choosing tours, click <a href="${pageContext.request.contextPath}/catalog">here</a> or the "Catalog" button at the top.</p>
                <p>We are operators of tours in more than 30 countries. We will be happy to help you choose a tour! But for this you need to log in.</p>
                <p>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login" role="button">Login here</a>
                </p>
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