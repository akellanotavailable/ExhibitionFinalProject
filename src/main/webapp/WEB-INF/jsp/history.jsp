<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>

    <link rel="stylesheet" href="../../style/reset.css">

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
</head>
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Gallery</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="/expositions">Expositions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/about">About us</a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${login!=null}">
                            <a class="nav-link active" href="/cabinet" aria-disabled="true">Cabinet (${login})</a>
                        </c:if>
                        <c:if test="${login==null}">
                            <a class="nav-link active" href="/login" aria-disabled="true">Login</a>
                        </c:if>
                    </li>
                </ul>
                <form class="d-flex" action="/search">
                    <input name="search" class="form-control me-2" type="search" placeholder="Search"
                           aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Exposition</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${purchaseMap}">
            <tr>
                <td>${item.key}</td>
                <td>${item.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
