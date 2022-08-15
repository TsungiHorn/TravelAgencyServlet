<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 13.08.2022
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/login"><font color="#f0f8ff">Account</font></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><font color="#f0f8ff">Pricing</font></a>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"/>
<style>
    .form-width {
        max-width: 25rem;
    }

    .has-float-label {
        position: relative;
    }

    .has-float-label label {
        position: absolute;
        left: 0;
        top: 0;
        cursor: text;
        font-size: 75%;
        opacity: 1;
        -webkit-transition: all .2s;
        transition: all .2s;
        top: -.5em;
        left: 0.75rem;
        z-index: 3;
        line-height: 1;
        padding: 0 1px;
    }

    .has-float-label label::after {
        content: " ";
        display: block;
        position: absolute;
        background: white;
        height: 2px;
        top: 50%;
        left: -.2em;
        right: -.2em;
        z-index: -1;
    }

    .has-float-label .form-control::-webkit-input-placeholder {
        opacity: 1;
        -webkit-transition: all .2s;
        transition: all .2s;
    }

    .has-float-label .form-control:placeholder-shown:not(:focus)::-webkit-input-placeholder {
        opacity: 0;
    }

    .has-float-label .form-control:placeholder-shown:not(:focus) + label {
        font-size: 150%;
        opacity: .5;
        top: .3em;
    }

    .input-group .has-float-label {
        display: table-cell;
    }

    .input-group .has-float-label .form-control {
        border-radius: 0.25rem;
    }

    .input-group .has-float-label:not(:last-child) .form-control {
        border-bottom-right-radius: 0;
        border-top-right-radius: 0;
    }

    .input-group .has-float-label:not(:first-child) .form-control {
        border-bottom-left-radius: 0;
        border-top-left-radius: 0;
        margin-left: -1px;
    }
</style>
<div class="p-x-1 p-y-3">
    <form action="<%= request.getContextPath() %>/registration" method="post"
          class="card card-block m-x-auto bg-faded form-width">
        <legend class="m-b-1 text-xs-center">Registration</legend>
        <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="first" type="text" placeholder="Name" name="name"/>
 <label for="first">Full name</label>
 </span>
        </div>
        <div class="form-group input-group">
            <span class="input-group-addon">@</span>
            <span class="has-float-label">
 <input class="form-control" id="email" type="text" placeholder="name@example.com" name="email"/>
 <label for="email">E-mail</label>
 </span>
        </div>
        <div class="form-group has-float-label">
            <input class="form-control" id="password" type="text" placeholder="••••••••" name="password"/>
            <label for="password">password</label>
        </div>
        <div class="form-group">
                <span>Already have an account?
 <a href="${pageContext.request.contextPath}/view/login.jsp">Login</a>
 </span>
        </div>
        <div class="text-xs-center">
            <button class="btn btn-block btn-primary" type="submit">Create an account</button>
        </div>
    </form>
</div>
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
