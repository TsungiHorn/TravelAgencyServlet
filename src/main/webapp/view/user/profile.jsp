<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap"/>
        </svg>
        <span class="fs-4">TravelAgency</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/home" class="nav-link active" aria-current="page">Home</a></li>
        <li class="nav-item"><a href="/catalog" class="nav-link">Catalog</a></li>
        <li class="nav-item"><a href="/profile" class="nav-link">Profile</a></li>
        <li class="nav-item"><a href="/logout" class="nav-link">Logout</a></li>
    </ul>
</header>
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
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start
                                    date: ${tour.getTour().getStartDate()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price: ${tour.getFinalPrice()}$
                                </p>
                            </div>
                            <br>
                            <br>
                            <div class="col-sm-20">
                                <div class="btn-group">
                                    <form action="${pageContext.request.contextPath}/deleteTour?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}"
                                          method="post">
                                        <button class="btn btn-block btn-primary" type="submit">Cancel</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/buy?ui=${tour.getUser().getId()}&ti=${tour.getTour().getId()}"
                                          method="post">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-block btn-primary"
                                                                                    type="submit">Buy
                                        for ${tour.getFinalPrice()}$
                                    </button>
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
</html>
