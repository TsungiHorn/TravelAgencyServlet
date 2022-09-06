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
    <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap"/>
        </svg>
        <span class="fs-4">TravelAgency 4Manager</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/manager-users" class="nav-link">Orders</a></li>
        <li class="nav-item"><a href="/logout" class="nav-link">Logout</a></li>
    </ul>
</header>
<main>

    <div class="row">
        <div class="col-lg-12">
            <div class="card mb-4">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2>Orders:</h2>
                        </div>
                        <br>
                        <hr>
                    </div>
                    <div class="row">
                        <c:forEach var="order" items="${orders}">
                            <div class="col-sm-20">
                                <p class="mb-0">Name: <strong>${order.getUser().getName()}</strong>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email: <strong>${order.getUser().getEmail()}</strong>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tour: <strong>${order.getTour().getTitle()}</strong>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status: ${order.getStatus()}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start date: ${order.getTour().getStartDate()}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price: ${order.getTour().getPrice()}$
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order time: ${order.getOrderTime()}
                                </p>
                            </div>
                            <br>
                            <br>
                            <div class="col-sm-20">
                                <div class="btn-group">
                                    <form action="${pageContext.request.contextPath}/manager-edit-status?ui=${order.getUser().getId()}&ti=${order.getTour().getId()}&act=reg"
                                          method="post">
                                        <button class="btn btn-block btn-primary" type="submit">Registered</button>
                                    </form>
                                    &nbsp;&nbsp;&nbsp;<form action="${pageContext.request.contextPath}/manager-edit-status?ui=${order.getUser().getId()}&ti=${order.getTour().getId()}&act=pay"
                                          method="post">
                                        <button class="btn btn-block btn-primary" type="submit">Paid</button>
                                    </form>
                                    &nbsp;&nbsp;&nbsp;<form action="${pageContext.request.contextPath}/manager-edit-status?ui=${order.getUser().getId()}&ti=${order.getTour().getId()}&act=can"
                                          method="post">
                                        <button class="btn btn-block btn-primary" type="submit">Canceled</button>
                                    </form>
                                    </form>
                                    &nbsp;&nbsp;&nbsp;<form action="${pageContext.request.contextPath}/manager-delete-order?ui=${order.getUser().getId()}&ti=${order.getTour().getId()}&act=can"
                                                            method="post">
                                    <button class="btn btn-block btn-primary" type="submit">Delete order</button>
                                </form>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
