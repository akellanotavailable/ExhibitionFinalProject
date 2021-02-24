<%@ page import="com.epam.expositions.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>

    <link rel="stylesheet" href="style/reset.css">

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Gallery</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                    <input name="search" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <p style="color: green"><c:out value="${login}"/> signed-in successfully.</p>
    <form action="logout" method="get">
        <button type="submit" class="btn btn-primary mb-3">Sign out</button>
    </form>
    <br>
    <c:if test="${role.equals(\"admin\")}">
        <a href="/userlist">User list</a>
    </c:if>
    <c:if test="${role.equals(\"user\") || role.equals(\"admin\") || role.equals(\"client\")}">
        <table class="table">
            <tbody>
            <tr>
                <td scope="row">Login</td>
                <td>${userData.login}</td>
            </tr>
            <tr>
                <td scope="row">Password</td>
                <td scope="row">
                    <form action="changepassword" method="post">
                        <div class="mb-3 row">
                            <label for="oldPassword" class="col-sm-2 col-form-label">Current password</label>
                            <div class="col-sm-10">
                                <input name="password" type="password" class="form-control" id="oldPassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{1,16}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="newPassword" class="col-sm-2 col-form-label">New password</label>
                            <div class="col-sm-10">
                                <input name="newPassword" type="password" class="form-control" id="newPassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{1,16}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="rePassword" class="col-sm-2 col-form-label">Re-enter new password</label>
                            <div class="col-sm-10">
                                <input name="newRePassword" type="password" class="form-control" id="rePassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{4,16}">
                            </div>
                        </div>
                        <div id="errorMessage">

                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary mb-3">Change password</button>
                            </div>
                        </div>
                    </form>

                </td>
            </tr>
            <tr>
                <td scope="row">Email</td>
                <td>${userData.email}</td>
            </tr>
            <tr>
                <td>Your ticket history</td>
                <td><a href="/history">View here.</a></td>
            </tr>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>