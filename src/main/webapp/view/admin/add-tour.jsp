<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add tour</title>
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
        <span class="fs-4">TravelAgency 4Admin</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/4admin-users" class="nav-link">Users</a></li>
        <li class="nav-item"><a href="/4admin-catalog" class="nav-link">Tours</a></li>
        <li class="nav-item"><a href="/logout" class="nav-link">Logout</a></li>
    </ul>
</header>
<main>
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
        <form action="${pageContext.request.contextPath}/AddTourServlet" method="post"
              class="card card-block m-x-auto bg-faded form-width">
            <legend class="m-b-1 text-xs-center">Create new tour</legend>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="one" type="text" placeholder="Title" name="title"/>
 <label for="one">Title</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="two" type="text" placeholder="REST, SHOPPING or EXCURSION" name="type"/>
 <label for="two">REST, SHOPPING or EXCURSION</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="three" type="text" placeholder="Person number" name="person"/>
 <label for="three">Person number</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="four" type="text" placeholder="Hotel stars" name="stars" />
 <label for="four">Hotel stars</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="five" type="text" placeholder="Price" name="price" />
 <label for="five">Price</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="six" type="text" placeholder="Country" name="country" />
 <label for="six">Country</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="seven" type="text" placeholder="City" name="city" />
 <label for="seven">City</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="eight" type="date" placeholder="Start date" name="date" />
 <label for="eight">Start date</label>
 </span>
            </div>


            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="nine" type="text" placeholder="Hot" name="hot"/>
 <label for="nine">Hot(true or false)</label>
 </span>
            </div>

            <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" id="ten" type="text" placeholder="Hidden" name="hidden"/>
 <label for="ten">Hidden(true or false)</label>
 </span>
            </div>




            <div class="text-xs-center">
                <button class="btn btn-block btn-primary" type="submit">
                    <a href="${pageContext.request.contextPath}/4admin-catalog"><font color="white">Do edit</font></a>
                </button>
            </div>
        </form>
    </div>
</main>
<footer class="bg-light text-center text-white">
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2022
        <a class="text-white">TravelAgency</a>
    </div>
</footer>
</body>
</html>
