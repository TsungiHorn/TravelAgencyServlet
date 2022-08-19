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
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
        <a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><font color="#f0f8ff">Home</font></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/catalog"><font color="#f0f8ff">Catalog</font></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile"><font color="#f0f8ff">Account</font></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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