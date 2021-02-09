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

    <link rel="stylesheet" href="../style/reset.css">

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
    <h2>Cabinet page</h2>
    <p style="color: green"><c:out value="${login}"/> signed-in successfully.</p>
    <form action="logout" method="get">
        <button type="submit" class="btn btn-primary mb-3">Sign out</button>
    </form>
    <br>
    <c:if test="${role.equals(\"admin\")}">
        <a href="/userlist">User list</a>
    </c:if>
    <c:if test="${role.equals(\"user\") || role.equals(\"admin\")}">
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
                                       required="required" pattern="[A-Za-z0-9_!?]{4,16}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="newPassword" class="col-sm-2 col-form-label">New password</label>
                            <div class="col-sm-10">
                                <input name="newPassword" type="password" class="form-control" id="newPassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{4,16}">
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
                        <c:if test="${successChange==true}">
                            <h6 style = "color: green">Password changed successfuly.</h6>
                        </c:if>
                    </form>

                </td>
            </tr>
            <tr>
                <td scope="row">Email</td>
                <td>${userData.email}</td>
            </tr>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>